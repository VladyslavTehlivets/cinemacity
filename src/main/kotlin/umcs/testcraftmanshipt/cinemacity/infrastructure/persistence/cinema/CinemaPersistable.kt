package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import javax.persistence.Entity

@Entity
data class CinemaPersistable(
        var name: String,
        var cityName: String
) {
    constructor(cinema: Cinema) : this(cinema.cinemaName, cinema.cityName)

    fun toDomainObject(): Cinema {
        return Cinema(name, cityName)
    }
}
