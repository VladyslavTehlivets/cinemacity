package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaId
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieId

@Document("movie")
data class MoviePersistable(
        @Id var id: String,
        @Indexed(unique = true)
        var name: String,
        var cinemaId: String
) {
    constructor(movie: Movie) : this(movie.id.value, movie.name, movie.cinemaId.value)

    fun toDomainObject(): Movie {
        return Movie(MovieId(id), name, CinemaId(cinemaId))
    }
}
