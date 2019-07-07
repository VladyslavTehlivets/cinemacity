package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount

import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketDiscount

//todo implement
interface ShowTicketDiscountRepository {
    fun findByShowId(showId: ShowId): List<ShowTicketDiscount>
    fun save(showDefaultDiscount: TicketDiscount)
}
