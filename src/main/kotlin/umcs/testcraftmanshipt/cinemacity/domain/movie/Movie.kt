package umcs.testcraftmanshipt.cinemacity.domain.movie

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

class Movie(val name: String, val cinemaId: DomainObjectID) : DomainObject()
