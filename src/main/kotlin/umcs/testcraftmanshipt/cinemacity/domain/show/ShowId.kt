package umcs.testcraftmanshipt.cinemacity.domain.show

import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import java.util.*

class ShowId(value: String = UUID.randomUUID().toString()): DomainObjectID(value)
