package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.NOT_RESERVATED

class TicketRow(columnSize: Int = 30) {
    val ticketColumns = MutableList(columnSize) { index -> TicketColumn(index, NOT_RESERVATED) }

    fun getColumn(columnIndex: Int): TicketColumn {
        return ticketColumns[columnIndex]
    }
}
