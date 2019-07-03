package umcs.testcraftmanshipt.cinemacity

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.junit.Assert.assertEquals
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketPrice
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Administration(private val commandHandler: CommandHandler, private val cinemaRepository: CinemaRepository) : En {

    @When("^administrator creates the cinema <cinemaName> in the city <cityName>$")
    fun administratorCreatesTheCinemaInTheCity(cinemaName: String, cityName: String) {
        val createCinemaCMD = CreateCinemaCMD(cinemaName, cityName)
        commandHandler.execute(createCinemaCMD)
    }

    @Then("^cinema <cinemaName> in the city <cityName> is created$")
    fun cinemaInTheCityIsCreated(cinemaName: String, cityName: String) {
        val cinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!
        assertEquals(cinema.cinemaName, cinemaName)
        assertEquals(cinema.cityName, cityName)
    }

    @When("^administrator creates the movie <movieName>$")
    fun administratorCreateTheMovie(movieName: String) {

    }

    @Then("^movie <movieName> is created$")
    fun movieIsCreated(movieName: String) {

    }

    @Given("^cinema <cinemaName> in the city <cityName> is defined$")
    fun cinemaIsDefined(cinemaName: String, cityName: String) {

    }

    @And("^movie <movieName> is defined$")
    fun movieIsDefined(movieName: String) {

    }

    @When("^administrator create show <showName> with given movie, one ticket to which costs (\\d+) PLN in given cinema$")
    fun administratorCreateShowWithGivenmovieOneTicketToWhichCostsPLNInGivenCinema(showName: String, ticketCost: Int) {

    }

    @Then("^one ticket to this show in given cinema costs (\\d+) PLN$")
    fun oneTicketToThisShowInGivenCinemaCostsPLN(expectedCost: TicketPrice) {

    }

    @Given("^show <showName> is defined in cinema and normally costs (\\d+) PLN$")
    fun showIsDefinedInCinemaAndNormallyCostsPLN(showName: String, ticketPrice: TicketPrice) {

    }

    @When("^administrator add <ticketPolicy> which costs (\\d+) PLN to show$")
    fun administratorAddWhichCostsPLNToShow(ticketPolicy: String, ticketPrice: TicketPrice) {

    }

    @Then("^show's <ticketPolicy> costs (\\d+) PLN$")
    fun showSCostsPLN(ticketPolicy: String, ticketPrice: TicketPrice) {

    }

    @Then("^show's <studentTicketPolicy> costs (\\d+) PLN$")
    fun showSStudentCostsPLN(studentTicketPolicy: String, ticketPrice: TicketPrice) {

    }
}
