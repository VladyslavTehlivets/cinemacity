package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.application.commands.AcceptReservationCMD
import umcs.testcraftmanshipt.cinemacity.application.commands.ReserveTicketsCMD
import umcs.testcraftmanshipt.cinemacity.domain.DomainException
import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import java.util.*

class TicketBoard(val showId: ShowId, val ticketPlaces: List<TicketPlace>, id: TicketBoardId) : DomainObject(id) {
    constructor(id: TicketBoardId, showId: ShowId, ticketPlaces: List<TicketPlace>) : this(showId, ticketPlaces, id)
    constructor(showId: ShowId, ticketPlaces: List<TicketPlace>) : this(showId, ticketPlaces, TicketBoardId())

    private fun getPlace(rowId: Int, columnId: Int): TicketPlace {
        return ticketPlaces.firstOrNull { it.rowIndex == rowId && it.colIndex == columnId }
                ?: throw DomainException("ticketBoard.noSuchPlace")
    }

    fun isAvailableToReservePlaces(ticketCount: Int): Boolean {
        return ticketPlaces.count { it.isNotReserved() } >= ticketCount
    }

    fun acceptReservation(command: AcceptReservationCMD) {
        val acceptedCount = ticketPlaces
                .filter { it.hasNumber(command.reservationNumber) }
                .onEach { it.acceptReservation() }
                .count()

        if ((acceptedCount < 1)) {
            throw DomainException("ticketBoard.thereIsNoReservationForClient")
        }
    }

    fun rejectNotPayedReservations() {
        ticketPlaces
                .filter { it.isReserved() }
                .forEach { it.cancelReservation() }
    }

    fun reserveTickets(command: ReserveTicketsCMD): String {
        val reservationNumber = UUID.randomUUID().toString()

        command.selectedPlaces
                .map { getPlace(it.rowSeatPlace, it.columnSeatPlace) }
                .forEach { it.reserve(command, reservationNumber) }

        return reservationNumber
    }
}