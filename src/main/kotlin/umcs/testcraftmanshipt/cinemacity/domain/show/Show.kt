package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import java.time.LocalDateTime

class Show(val name: String, val movieId: DomainObjectID, val cinemaId: DomainObjectID,
           val cost: ShowCost, val dateTime: LocalDateTime) : DomainObject()
