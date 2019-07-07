package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount

import umcs.testcraftmanshipt.cinemacity.domain.Command

class CreateShowTicketDiscountCMD(val showId: String, val discountName: String, val percentFromNormalCost: Int) : Command
