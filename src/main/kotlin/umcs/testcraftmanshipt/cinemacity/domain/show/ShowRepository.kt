package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID

interface ShowRepository {
    fun save(show: Show)
    fun findByNameAndCinemaId(expectedShowName: String, id: DomainObjectID): Show?
    fun findById(showId: ShowId): Show?
}
