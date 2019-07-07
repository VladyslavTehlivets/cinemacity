package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticket

import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoard
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardRepository

@Repository
class TicketBoardRepositoryImpl(private val mongoTicketBoardRepository: MongoTicketBoardRepository) : TicketBoardRepository {
    override fun save(ticketBoard: TicketBoard) {
        val ticketBoardPersistence = TicketBoardPersistence(ticketBoard)
        mongoTicketBoardRepository.save(ticketBoardPersistence)
    }

    override fun findByShowId(showId: ShowId): TicketBoard? {
        return mongoTicketBoardRepository.findByShowId(showId.value)?.toDomainObject()
    }
}