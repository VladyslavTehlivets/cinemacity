package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import java.util.*

class TicketBoardId(value: String = UUID.randomUUID().toString()) : DomainObjectID(value)
