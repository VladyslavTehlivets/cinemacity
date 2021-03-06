package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaId
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieId
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowCost
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import java.math.BigDecimal
import java.time.LocalDateTime

@Document("show")
data class ShowPersistable(
        @Id var id: String,
        @Indexed(unique = true) var name: String,
        var movieId: String,
        var cinemaId: String,
        var cost: BigDecimal,
        var dateTime: LocalDateTime
) {
    constructor(show: Show) : this(show.id.value, show.name, show.movieId.value, show.cinemaId.value, show.cost.value, show.dateTime)

    fun toDomainObject(): Show {
        return Show(ShowId(id), name, MovieId(movieId), CinemaId(cinemaId), ShowCost(cost), dateTime)
    }
}
