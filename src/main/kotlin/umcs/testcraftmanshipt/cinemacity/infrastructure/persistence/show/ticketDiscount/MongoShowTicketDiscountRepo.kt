package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticketDiscount

import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(domainClass = TicketDiscountPersistable::class, idClass = String::class)
interface MongoShowTicketDiscountRepo {
    fun findByShowId(showId: String): List<TicketDiscountPersistable>
    fun save(showDefaultDiscount: TicketDiscountPersistable)
}
