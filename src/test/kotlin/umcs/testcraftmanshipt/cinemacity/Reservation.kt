package umcs.testcraftmanshipt.cinemacity

import cucumber.api.PendingException
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import java.time.LocalDateTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Reservation(val cinemaRepository: CinemaRepository,
                  val commandHandler: CommandHandler,
                  val showRepository: ShowRepository,
                  val movieRepository: MovieRepository) : En {

    private lateinit var selectedShow: Show
    private lateinit var selectedCinema: Cinema
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
    }

    @Given("^movie \"([^\"]*)\" is defined$")
    fun movie_is_defined(expectedMovieName: String) {
        val createMovieCMD = CreateMovieCMD(expectedMovieName, selectedCinema.id) //todo this will throw exception
        commandHandler.execute(createMovieCMD)
    }

    @Given("^show \"([^\"]*)\" is played in cinema \"([^\"]*)\" in \"([^\"]*)\"$")
    fun show_is_played_in_cinema_in(expectedShowName: String, expectedCinemaName: String, expectedCityName: String) {
        val cinemaId = cinemaRepository.findByNameAndCityName(expectedCinemaName, expectedCityName)!!.id
        val createShowCMD = CreateShowCMD(expectedShowName, cinemaId)
        commandHandler.execute(createShowCMD)
    }

    @Given("^the ticked cost is (\\d+) PLN$")
    fun the_ticked_cost_is_PLN(expectedTicketCost: Int) {
        ticketCost = expectedTicketCost
    }

    @When("^I select cinema \"([^\"]*)\" in \"([^\"]*)\"$")
    fun i_select_cinema_in(expectedCinema: String, expectedCity: String) {
        selectedCinema = cinemaRepository.findByNameAndCityName(expectedCinema, expectedCity)!!
    }

    @When("^I select show at \"([^\"]*)\"$")
    fun i_select_show_at(expectedShowName: String) {
        //todo aaaaaaaaaaaaaaaaaaaa
        selectedShow = showRepository.findByNameAndCinemaId(expectedShowName, selectedCinema.id)
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