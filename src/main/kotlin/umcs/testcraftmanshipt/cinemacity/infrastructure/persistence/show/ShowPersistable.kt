package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowCost
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
data class ShowPersistable(
        var name: String,
        var movieId: String,
        var cinemaId: String,
        var cost: BigDecimal,
        var dateTime: LocalDateTime
) {
    constructor(show: Show) : this(show.name, show.movieId.value, show.cinemaId.value, show.cost.value, show.dateTime)

    fun toDomainObject(): Show {
        return Show(name, DomainObjectID(movieId), DomainObjectID(cinemaId), ShowCost(cost), dateTime)
    }
}
