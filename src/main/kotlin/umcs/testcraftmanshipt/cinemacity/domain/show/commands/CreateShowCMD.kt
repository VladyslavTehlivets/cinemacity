package umcs.testcraftmanshipt.cinemacity.domain.show.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import java.math.BigDecimal

class CreateShowCMD(val expectedShowName: String, val cinemaId: DomainObjectID, val cost: BigDecimal) : Command
