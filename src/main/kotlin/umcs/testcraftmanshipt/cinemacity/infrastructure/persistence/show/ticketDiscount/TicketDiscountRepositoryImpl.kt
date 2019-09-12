package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticketDiscount

import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscountRepository

@Repository
class TicketDiscountRepositoryImpl(private val mongoShowTicketDiscountRepo: MongoShowTicketDiscountRepo) : TicketDiscountRepository {
    override fun save(showDefaultDiscount: TicketDiscount) {
        mongoShowTicketDiscountRepo.save(TicketDiscountPersistable(showDefaultDiscount))
    }

    override fun findByShowId(showId: ShowId): List<TicketDiscount> {
        return mongoShowTicketDiscountRepo.findByShowId(showId.value).map { it.toDomainObject() }
    }
}