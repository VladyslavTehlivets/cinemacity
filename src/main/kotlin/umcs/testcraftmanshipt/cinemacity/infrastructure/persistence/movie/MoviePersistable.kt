package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import javax.persistence.Entity

@Entity
data class MoviePersistable(
        var id: String,
        var name: String,
        var cinemaId: String
) {
    constructor(movie: Movie) : this(movie.id.value, movie.name, movie.cinemaId.value)

    fun toDomainObject(): Movie {
        return Movie(DomainObjectID(id), name, DomainObjectID(cinemaId))
    }
}
