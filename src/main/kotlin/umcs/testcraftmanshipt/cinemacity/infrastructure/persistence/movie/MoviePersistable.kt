package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie

import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import javax.persistence.Entity

@Entity
data class MoviePersistable(
        var name: String,
        var cinemaId: String
) {
    constructor(movie: Movie) : this(movie.name, movie.cinemaId.value)

    fun toDomainObject(): Movie {
        return Movie(name, DomainObjectID(cinemaId))
    }
}
