package umcs.testcraftmanshipt.cinemacity

import cucumber.api.PendingException
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Reservation @Autowired constructor(val cinemaRepository: CinemaRepository) : En {

    private var ticketCost: Int = 0
    private lateinit var today_date: String
    private lateinit var cinema: Cinema
    private lateinit var movie: Movie
    private lateinit var show: Show

    @Given("^now is \"([^\"]*)\"$")
    @Throws(Exception::class)
    fun now_is(date: String) {
        today_date = date
    }

    @Given("^cinema \"([^\"]*)\" in \"([^\"]*)\" is defined$")
    @Throws(Exception::class)
    fun cinema_in_is_defined(expectedCinemaName: String, expectedCityName: String) {
        cinema = Cinema(expectedCinemaName, expectedCityName)
    }

    @Given("^movie \"([^\"]*)\" is defined$")
    @Throws(Exception::class)
    fun movie_is_defined(expectedMovieName: String) {
        movie = Movie(expectedMovieName)
    }

    @Given("^show \"([^\"]*)\" is played in cinema \"([^\"]*)\" in \"([^\"]*)\"$")
    @Throws(Exception::class)
    fun show_is_played_in_cinema_in(expectedShowName: String, expectedCinemaName: String, expectedCityName: String) {
        show = Show(expectedShowName, cinema)
    }

    @Given("^the ticked cost is (\\d+) PLN$")
    @Throws(Exception::class)
    fun the_ticked_cost_is_PLN(expectedTicketCost: Int) {
        ticketCost = expectedTicketCost
    }

    @When("^I select cinema \"([^\"]*)\" in \"([^\"]*)\"$")
    @Throws(Exception::class)
    fun i_select_cinema_in(expectedCinema: String, expectedCity: String) {
        val selectedCinema = cinemaRepository.findByNameAndCityName(expectedCinema, expectedCity)
    }

    @When("^I select show at \"([^\"]*)\"$")
    @Throws(Exception::class)
    fun i_select_show_at(arg1: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("^I select (\\d+) ticket$")
    @Throws(Exception::class)
    fun i_select_ticket(arg1: Int) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("^the cost of reservation is (\\d+) PLN$")
    @Throws(Exception::class)
    fun the_cost_of_reservation_is_PLN(arg1: Int) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
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