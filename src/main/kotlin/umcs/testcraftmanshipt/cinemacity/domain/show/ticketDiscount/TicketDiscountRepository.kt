package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount

import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId

interface TicketDiscountRepository {
    fun findByShowId(showId: ShowId): List<TicketDiscount>
    fun save(showDefaultDiscount: TicketDiscount)
}
