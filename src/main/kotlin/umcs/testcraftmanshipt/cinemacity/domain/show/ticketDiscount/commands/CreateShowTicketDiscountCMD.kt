package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command

class CreateShowTicketDiscountCMD(val showId: String, val discountName: String, val percentFromNormalCost: Double) : Command
