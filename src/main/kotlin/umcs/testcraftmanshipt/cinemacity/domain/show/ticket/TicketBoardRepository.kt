package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId

interface TicketBoardRepository {
    fun findByShowId(showId: ShowId): TicketBoard?
    fun save(ticketBoard: TicketBoard)
}
