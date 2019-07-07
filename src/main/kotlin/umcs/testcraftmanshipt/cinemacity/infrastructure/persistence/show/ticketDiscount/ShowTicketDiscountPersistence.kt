package umcs.testcraftmanshipt.cinemacity.infrastructure.persistence.show.ticketDiscount

import org.springframework.data.mongodb.core.mapping.Document
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.ShowTicketDiscount
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.ShowTicketDiscountId
import java.math.BigDecimal

@Document("showTicketDiscountPersistence")
data class ShowTicketDiscountPersistence(var showId: String, var percentFromShowCost: BigDecimal,
                                    var value: String, var id: String) {
    fun toDomainObject(): ShowTicketDiscount {
        return ShowTicketDiscount(ShowId(showId), percentFromShowCost, value, ShowTicketDiscountId(id))
    }

}
