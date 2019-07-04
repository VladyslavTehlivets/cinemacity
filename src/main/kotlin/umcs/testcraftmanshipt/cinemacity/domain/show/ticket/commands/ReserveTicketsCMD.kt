package umcs.testcraftmanshipt.cinemacity.domain.show.ticket.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.Reservation

class ReserveTicketsCMD(val username: String, val selectedPlaces: MutableList<Reservation>, showId: String) : Command
