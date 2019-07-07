package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticketDiscount

import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.ShowTicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.ShowTicketDiscountRepository

@Repository
class ShowTicketDiscountRepositoryImpl(private val mongoShowTicketDiscountRepo: MongoShowTicketDiscountRepo) : ShowTicketDiscountRepository {
    override fun save(showDefaultDiscount: TicketDiscount) {
        mongoShowTicketDiscountRepo.save(showDefaultDiscount)
    }

    override fun findByShowId(showId: ShowId): List<ShowTicketDiscount> {
        return mongoShowTicketDiscountRepo.findByShowId(showId.value).map { it.toDomainObject() }
    }
}