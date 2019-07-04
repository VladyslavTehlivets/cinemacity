package umcs.testcraftmanshipt.cinemacity.domain.show.ticketPolicy

//todo implement
interface ShowTicketPolicyRepository {
    fun findByShowId(value: String): List<ShowTicketPolicy>
}
