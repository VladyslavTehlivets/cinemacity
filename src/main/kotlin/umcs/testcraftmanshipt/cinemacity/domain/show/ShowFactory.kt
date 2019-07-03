package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardFactory.Companion.createTicketBoard

class ShowFactory {
    companion object {
        @JvmStatic
        fun createShow(createShowCMD: CreateShowCMD): Show {
            val movieId = DomainObjectID(createShowCMD.movieId)
            val cinemaId = DomainObjectID(createShowCMD.cinemaId)
            val show = Show(createShowCMD.showName, movieId, cinemaId, ShowCost(createShowCMD.cost), createShowCMD.dateTime)
            createTicketBoard(show.id) //todo create in repo
            return show
        }
    }
}
