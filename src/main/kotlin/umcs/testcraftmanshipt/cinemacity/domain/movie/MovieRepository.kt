package umcs.testcraftmanshipt.cinemacity.domain.movie

interface MovieRepository {
    fun save(movie: Movie)
    fun findByName(movieName: String): Movie
    fun findById(movieId: MovieId): Movie
}
