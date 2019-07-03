package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.cinema

import org.springframework.data.repository.RepositoryDefinition

@RepositoryDefinition(domainClass = CinemaPersistable::class, idClass = Long::class)
interface SpringDataCinemaRepository {
    fun findByNameAndCityName(expectedCinema: String, expectedCity: String): CinemaPersistable?
    fun save(cinemaPersistable: CinemaPersistable)
}
