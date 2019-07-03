package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.NOT_RESERVATED

//todo unsigned int add validation
class TicketBoard(showId: DomainObjectID, private val ticketPlaces: List<TicketPlace>) : DomainObject() {
    fun get(rowId: Int, columnId: Int): TicketPlace {
        return ticketPlaces.first { it.rowIndex == rowId && it.colIndex == columnId }
    }

    fun isAvailableToReservePlaces(ticketCount: Int): Boolean {
        return ticketPlaces.count { it.ticketStatus == NOT_RESERVATED } >= ticketCount
    }
}