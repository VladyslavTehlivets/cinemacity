package umcs.testcraftmanshipt.cinemacity.domain.movie

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaId

class Movie private constructor(val name: String, val cinemaId: CinemaId, id: MovieId) : DomainObject(id) {

    constructor(name: String, cinemaId: CinemaId) : this(MovieId(), name, cinemaId)

    constructor(id: MovieId, name: String, cinemaId: CinemaId) : this(name, cinemaId, id)
}
