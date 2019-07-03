package umcs.testcraftmanshipt.cinemacity.domain.show.ticket.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.commands.ReserveTicketsCMD

@Service
class ReserveTicketsHandler(private val ticketBoardRepository: TicketBoardRepository) : Handler<ReserveTicketsCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == ReserveTicketsCMD::class
    }

    override fun handle(command: ReserveTicketsCMD) {
        //todo implement
    }
}
