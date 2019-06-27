package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie

import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository

@Repository
class JpaMovieRepository(private val movieRepository: SpringDataMovieRepository) : MovieRepository {

    override fun save(movie: Movie) {
        val moviePersistable = MoviePersistable(movie)
        movieRepository.save(moviePersistable)
    }
}
