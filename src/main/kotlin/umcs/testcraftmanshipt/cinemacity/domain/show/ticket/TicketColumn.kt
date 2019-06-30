package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.NOT_RESERVATED

class TicketColumn(columnIndex: Int, private val reservationStatus: ReservationStatus = NOT_RESERVATED) {
    fun isPossibleToReserve(): Boolean {
        return reservationStatus == NOT_RESERVATED
    }
}
