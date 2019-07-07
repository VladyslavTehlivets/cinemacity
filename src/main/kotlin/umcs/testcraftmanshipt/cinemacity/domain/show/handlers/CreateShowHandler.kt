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
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.ShowTicketDiscountRepository
import java.math.BigDecimal

@Service
class CreateShowHandler(private val showRepository: ShowRepository,
                        private val ticketBoardRepository: TicketBoardRepository,
                        private val showDiscountRepository: ShowTicketDiscountRepository) : Handler<CreateShowCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateShowCMD::class
    }

    override fun handle(command: CreateShowCMD): DomainObjectID {
        val show = createShow(command)
        showRepository.save(show)

        val showId = ShowId(show.id.value)
        val ticketBoard = createTicketBoard(showId)
        ticketBoardRepository.save(ticketBoard)

        val showDefaultDiscount = TicketDiscount(showId, "NORMALLY_TICKET", BigDecimal(100))
        showDiscountRepository.save(showDefaultDiscount)

        return show.id
    }
}
