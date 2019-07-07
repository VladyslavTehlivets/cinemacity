package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import java.math.BigDecimal

//todo unsigned validator
class ShowTicketDiscount(val showId: ShowId, val percentFromShowCost: BigDecimal, val value: String, id: ShowTicketDiscountId) : DomainObject(id)
