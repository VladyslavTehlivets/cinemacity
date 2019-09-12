package umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscountId
import java.math.BigDecimal

class TicketDiscount(val showId: ShowId, val discountName: String, val discountCost: BigDecimal, id: TicketDiscountId): DomainObject(id) {

    companion object {
        const val BASE_NORMALLY_TICKET = "NORMALLY_TICKET"
    }

    constructor(showId: ShowId, discountName: String, discountCost: BigDecimal): this(TicketDiscountId(), showId, discountName, discountCost)

    constructor(id: TicketDiscountId, showId: ShowId, discountName: String, discountCost: BigDecimal): this(showId, discountName, discountCost, id)
}
