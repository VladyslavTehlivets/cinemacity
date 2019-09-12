package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticketDiscount

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscount
import java.math.BigDecimal

@Document("ticketDiscount")
data class TicketDiscountPersistable(var showId: String, var cost: BigDecimal,
                                     @Indexed(unique = true) var value: String, @Id var id: String) {
    fun toDomainObject(): TicketDiscount {
        return TicketDiscount(ShowId(showId), value, cost)
    }

    constructor(ticketDiscount: TicketDiscount) : this(ticketDiscount.showId.value, ticketDiscount.discountCost,
            ticketDiscount.discountName, ticketDiscount.id.value)
}
