package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.data.repository.RepositoryDefinition
import umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie.MoviePersistable

@RepositoryDefinition(domainClass = MoviePersistable::class, idClass = Long::class)
interface SpringDataCinemaRepository {
    fun findByNameAndCityName(expectedCinema: String, expectedCity: String): MoviePersistable?
    fun save(cinemaPersistable: MoviePersistable)
}
