package umcs.testcraftmanshipt.cinemacity.domain.cinema

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import java.util.*

class CinemaId(value: String = UUID.randomUUID().toString()) : DomainObjectID(value)
