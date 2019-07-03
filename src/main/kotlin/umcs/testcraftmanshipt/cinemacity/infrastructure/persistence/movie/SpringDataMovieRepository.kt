package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie

import org.springframework.data.repository.RepositoryDefinition
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie.MoviePersistable

@RepositoryDefinition(domainClass = MoviePersistable::class, idClass = Long::class)
interface SpringDataMovieRepository {
    fun save(cinemaPersistable: MoviePersistable)
    fun findByName(movieName: String): Movie
}
