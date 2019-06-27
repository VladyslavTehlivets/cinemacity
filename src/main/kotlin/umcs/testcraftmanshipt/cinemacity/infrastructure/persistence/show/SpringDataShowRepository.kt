package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show

import org.springframework.data.repository.RepositoryDefinition
import umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ShowPersistable

@RepositoryDefinition(domainClass = ShowPersistable::class, idClass = Long::class)
interface SpringDataShowRepository {
    fun save(showPersistable: ShowPersistable)
    fun findByNameAndCinemaId(showName: String, cinemaId: String)
}
