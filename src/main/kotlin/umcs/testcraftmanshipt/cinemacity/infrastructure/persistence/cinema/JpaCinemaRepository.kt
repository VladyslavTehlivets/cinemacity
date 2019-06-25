package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.beans.factory.annotation.Autowired
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import org.springframework.stereotype.Repository

@Repository
class JpaCinemaRepository @Autowired constructor(private val cinemaRepository: SpringDataCinemaRepository) : CinemaRepository {

    override fun findByNameAndCityName(expectedCinema: String, expectedCity: String): Cinema? {
        return cinemaRepository.findByNameAndCityName(expectedCinema, expectedCity)?.toCinemaAggregate()
    }
}
