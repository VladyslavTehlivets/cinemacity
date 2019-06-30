package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

//todo change to factory
//todo unsigned int add validation
class TicketBoard(showId: DomainObjectID, rowSize: Int = 20, columnSize: Int = 30, private val rows: List<TicketRow> = MutableList(rowSize) { TicketRow(columnSize) }) : DomainObject() {
    fun get(rowId: Int, columnId: Int): TicketColumn {
        return rows[rowId].getColumn(columnId)
    }

    fun isAvailableToReservePlaces(ticketCount: Int) {
        rows.flatMap { ticketRow -> ticketRow.ticketColumns }
                .filter { it.isPossibleToReserve() }
                .count() >= ticketCount
    }
}