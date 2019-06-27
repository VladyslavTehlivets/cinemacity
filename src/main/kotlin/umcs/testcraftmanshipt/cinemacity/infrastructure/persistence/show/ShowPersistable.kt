package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show

import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import javax.persistence.Entity

@Entity
data class ShowPersistable(
        var name: String,
        var cinemaId: String
) {
    constructor(show: Show) : this(show.name, show.cinemaId.value)

    fun toDomainObject(): Show {
        return Show(name, DomainObjectID(cinemaId))
    }
}
