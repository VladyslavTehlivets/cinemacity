package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticket

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoard
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketBoardId

@Document("ticketBoard")
data class TicketBoardPersistence(@Id var id: String, @Indexed(unique = true) var showId: String, var ticketPlaces: List<TicketPlacePersistence>) {

    fun toDomainObject(): TicketBoard? {
        return TicketBoard(ShowId(showId), ticketPlaces.map { it.toDomainObject() }, TicketBoardId(id))
    }

    constructor(ticketBoard: TicketBoard) : this(ticketBoard.id.value, ticketBoard.showId.value, ticketBoard.ticketPlaces.map { TicketPlacePersistence(it) })
}
