package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.NOT_RESERVATED

class TicketPlace(val rowIndex: Int, val colIndex: Int) {
    val ticketStatus: ReservationStatus = NOT_RESERVATED
}
