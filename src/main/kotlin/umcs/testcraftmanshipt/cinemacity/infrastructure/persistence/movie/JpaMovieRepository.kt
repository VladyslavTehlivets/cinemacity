package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.movie

import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieId
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository

@Repository
class JpaMovieRepository(private val movieRepository: SpringDataMovieRepository) : MovieRepository {
    override fun findById(movieId: MovieId): Movie {
        return movieRepository.findById(movieId.value)
    }

    override fun findByName(movieName: String): Movie {
        return movieRepository.findByName(movieName)
    }

    override fun save(movie: Movie) {
        val moviePersistable = MoviePersistable(movie)
        movieRepository.save(moviePersistable)
    }
}
