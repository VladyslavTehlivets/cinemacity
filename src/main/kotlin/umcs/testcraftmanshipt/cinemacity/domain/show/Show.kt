package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaId
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieId
import java.time.LocalDateTime

class Show private constructor(val name: String, val movieId: MovieId, val cinemaId: CinemaId,
                               val cost: ShowCost, val dateTime: LocalDateTime, id: ShowId) : DomainObject(id) {

    constructor(name: String, movieId: MovieId, cinemaId: CinemaId,
                cost: ShowCost, dateTime: LocalDateTime) : this(name, movieId, cinemaId, cost, dateTime, ShowId())

    constructor(id: ShowId, name: String, movieId: MovieId, cinemaId: CinemaId,
                cost: ShowCost, dateTime: LocalDateTime) : this(name, movieId, cinemaId, cost, dateTime, id)
}
