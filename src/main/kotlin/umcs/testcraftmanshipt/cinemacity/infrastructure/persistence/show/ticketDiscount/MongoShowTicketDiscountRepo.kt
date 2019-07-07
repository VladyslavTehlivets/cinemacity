package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticketDiscount

import org.springframework.data.repository.RepositoryDefinition
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketDiscount

@RepositoryDefinition(domainClass = ShowTicketDiscountPersistence::class, idClass = String::class)
interface MongoShowTicketDiscountRepo {
    fun findByShowId(showId: String): List<ShowTicketDiscountPersistence>
    fun save(showDefaultDiscount: TicketDiscount)
}
