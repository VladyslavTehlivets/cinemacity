package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema

@Document("cinema")
data class CinemaPersistable(
        @Id var id: String,
        @Indexed(unique = true) var name: String,
        var cityName: String
) {
    constructor(cinema: Cinema) : this(cinema.id.value, cinema.cinemaName, cinema.cityName)

    fun toDomainObject(): Cinema {
        return Cinema(name, cityName)
    }
}
