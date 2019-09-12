package umcs.testcraftmanshipt.cinemacity.application.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.application.commands.ReserveTicketsCMD
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainException
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.exception.DomainEntityNotFoundException
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfo
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.UserShowReservationRepo
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.RESERVED
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscountRepository
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ReserveTicketsHandler(private val ticketBoardRepository: TicketBoardRepository,
                            private val showRepository: ShowRepository,
                            private val ticketDiscountRepository: TicketDiscountRepository,
                            private val userShowReservRepo: UserShowReservationRepo) : Handler<ReserveTicketsCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == ReserveTicketsCMD::class
    }

    override fun handle(command: ReserveTicketsCMD): DomainObjectID {
        val showId = ShowId(command.showId)

        val show = showRepository.findById(showId) ?: throw DomainEntityNotFoundException(command.showId)
        val ticketBoard = ticketBoardRepository.findByShowId(showId) ?: throw DomainEntityNotFoundException(command.showId)

        val reservationNumber = ticketBoard.reserveTickets(command)
        ticketBoardRepository.save(ticketBoard)

        val reservationCost = calculateReservationCost(showId, command, show.cost.value)
        val reservationInfo = ReservationInfo(showId, reservationNumber, reservationCost, RESERVED, command.username)

        userShowReservRepo.save(reservationInfo)

        return reservationInfo.id
    }

    private fun calculateReservationCost(showId: ShowId, command: ReserveTicketsCMD, showCostValue: BigDecimal): BigDecimal {
        val showTicketDiscounts = ticketDiscountRepository.findByShowId(showId)
        val selectedPriceDiscounts = command.selectedPlaces.map { it.ticketDiscount }

        return showTicketDiscounts.filter { selectedPriceDiscounts.contains(it.discountName) }
                .map { showCostValue.setScale(0, RoundingMode.HALF_EVEN) }
                .reduce { acc, value -> acc.plus(value) } ?: throw DomainException("reserveTicketsHandler.ticketDiscountsNotConfiguredException")
    }
}
