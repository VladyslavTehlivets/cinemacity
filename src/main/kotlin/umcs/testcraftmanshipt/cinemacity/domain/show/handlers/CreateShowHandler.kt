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

@Service
class CreateShowHandler(private val showRepository: ShowRepository, val ticketBoardRepository: TicketBoardRepository) : Handler<CreateShowCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateShowCMD::class
    }

    override fun handle(command: CreateShowCMD): DomainObjectID {
        val show = createShow(command)
        showRepository.save(show)

        val ticketBoard = createTicketBoard(ShowId(show.id.value))
        ticketBoardRepository.save(ticketBoard)

        return show.id
    }
}
