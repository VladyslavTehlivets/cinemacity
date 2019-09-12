package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.exception.DomainEntityNotFoundException
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoard
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.commands.CheckTicketAvailabilityCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscountRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.commands.CreateShowTicketDiscountCMD
import java.math.BigDecimal
import java.math.BigDecimal.valueOf

@Service
class CreateShowTicketDiscountHandler(private val ticketDiscountRepository: TicketDiscountRepository, private val showRepository: ShowRepository) : Handler<CreateShowTicketDiscountCMD> {

    private final val BIG_DECIMAL_100 = valueOf(100)

    override fun handle(command: CreateShowTicketDiscountCMD): DomainObjectID {
        val showId = ShowId(command.showId)
        val show = showRepository.findById(showId)!!
        val discountCost = calculateShowDiscountCost(show, command)

        val ticketDiscount = TicketDiscount(showId, command.discountName, discountCost)
        ticketDiscountRepository.save(ticketDiscount)
        return ticketDiscount.id
    }

    private fun calculateShowDiscountCost(show: Show, command: CreateShowTicketDiscountCMD): BigDecimal {
        return show.cost.value
                .divide(BIG_DECIMAL_100)
                .multiply(valueOf(command.percentFromNormalCost))!!
    }

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateShowTicketDiscountCMD::class
    }
}
