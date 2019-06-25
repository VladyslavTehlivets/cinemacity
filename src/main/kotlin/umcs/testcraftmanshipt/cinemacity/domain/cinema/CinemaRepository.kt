package umcs.testcraftmanshipt.cinemacity.domain.cinema

interface CinemaRepository {
    fun findByNameAndCityName(expectedCinema: String, expectedCity: String): Cinema?
}
