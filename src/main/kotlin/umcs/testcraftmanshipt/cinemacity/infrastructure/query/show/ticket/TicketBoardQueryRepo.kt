package umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket

import org.springframework.stereotype.Repository

@Repository
class TicketBoardQueryRepo {
    fun getResponseFor(ticketAvailabilityQuery: TicketAvailabilityQuery): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getResponseFor(ticketsCostQuery: TicketsCostQuery): TicketCostDTO {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getResponseFor(ticketsStatusQuery: TicketsStatusQuery): List<TicketStatusDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getResponseFor(availableTicketsQuery: AvailableTicketsQuery): List<TicketDTO> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
