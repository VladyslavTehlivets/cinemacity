package umcs.testcraftmanshipt.cinemacity.administrator

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert.assertEquals
import org.springframework.beans.factory.annotation.Autowired
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
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.TicketPrice
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.TicketDiscountId
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.commands.CreateShowTicketDiscountCMD
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticketDiscount.ShowTicketDiscountQueryRepo
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticketDiscount.TicketDiscountPriceQuery
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Administration {

    @Autowired
    private lateinit var commandHandler: CommandHandler
    @Autowired
    private lateinit var cinemaRepository: CinemaRepository
    @Autowired
    private lateinit var movieRepository: MovieRepository
    @Autowired
    private lateinit var showRepository: ShowRepository
    @Autowired
    private lateinit var showTicketDiscountQueryRepo: ShowTicketDiscountQueryRepo

    private lateinit var givenShow: Show
    private lateinit var showId: ShowId
    private lateinit var movieId: MovieId
    private lateinit var cinemaId: DomainObjectID
    private lateinit var ticketDiscountId: TicketDiscountId

    private val today: LocalDate = LocalDate.now()

    @When("administrator creates the cinema {string} in the city {string}")
    fun administratorCreatesTheCinemaInTheCity(cinemaName: String, cityName: String) {
        val createCinemaCMD = CreateCinemaCMD(cinemaName, cityName)
        cinemaId = commandHandler.execute(createCinemaCMD)
    }

    @Then("cinema {string} in the city {string} is created")
    fun cinemaInTheCityIsCreated(cinemaName: String, cityName: String) {
        val cinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!
        assertEquals(cinema.cinemaName, cinemaName)
        assertEquals(cinema.cityName, cityName)
    }

    @When("administrator creates the movie {string}")
    fun administratorCreateTheMovie(movieName: String) {
        val createMovieCMD = CreateMovieCMD(movieName, cinemaId.value)
        val domainObjectID = commandHandler.execute(createMovieCMD)
        movieId = domainObjectID as MovieId
    }

    @Then("movie {string} is created")
    fun movieIsCreated(movieName: String) {
        movieRepository.findById(movieId)
    }

    @Given("cinema {string} in the city {string} is defined")
    fun cinemaIsDefined(cinemaName: String, cityName: String) {
        val cinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!

        assertEquals(cinema.cinemaName, cinemaName)
        assertEquals(cinema.cityName, cityName)
    }

    @And("movie with {string} is defined")
    fun movieIsDefined(movieName: String) {
        val movie = movieRepository.findById(movieId)
        assertEquals(movie.name, movieName)
    }

    @When("administrator create show {string} with given movie, one ticket to which costs {int} PLN in given cinema at {string}")
    fun administratorCreateShowWithGivenMovieOneTicketToWhichCostsPLNInGivenCinema(showName: String, ticketCost: Int, givenTime: LocalTime) {
        val createShowCMD = CreateShowCMD(showName, movieId.value, cinemaId.value, BigDecimal(ticketCost), LocalDateTime.of(today, givenTime))
        val domainObjectID = commandHandler.execute(createShowCMD)
        showId = domainObjectID as ShowId
    }

    @Then("one ticket to this show in given cinema costs {int} PLN")
    fun oneTicketToThisShowInGivenCinemaCostsPLN(expectedCost: TicketPrice) {
        givenShow = showRepository.findById(showId)!!

        assertEquals(givenShow.cost.value, expectedCost)
    }

    @Given("show {string} is defined in cinema and normally costs {int} PLN")
    fun showIsDefinedInCinemaAndNormallyCostsPLN(expectedShowName: String, expectedTicketPrice: Int) {
        assertEquals(givenShow.name, expectedShowName)
        assertEquals(givenShow.cost.value, BigDecimal(expectedTicketPrice))
    }


    @When("administrator add {string} which costs {int} % to show")
    fun administratorAddWhichCostsPLNToShow(ticketDiscount: String, ticketPrice: TicketPrice, percentFromNormalCost: Int) {
        val createShowTicketDiscountCMD = CreateShowTicketDiscountCMD(showId.value, "STUDENT_TICKET", percentFromNormalCost.toDouble())
        val domainObjectID = commandHandler.execute(createShowTicketDiscountCMD)
        ticketDiscountId = domainObjectID as TicketDiscountId
    }

    @Then("show's {string} costs {int} PLN")
    fun showSCostsPLN(ticketDiscount: String, ticketPrice: TicketPrice) {
        val ticketDiscountPriceQuery = TicketDiscountPriceQuery(ticketDiscount, showId.value)
        val discountsDTO = showTicketDiscountQueryRepo.getResultFor(ticketDiscountPriceQuery)
        val discountInfoDTO = discountsDTO.discountsList.first { it.ticketDiscount == ticketDiscount }

        assertEquals(discountInfoDTO.ticketPrice, ticketPrice)
    }

    @Then("show's {string} costs {string} PLN")
    fun showSStudentCostsPLN(studentTicketDiscount: String, ticketPrice: TicketPrice) {
        val ticketDiscountPriceQuery = TicketDiscountPriceQuery(studentTicketDiscount, showId.value)
        val discountsDTO = showTicketDiscountQueryRepo.getResultFor(ticketDiscountPriceQuery)
        val discountInfoDTO = discountsDTO.discountsList.first { it.ticketDiscount == studentTicketDiscount }

        assertEquals(discountInfoDTO.ticketPrice, ticketPrice)
    }
}
