package umcs.testcraftmanshipt.cinemacity.reservation

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import umcs.testcraftmanshipt.cinemacity.CucumberStepDefinitions
import umcs.testcraftmanshipt.cinemacity.application.commands.ReserveTicketsCMD
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.Reservation
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus.RESERVED
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.*
import java.lang.Integer.parseInt
import java.math.BigDecimal
import java.time.LocalDateTime

class ReservationSteps @Autowired constructor() : CucumberStepDefinitions() {

    @Autowired
    private lateinit var cinemaRepository: CinemaRepository
    @Autowired
    private lateinit var commandHandler: CommandHandler
    @Autowired
    private lateinit var showRepository: ShowRepository
    @Autowired
    private lateinit var movieRepository: MovieRepository
    @Autowired
    private lateinit var ticketBoardRepo: TicketBoardQueryRepo

    private lateinit var givenMovie: Movie
    private lateinit var selectedPlaces: MutableList<Reservation>
    private lateinit var givenShow: Show
    private lateinit var givenCinema: Cinema

    @Given("cinema {string} in {string} is defined")
    fun cinema_in_is_defined(cinemaName: String, cityName: String) {
        val createCinemaCMD = CreateCinemaCMD(cinemaName, cityName)
        commandHandler.execute(createCinemaCMD)
        givenCinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!
    }

    @Given("movie {string} is defined")
    fun movie_is_defined(movieName: String) {
        val createMovieCMD = CreateMovieCMD(movieName, givenCinema.id.value)
        commandHandler.execute(createMovieCMD)
        givenMovie = movieRepository.findByName(movieName)
    }

    @Given("show {string} in the cinema has {int} PLN ticket cost and played at {string}")
    fun show_is_played_in_cinema_in(expectedShowName: String, givenCost: BigDecimal, dateTime: LocalDateTime) {
        val createShowCMD = CreateShowCMD(expectedShowName, givenMovie.id.value, givenCinema.id.value, givenCost, dateTime)
        commandHandler.execute(createShowCMD)
        givenShow = showRepository.findByNameAndCinemaId(expectedShowName, givenCinema.id)!!

        assertEquals(givenShow.name, expectedShowName)
        assertEquals(givenShow.cost.value, givenCost)
        assertEquals(givenShow.dateTime, dateTime)
    }

    @When("I select a cinema {string} in {string}")
    fun i_select_cinema_in(cinemaName: String, cityName: String) {
        assertEquals(givenCinema.cinemaName, cinemaName)
        assertEquals(givenCinema.cityName, cityName)
    }

    @When("I select show {string} at {string}")
    fun i_select_show_at(showName: String, dateTime: LocalDateTime) {
        assertEquals(givenShow.name, showName)
        assertEquals(givenShow.dateTime, dateTime)
    }

    @And("I select seat {int} in row {int}")
    fun i_select_seat_in_row(columnSeatPlace: Int, rowSeatPlace: Int) {
        selectedPlaces = mutableListOf(Reservation(columnSeatPlace, rowSeatPlace))
    }

    @And("I select seats {string} in row {int}")
    fun i_select_two_seats_in_row(columnsToSplit: String, rowSeatPlace: Int) {
        val reservationList = columnsToSplit.split(",").map { Reservation(parseInt(it), rowSeatPlace) }.toMutableList()
        selectedPlaces = reservationList
    }

    //todo to delete
    @When("I select (\\d+) tickets with")
    fun i_select_ticket(ticketCount: Int) {
        val availableStatusResponse = ticketBoardRepo.getResponseFor(TicketAvailabilityQuery(ticketCount, givenShow.id.value))
        assertTrue(availableStatusResponse)
    }

    @Then("the cost of reservation is {int} PLN")
    fun the_cost_of_reservation_is_PLN(amount: BigDecimal) {
        val ticketCostDTO = ticketBoardRepo.getResponseFor(TicketsCostQuery(mutableListOf("NORMAL_TICKET", "STUDENT_TICKET")))
        assertEquals(ticketCostDTO.cost, amount)
    }

    @When("I select seat {int} in row {int} with ticket discount {string}")
    fun i_select_seat_in_row_with_ticket_discount(columnSeatPlace: Int, rowSeatPlace: Int, ticketDiscount: String) {
        selectedPlaces = mutableListOf(Reservation(columnSeatPlace, rowSeatPlace, ticketDiscount))
    }

    @WithMockUser("vladyslav")
    @When("I make a reservation")
    fun i_make_a_reservation() {
        val reserveTicketsCMD = ReserveTicketsCMD(givenShow.id.value, "vladyslav", selectedPlaces)
        commandHandler.execute(reserveTicketsCMD)
    }

    @Then("the selected seat is reserved for me")
    fun the_selected_seat_is_reserved_for_me() {
        val username = "vladyslav"
        val ticketsStatuses = ticketBoardRepo.getResponseFor(TicketsStatusQuery(username))

        assertTrue(ticketsStatuses.all { it.reservationStatus == RESERVED.name })
        assertTrue(ticketsStatuses.all { it.reservedBy == username })
    }

    @Then("I get an email with reservation confirmation")
    fun i_get_an_email_with_reservation_confirmation() {
        //todo mock
    }

    @Given("seat {int} in row {int} is reserved for {string}")
    fun seat_in_row_is_reserved_for(seatColumn: Int, seatRow: Int, userName: String) {
        val reservationStatusForUser = ticketBoardRepo.getResponseFor(TicketsStatusQuery(userName))
        val ticketStatusDTO = reservationStatusForUser.first { it.columnPlace == seatColumn && it.rowPlace == seatRow }
        assertEquals(ticketStatusDTO.reservationStatus, RESERVED.name)
        assertEquals(ticketStatusDTO.reservedBy, userName)
    }

    @Then("I see that seat {int} in row {int} is not available for reservation")
    fun i_see_that_seat_in_row_is_not_available_for_reservation(rowSeatPlace: Int, columnSeatPlace: Int) {
        val tickets = ticketBoardRepo.getResponseFor(AvailableTicketsQuery(givenShow.id.value))
        assertTrue(tickets.none { it.seatRow == rowSeatPlace && it.seatColumn == columnSeatPlace })
    }
}