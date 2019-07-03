package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.PricePolicy.NORMAL_TICKET

class Reservation(val columnSeatPlace: Int, val rowSeatPlace: Int, val pricePolicy: PricePolicy = NORMAL_TICKET)
