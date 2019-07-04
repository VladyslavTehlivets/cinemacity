package umcs.testcraftmanshipt.cinemacity.domain.movie.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command

class CreateMovieCMD(val expectedMovieName: String, val cinemaId: String) : Command
