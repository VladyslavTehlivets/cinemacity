package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema

@Document
data class CinemaPersistable(
        var name: String,
        var cityName: String
) {
    constructor(cinema: Cinema) : this(cinema.cinemaName, cinema.cityName)

    fun toDomainObject(): Cinema {
        return Cinema(name, cityName)
    }
}
