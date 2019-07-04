package umcs.testcraftmanshipt.cinemacity.domain.show.ticketPolicy

import umcs.testcraftmanshipt.cinemacity.domain.Command

class CreateShowTicketPolicyCMD(val showId: String, val policyName: String, val percentFromNormalCost: Int) : Command
