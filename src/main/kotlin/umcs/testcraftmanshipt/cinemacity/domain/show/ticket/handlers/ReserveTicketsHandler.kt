package umcs.testcraftmanshipt.cinemacity.domain.show.ticket.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.commands.ReserveTicketsCMD
import java.util.*

@Service
class ReserveTicketsHandler(private val ticketBoardRepository: TicketBoardRepository) : Handler<ReserveTicketsCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == ReserveTicketsCMD::class
    }

    override fun handle(command: ReserveTicketsCMD): DomainObjectID {
        //todo implement
        return ShowId()
    }
}
