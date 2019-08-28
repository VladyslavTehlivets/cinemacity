package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticket

import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketPlace
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketPlaceState

@Document("ticket_place")
class TicketPlacePersistence(
        var reservationStatus: String,
        var reservedForPerson: String?,
        var reservationNumber: String?,
        var rowIndex: Int,
        var colIndex: Int) {
    fun toDomainObject(): TicketPlace {
        val ticketPlaceState = TicketPlaceState(reservationStatus, reservedForPerson, reservationNumber, rowIndex, colIndex)
        return TicketPlace(ticketPlaceState)
    }

    constructor(ticketPlace: TicketPlace) : this(
            ticketPlace.getTicketStatus().name,
            ticketPlace.getReservedForPerson(),
            ticketPlace.getReservationNumber(),
            ticketPlace.rowIndex,
            ticketPlace.colIndex
    )
}
