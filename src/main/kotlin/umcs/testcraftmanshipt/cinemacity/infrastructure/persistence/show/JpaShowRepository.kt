package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository

@Repository
class JpaShowRepository @Autowired constructor(private val showRepository: SpringDataShowRepository) : ShowRepository {
    override fun findById(showId: ShowId): Show? {
        return showRepository.findById(showId.value)?.toDomainObject()
    }

    override fun save(show: Show) {
        val showPersistable = ShowPersistable(show)
        showRepository.save(showPersistable)
    }

    override fun findByNameAndCinemaId(expectedShowName: String, id: DomainObjectID): Show? {
        return showRepository.findByNameAndCinemaId(expectedShowName, id.value)?.toDomainObject()
    }
}
