package umcs.testcraftmanshipt.cinemacity.domain.show.ticket

import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import java.math.BigDecimal

class TicketDiscount(val showId: ShowId, val discountName: String, val discountPercent: BigDecimal)
