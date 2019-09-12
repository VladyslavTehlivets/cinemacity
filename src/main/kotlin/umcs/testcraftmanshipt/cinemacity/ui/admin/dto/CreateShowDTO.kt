package umcs.testcraftmanshipt.cinemacity.ui.admin.dto

import java.math.BigDecimal
import java.time.LocalDateTime

class CreateShowDTO(val showName: String, val movieId: String, val cinemaId: String,
                    val cost: BigDecimal, val dateTime: LocalDateTime)