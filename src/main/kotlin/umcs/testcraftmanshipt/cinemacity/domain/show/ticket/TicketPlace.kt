package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.application.commands.ReserveTicketsCMD
import umcs.testcraftmanshipt.cinemacity.domain.DomainException
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.*

class TicketPlace(val rowIndex: Int, val colIndex: Int) {

    private val statusFlowError: String = "ticketPlace.cancelReservation.statusFlowError"
    private var ticketStatus: ReservationStatus = NOT_RESERVED
    private var reservedForPerson: String? = null
    private var reservationNumber: String? = null

    fun acceptReservation() {
        if (ticketStatus == RESERVED && !reservedForPerson.isNullOrEmpty()) {
            ticketStatus = PAID
        } else {
            throw DomainException("ticketPlace.acceptReservationError")
        }
    }

    fun hasNumber(reserveNumber: String): Boolean {
        return !reservationNumber.isNullOrEmpty() && reservationNumber == reserveNumber
    }

    fun isNotReserved(): Boolean {
        return ticketStatus != NOT_RESERVED
    }

    fun isReserved(): Boolean {
        return ticketStatus == RESERVED
    }

    fun cancelReservation() {
        if (ticketStatus == RESERVED) {
            ticketStatus = NOT_RESERVED
            reservationNumber = null
        } else {
            throw DomainException(statusFlowError)
        }
    }

    fun reserve(command: ReserveTicketsCMD, reserveNumber: String) {
        if (ticketStatus == NOT_RESERVED) {
            ticketStatus = RESERVED
            reservedForPerson = command.username
            reservationNumber = reserveNumber
        } else throw DomainException(statusFlowError)
    }
}
