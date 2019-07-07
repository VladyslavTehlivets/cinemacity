package umcs.testcraftmanshipt.cinemacity.administrator

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.junit.Assert.assertEquals
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieId
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowId
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ShowTicketsRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketPrice
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.CreateShowTicketDiscountCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.ShowTicketDiscountId
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticketDiscount.ShowTicketDiscountQueryRepo
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticketDiscount.TicketDiscountPriceQuery
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Administration(private val commandHandler: CommandHandler,
                     private val cinemaRepository: CinemaRepository,
                     private val movieRepository: MovieRepository,
                     private val showTicketsRepository: ShowTicketsRepository,
                     private val showRepository: ShowRepository,
                     private val showTicketDiscountQueryRepo: ShowTicketDiscountQueryRepo) : En {

    private lateinit var givenShow: Show
    private lateinit var showId: ShowId
    private lateinit var movieId: MovieId
    private lateinit var cinemaId: DomainObjectID
    private lateinit var showTicketDiscountId: ShowTicketDiscountId

    private val today: LocalDate = LocalDate.now()

    @When("^administrator creates the cinema <cinemaName> in the city <cityName>$")
    fun administratorCreatesTheCinemaInTheCity(cinemaName: String, cityName: String) {
        val createCinemaCMD = CreateCinemaCMD(cinemaName, cityName)
        cinemaId = commandHandler.execute(createCinemaCMD)
    }

    @Then("^cinema <cinemaName> in the city <cityName> is created$")
    fun cinemaInTheCityIsCreated(cinemaName: String, cityName: String) {
        val cinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!
        assertEquals(cinema.cinemaName, cinemaName)
        assertEquals(cinema.cityName, cityName)
    }

    @When("^administrator creates the movie <movieName>$")
    fun administratorCreateTheMovie(movieName: String) {
        val createMovieCMD = CreateMovieCMD(movieName, cinemaId.value)
        val domainObjectID = commandHandler.execute(createMovieCMD)
        movieId = domainObjectID as MovieId
    }

    @Then("^movie <movieName> is created$")
    fun movieIsCreated(movieName: String) {
        movieRepository.findById(movieId)
    }

    @Given("^cinema <cinemaName> in the city <cityName> is defined$")
    fun cinemaIsDefined(cinemaName: String, cityName: String) {
        val cinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!

        assertEquals(cinema.cinemaName, cinemaName)
        assertEquals(cinema.cityName, cityName)
    }

    @And("^movie <movieName> is defined$")
    fun movieIsDefined(movieName: String) {
        val movie = movieRepository.findById(movieId)
        assertEquals(movie.name, movieName)
    }

    @When("^administrator create show <showName> with given movie, one ticket to which costs <ticketPrice> PLN in given cinema at <givenTime>$")
    fun administratorCreateShowWithGivenMovieOneTicketToWhichCostsPLNInGivenCinema(showName: String, ticketCost: Int, givenTime: LocalTime) {
        val createShowCMD = CreateShowCMD(showName, movieId.value, cinemaId.value, BigDecimal(ticketCost), LocalDateTime.of(today, givenTime))
        val domainObjectID = commandHandler.execute(createShowCMD)
        showId = domainObjectID as ShowId
    }

    @Then("^one ticket to this show in given cinema costs <ticketPrice> PLN$")
    fun oneTicketToThisShowInGivenCinemaCostsPLN(expectedCost: TicketPrice) {
        givenShow = showRepository.findById(showId)!!

        assertEquals(givenShow.cost.value, expectedCost)
    }

    @Given("^show <showName> is defined in cinema and normally costs <ticketPrice> PLN$")
    fun showIsDefinedInCinemaAndNormallyCostsPLN(expectedShowName: String, expectedTicketPrice: TicketPrice) {
        assertEquals(givenShow.name, expectedShowName)
        assertEquals(givenShow.cost, expectedTicketPrice)
    }


    @When("^administrator add <ticketDiscount> which costs <percentFromNormalCost> % to show$")
    fun administratorAddWhichCostsPLNToShow(ticketDiscount: String, ticketPrice: TicketPrice, percentFromNormalCost: Int) {
        val createShowTicketDiscountCMD = CreateShowTicketDiscountCMD(showId.value, "STUDENT_TICKET", percentFromNormalCost)
        val domainObjectID = commandHandler.execute(createShowTicketDiscountCMD)
        showTicketDiscountId = domainObjectID as ShowTicketDiscountId
    }

    @Then("^show's <ticketDiscount> costs <ticketPrice> PLN$")
    fun showSCostsPLN(ticketDiscount: String, ticketPrice: TicketPrice) {
        val ticketDiscountPriceQuery = TicketDiscountPriceQuery(ticketDiscount, showId.value)
        val discountsDTO = showTicketDiscountQueryRepo.getResultFor(ticketDiscountPriceQuery)
        val discountInfoDTO = discountsDTO.discountsList.first { it.ticketDiscount == ticketDiscount }

        assertEquals(discountInfoDTO.ticketPrice, ticketPrice)
    }

    @Then("^show's <studentTicketDiscount> costs <studentTicketPrice> PLN$")
    fun showSStudentCostsPLN(studentTicketDiscount: String, ticketPrice: TicketPrice) {
        val ticketDiscountPriceQuery = TicketDiscountPriceQuery(studentTicketDiscount, showId.value)
        val discountsDTO = showTicketDiscountQueryRepo.getResultFor(ticketDiscountPriceQuery)
        val discountInfoDTO = discountsDTO.discountsList.first { it.ticketDiscount == studentTicketDiscount }

        assertEquals(discountInfoDTO.ticketPrice, ticketPrice)
    }
}
