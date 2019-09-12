package umcs.testcraftmanshipt.cinemacity.ui.cashier.dto

import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount

class ReservationDTO(val columnSeatPlace: Int, val rowSeatPlace: Int, val ticketDiscount: String = TicketDiscount.BASE_NORMALLY_TICKET) {

}
