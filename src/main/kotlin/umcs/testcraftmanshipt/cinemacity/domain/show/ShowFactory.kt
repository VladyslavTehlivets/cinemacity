package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoard

class ShowFactory {
    companion object {
        @JvmStatic
        fun createShow(expectedShowName: String, cinemaId: DomainObjectID): Show {
            val ticketBoard = TicketBoard() //todo create in repo
            return Show(ticketBoard.id, expectedShowName, cinemaId)
        }
    }
}
