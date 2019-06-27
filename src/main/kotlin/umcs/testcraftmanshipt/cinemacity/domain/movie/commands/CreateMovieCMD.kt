package umcs.testcraftmanshipt.cinemacity.domain.movie.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

class CreateMovieCMD(val expectedMovieName: String, val cinemaId: DomainObjectID) : Command
