package umcs.testcraftmanshipt.cinemacity.application.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.application.commands.RejectNotPayedReservationsCMD
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.exception.DomainEntityNotFoundException
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.UserShowReservationRepo
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import java.time.LocalDateTime.now

@Service
class RejectNotPayedReservationsHandler(private val ticketBoardRepository: TicketBoardRepository,
                                        private val showRepository: ShowRepository,
                                        private val userShowReservRepo: UserShowReservationRepo) : Handler<RejectNotPayedReservationsCMD> {
    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == RejectNotPayedReservationsCMD::class
    }

    override fun handle(command: RejectNotPayedReservationsCMD): DomainObjectID {
        val showId = ShowId(command.showId)
        val show = showRepository.findById(showId) ?: throw DomainEntityNotFoundException(command.showId)
        val ticketBoard = ticketBoardRepository.findByShowId(showId)!!

        if (reservationAcceptationDateHasExpiredForShow(show)) {

            ticketBoard.rejectNotPayedReservations()
            ticketBoardRepository.save(ticketBoard)

            val reservationsInfo = userShowReservRepo.findByShowId(showId)
            reservationsInfo
                    .filter { it.isReserved() }
                    .forEach { it.rejectReservation() }
        }

        return ticketBoard.id
    }

    private fun reservationAcceptationDateHasExpiredForShow(show: Show) = show.dateTime.isBefore(now().plusMinutes(30))
}