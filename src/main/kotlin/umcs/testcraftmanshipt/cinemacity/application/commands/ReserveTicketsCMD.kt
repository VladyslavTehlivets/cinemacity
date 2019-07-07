package umcs.testcraftmanshipt.cinemacity.application.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.Reservation

class ReserveTicketsCMD(val showId: String, val username: String, val selectedPlaces: MutableList<Reservation>) : Command
