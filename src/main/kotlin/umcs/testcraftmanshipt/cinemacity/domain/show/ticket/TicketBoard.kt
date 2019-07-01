package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

//todo unsigned int add validation
class TicketBoard(showId: DomainObjectID, private val ticketPlaces: List<TicketPlace>) : DomainObject() {
    fun get(rowId: Int, columnId: Int): TicketColumn {
        return ticketPlaces[rowId].getColumn(columnId)
    }

    fun isAvailableToReservePlaces(ticketCount: Int) {
        ticketPlaces.flatMap { ticketRow -> ticketRow.ticketColumns }
                .filter { it.isPossibleToReserve() }
                .count() >= ticketCount
    }
}