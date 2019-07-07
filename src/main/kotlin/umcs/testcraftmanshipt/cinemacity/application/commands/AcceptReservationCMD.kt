package umcs.testcraftmanshipt.cinemacity.application.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command

class AcceptReservationCMD(val showId: String, val userName: String, val reservationNumber: String) : Command
