package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.reservation

import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfo
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfoId
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.UserShowReservationRepo
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId

@Repository
class UserShowReservationRepoImpl(private val mongoUserShowReservationRepo: MongoUserShowReservationRepo) : UserShowReservationRepo {

    override fun save(reservationInfo: ReservationInfo) {
        val reservationInfoPersistence = ReservationInfoPersistence(reservationInfo)
        mongoUserShowReservationRepo.save(reservationInfoPersistence)
    }

    override fun findByShowId(showId: ShowId): List<ReservationInfo> {
        return mongoUserShowReservationRepo.findByShowId(showId.value).map { it.toDomainObject() }
    }

    override fun findByReservationNumber(reservationNumber: String): ReservationInfo? {
        return mongoUserShowReservationRepo.findByReservationNumber(reservationNumber)?.toDomainObject()
    }

    override fun findByReservationInfoId(reservationInfoId: ReservationInfoId): ReservationInfo? {
        return mongoUserShowReservationRepo.findById(reservationInfoId.value)?.toDomainObject()
    }
}