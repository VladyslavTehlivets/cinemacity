package umcs.testcraftmanshipt.cinemacity.domain.movie

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import java.util.*

class MovieId(value: String = UUID.randomUUID().toString()) : DomainObjectID(value)
