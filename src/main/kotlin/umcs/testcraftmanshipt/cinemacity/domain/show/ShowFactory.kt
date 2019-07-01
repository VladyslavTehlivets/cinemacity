package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardFactory.Companion.createTicketBoard
import java.math.BigDecimal

class ShowFactory {
    companion object {
        @JvmStatic
        fun createShow(expectedShowName: String, cinemaId: DomainObjectID, cost: BigDecimal): Show {
            val show = Show(expectedShowName, cinemaId, ShowCost(cost))
            createTicketBoard(show.id) //todo create in repo
            return show
        }
    }
}
