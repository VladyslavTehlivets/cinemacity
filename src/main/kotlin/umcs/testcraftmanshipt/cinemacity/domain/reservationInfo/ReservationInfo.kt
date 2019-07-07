package umcs.testcraftmanshipt.cinemacity.domain.reservationInfo

import umcs.testcraftmanshipt.cinemacity.domain.DomainException
import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.*
import java.math.BigDecimal

class ReservationInfo(val showId: ShowId, val reservationNumber: String, val reservationCost: BigDecimal,
                      val username: String, private var reservationStatus: ReservationStatus, id: ReservationInfoId) : DomainObject(id) {

    private val errorStatusFlow: String = "reservationInfo.errorStatusFlow"

    constructor(showId: ShowId, reservationNumber: String, reservationCost: BigDecimal, reservationStatus: ReservationStatus, username: String) : this(showId, reservationNumber, reservationCost, username, reservationStatus, ReservationInfoId())
    constructor(id: ReservationInfoId, reservationNumber: String, showId: ShowId, reservationCost: BigDecimal, reservationStatus: ReservationStatus, username: String) : this(showId, reservationNumber, reservationCost, username, reservationStatus, id)

    fun rejectReservation() {
        if (reservationStatus == RESERVED) {
            reservationStatus = NOT_RESERVED
        } else {
            throw DomainException(errorStatusFlow)
        }
    }

    fun isReserved(): Boolean {
        return reservationStatus == RESERVED
    }

    fun acceptReservation() {
        if (reservationStatus == RESERVED) {
            reservationStatus = PAID
        } else throw DomainException(errorStatusFlow)
    }

    fun reservationStatus(): ReservationStatus {
        return reservationStatus
    }
}
