package umcs.testcraftmanshipt.cinemacity

import cucumber.api.PendingException
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.PricePolicy
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketAvailabilityQuery
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketBoardQueryRepo
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketsCostQuery
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Reservation(val cinemaRepository: CinemaRepository,
                  val commandHandler: CommandHandler,
                  val showRepository: ShowRepository,
                  val movieRepository: MovieRepository,
                  val ticketBoardRepo: TicketBoardQueryRepo) : En {

    private lateinit var givenShow: Show
    private lateinit var givenCinema: Cinema
    private var ticketCost: Int = 0
    private lateinit var today_date: LocalDateTime

    @Given("^now is \"([^\"]*)\"$")
    fun now_is(date: String) {
        today_date = LocalDateTime.parse(date)
    }

    @Given("^cinema \"([^\"]*)\" in \"([^\"]*)\" is defined$")
    fun cinema_in_is_defined(expectedCinemaName: String, expectedCityName: String) {
        val createCinemaCMD = CreateCinemaCMD(expectedCinemaName, expectedCityName)
        commandHandler.execute(createCinemaCMD)
        givenCinema = cinemaRepository.findByNameAndCityName(expectedCinemaName, expectedCityName)!!
    }

    @Given("^movie \"([^\"]*)\" is defined$")
    fun movie_is_defined(expectedMovieName: String) {
        val createMovieCMD = CreateMovieCMD(expectedMovieName, givenCinema.id)
        commandHandler.execute(createMovieCMD)
    }

    @Given("^show \"([^\"]*)\" in the cinema has (\\d+) PLN ticket cost\$")
    fun show_is_played_in_cinema_in(expectedShowName: String, givenCost: BigDecimal) {
        val createShowCMD = CreateShowCMD(expectedShowName, givenCinema.id, givenCost)
        commandHandler.execute(createShowCMD)
        givenShow = showRepository.findByNameAndCinemaId(expectedShowName, givenCinema.id)
    }

    @When("^I select cinema \"([^\"]*)\" in \"([^\"]*)\"$")
    fun i_select_cinema_in(expectedCinemaName: String, expectedCity: String) {
        assertEquals(givenCinema.cinemaName, expectedCinemaName)
        assertEquals(givenCinema.cityName, expectedCity)
    }

    @When("^I select show at \"([^\"]*)\"$")
    fun i_select_show_at(expectedShowName: String) {
        assertEquals(givenShow.name, expectedShowName)
    }

    @When("^I select (\\d+) tickets with$")
    fun i_select_ticket(ticketCount: Int) {
        val availableStatusResponse = ticketBoardRepo.getResponseFor(TicketAvailabilityQuery(ticketCount, givenShow.id.value))
        assertTrue(availableStatusResponse)
    }

    @Then("^the cost of reservation is (\\d+) PLN$")
    fun the_cost_of_reservation_is_PLN(amount: BigDecimal) {
        ticketBoardRepo.getResponseFor(TicketsCostQuery(mutableListOf(PricePolicy.NORMAL_TICKET, PricePolicy.STUDENT_TICKET)))
    }

    @When("^I select seat (\\d+) in row (\\d+)$")
    @Throws(Exception::class)
    fun i_select_seat_in_row(arg1: Int, arg2: Int) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("^I make a reservation$")
    @Throws(Exception::class)
    fun i_make_a_reservation() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("^the selected seat is reserved for me$")
    @Throws(Exception::class)
    fun the_selected_seat_is_reserved_for_me() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("^I get an email with reservation confirmation$")
    @Throws(Exception::class)
    fun i_get_an_email_with_reservation_confirmation() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("^I select (\\d+) tickets$")
    @Throws(Exception::class)
    fun i_select_tickets(arg1: Int) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Given("^seat (\\d+) in row (\\d+) is reserved for \"([^\"]*)\"$")
    @Throws(Exception::class)
    fun seat_in_row_is_reserved_for(arg1: Int, arg2: Int, arg3: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("^I select movie \"([^\"]*)\"$")
    @Throws(Exception::class)
    fun i_select_movie(arg1: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("^I see that seat (\\d+) in row (\\d+) is already reserved$")
    @Throws(Exception::class)
    fun i_see_that_seat_in_row_is_already_reserved(arg1: Int, arg2: Int) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }
}