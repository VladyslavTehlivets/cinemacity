package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie.MoviePersistable
import umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie.SpringDataMovieRepository

@Repository
class JpaCinemaRepository @Autowired constructor(private val cinemaRepository: SpringDataMovieRepository) : CinemaRepository {

    override fun save(cinema: Cinema) {
        val cinemaPersistable = MoviePersistable(cinema)
        cinemaRepository.save(cinemaPersistable)
    }

    override fun findByNameAndCityName(expectedCinema: String, expectedCity: String): Cinema? {
        return cinemaRepository.findByNameAndCityName(expectedCinema, expectedCity)?.toDomainObject()
    }
}
