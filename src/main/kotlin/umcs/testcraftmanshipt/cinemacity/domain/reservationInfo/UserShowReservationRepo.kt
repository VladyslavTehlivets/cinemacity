package umcs.testcraftmanshipt.cinemacity.domain.reservationInfo

import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId

interface UserShowReservationRepo {
    fun save(reservationInfo: ReservationInfo)
    fun findByShowId(showId: ShowId): List<ReservationInfo>
    fun findByReservationNumber(reservationNumber: String): ReservationInfo?
    fun findByReservationInfoId(reservationInfoId: ReservationInfoId): ReservationInfo?
}
