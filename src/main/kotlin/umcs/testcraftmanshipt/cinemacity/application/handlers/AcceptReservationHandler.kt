package umcs.testcraftmanshipt.cinemacity.application.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.application.commands.AcceptReservationCMD
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.exception.DomainEntityNotFoundException
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.UserShowReservationRepo
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository

@Service
class AcceptReservationHandler(private val ticketBoardRepository: TicketBoardRepository,
                               private val userShowReservRepo: UserShowReservationRepo) : Handler<AcceptReservationCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == AcceptReservationCMD::class
    }

    override fun handle(command: AcceptReservationCMD): DomainObjectID {

        val reservationInfo = userShowReservRepo.findByReservationNumber(command.reservationNumber) ?: throw DomainEntityNotFoundException(command.reservationNumber)
        val ticketBoard = ticketBoardRepository.findByShowId(ShowId(command.showId)) ?: throw DomainEntityNotFoundException(command.showId)

        ticketBoard.acceptReservation(command)
        ticketBoardRepository.save(ticketBoard)

        reservationInfo.acceptReservation()
        userShowReservRepo.save(reservationInfo)

        return ticketBoard.id
    }
}