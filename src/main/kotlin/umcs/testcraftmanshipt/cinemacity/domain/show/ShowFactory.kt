package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaId
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieId
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD

class ShowFactory {
    companion object {
        @JvmStatic
        fun createShow(createShowCMD: CreateShowCMD): Show {
            val movieId = MovieId(createShowCMD.movieId)
            val cinemaId = CinemaId(createShowCMD.cinemaId)
            return Show(createShowCMD.showName, movieId, cinemaId, ShowCost(createShowCMD.cost), createShowCMD.dateTime)
        }
    }
}
