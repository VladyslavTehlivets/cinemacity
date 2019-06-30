package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

interface TicketBoardRepository {
    fun findByShowId(domainObjectID: DomainObjectID): TicketBoard
}
