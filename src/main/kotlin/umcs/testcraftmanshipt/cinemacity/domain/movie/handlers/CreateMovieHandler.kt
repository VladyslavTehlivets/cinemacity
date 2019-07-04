package umcs.testcraftmanshipt.cinemacity.domain.movie.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD

@Service
class CreateMovieHandler(private val movieRepository: MovieRepository) : Handler<CreateMovieCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateMovieCMD::class
    }

    override fun handle(command: CreateMovieCMD): DomainObjectID {
        val movie = Movie(command.expectedMovieName, command.cinemaId)
        movieRepository.save(movie)
        return movie.id
    }
}
