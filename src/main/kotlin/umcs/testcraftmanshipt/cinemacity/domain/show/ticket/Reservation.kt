package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount.Companion.BASE_NORMALLY_TICKET

class Reservation(val columnSeatPlace: Int, val rowSeatPlace: Int, val ticketDiscount: String = BASE_NORMALLY_TICKET)
