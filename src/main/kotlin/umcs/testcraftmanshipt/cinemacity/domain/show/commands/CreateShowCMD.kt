package umcs.testcraftmanshipt.cinemacity.domain.show.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

class CreateShowCMD(val expectedShowName: String, val cinemaId: DomainObjectID) : Command
