package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.reservation

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfo
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfoId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.valueOf
import java.math.BigDecimal

@Document("reservationInfo")
data class ReservationInfoPersistence(var showId: String, @Indexed(unique = true) var reservationNumber: String,
                                      var reservationCost: BigDecimal, var username: String,
                                      var reservationStatus: String, @Id var id: String) {
    fun toDomainObject(): ReservationInfo {
        val reservationInfoId = ReservationInfoId(id)
        return ReservationInfo(reservationInfoId, reservationNumber, ShowId(showId), reservationCost, valueOf(reservationStatus), username)
    }

    constructor(reservationInfo: ReservationInfo) : this(reservationInfo.showId.value, reservationInfo.reservationNumber,
            reservationInfo.reservationCost, reservationInfo.username, reservationInfo.reservationStatus().name, reservationInfo.id.value
    )
}
