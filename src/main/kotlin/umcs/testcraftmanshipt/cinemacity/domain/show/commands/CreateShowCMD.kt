package umcs.testcraftmanshipt.cinemacity.domain.show.commands

import umcs.testcraftmanshipt.cinemacity.domain.Command
import java.math.BigDecimal
import java.time.LocalDateTime

class CreateShowCMD(val showName: String, val movieId: String, val cinemaId: String,
                    val cost: BigDecimal, val dateTime: LocalDateTime) : Command
