package umcs.testcraftmanshipt.cinemacity.domain.show.ticket.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command

class CheckTicketAvailabilityCMD(val ticketCount: Int, val showId: String) : Command
