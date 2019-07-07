package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticket

import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(domainClass = TicketBoardPersistence::class, idClass = String::class)
interface MongoTicketBoardRepository {
    fun save(ticketBoardPersistence: TicketBoardPersistence)
    fun findByShowId(value: String): TicketBoardPersistence?
}
