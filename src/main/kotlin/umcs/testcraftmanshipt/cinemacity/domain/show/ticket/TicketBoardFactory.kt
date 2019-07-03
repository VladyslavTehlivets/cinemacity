package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

class TicketBoardFactory {
    companion object {
        @JvmStatic
        fun createTicketBoard(showId: DomainObjectID, rowSize: Int = 20, columnsSize: Int = 30): TicketBoard {

            val rows = (1..rowSize).map { rowIndex -> (1..columnsSize).map { TicketPlace(rowIndex, it) } }.flatten()
            return TicketBoard(showId, rows)
        }
    }
}
