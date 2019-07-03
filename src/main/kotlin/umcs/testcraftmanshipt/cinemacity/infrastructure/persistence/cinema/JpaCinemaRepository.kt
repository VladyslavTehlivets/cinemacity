package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository

@Repository
class JpaCinemaRepository @Autowired constructor(private val cinemaRepository: SpringDataCinemaRepository) : CinemaRepository {

    override fun save(cinema: Cinema) {
        val cinemaPersistable = CinemaPersistable(cinema)
        cinemaRepository.save(cinemaPersistable)
    }

    override fun findByNameAndCityName(expectedCinema: String, expectedCity: String): Cinema? {
        return cinemaRepository.findByNameAndCityName(expectedCinema, expectedCity)?.toDomainObject()
    }
}
