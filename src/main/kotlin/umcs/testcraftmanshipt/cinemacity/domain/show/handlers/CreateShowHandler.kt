package umcs.testcraftmanshipt.cinemacity.domain.show.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowFactory.Companion.createShow
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardFactory.Companion.createTicketBoard
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount.Companion.BASE_NORMALLY_TICKET
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscountRepository

@Service
class CreateShowHandler(private val showRepository: ShowRepository,
                        private val ticketBoardRepository: TicketBoardRepository,
                        private val discountRepository: TicketDiscountRepository) : Handler<CreateShowCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateShowCMD::class
    }

    override fun handle(command: CreateShowCMD): DomainObjectID {
        val show = createShow(command)
        showRepository.save(show)

        val showId = ShowId(show.id.value)
        val ticketBoard = createTicketBoard(showId)
        ticketBoardRepository.save(ticketBoard)

        val showDefaultDiscount = TicketDiscount(showId, BASE_NORMALLY_TICKET, command.cost)
        discountRepository.save(showDefaultDiscount)

        return show.id
    }
}
