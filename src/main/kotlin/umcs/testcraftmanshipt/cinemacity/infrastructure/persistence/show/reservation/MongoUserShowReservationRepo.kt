package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.reservation

import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(domainClass = ReservationInfoPersistence::class, idClass = String::class)
interface MongoUserShowReservationRepo {
    fun save(reservationInfoPersistence: ReservationInfoPersistence)
    fun findByShowId(showId: String): List<ReservationInfoPersistence>
    fun findByReservationNumber(reservationNumber: String): ReservationInfoPersistence?
    fun findById(reservationInfoId: String): ReservationInfoPersistence?
}
