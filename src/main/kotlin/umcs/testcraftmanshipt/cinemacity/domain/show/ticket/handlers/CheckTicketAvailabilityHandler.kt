package umcs.testcraftmanshipt.cinemacity.domain.show.ticket.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.exception.DomainEntityNotFoundException
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoard
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.commands.CheckTicketAvailabilityCMD

@Service//todo change to querida, not command
class CheckTicketAvailabilityHandler(private val ticketBoardRepository: TicketBoardRepository) : Handler<CheckTicketAvailabilityCMD> { //todo autowire

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CheckTicketAvailabilityCMD::class
    }

    override fun handle(command: CheckTicketAvailabilityCMD): DomainObjectID {
        val ticketBoard: TicketBoard = ticketBoardRepository.findByShowId(ShowId(command.showId)) ?: throw DomainEntityNotFoundException(command.showId)
        ticketBoard.isAvailableToReservePlaces(command.ticketCount)
        return ticketBoard.id
    }
}
