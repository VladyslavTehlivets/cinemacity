package umcs.testcraftmanshipt.cinemacity

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import org.junit.BeforeClass
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime.of
import java.time.LocalTime.of

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Cashier(private val commandHandler: CommandHandler, private val cinemaRepository: CinemaRepository,
              private val showRepository: ShowRepository,
              private val movieRepository: MovieRepository) : En {

    private lateinit var givenMovie: Movie
    private lateinit var today: LocalDate
    private lateinit var givenShow: Show
    private lateinit var givenCinema: Cinema

    @BeforeClass
    fun setUp() {
        today = LocalDate.now()
    }

    @Given("^show <showName> at <dateTime>$")
    fun showAtToday(showName: String, showTime: String) {
        val cityName = "Lublin"
        val createCinemaCMD = CreateCinemaCMD(showTime, cityName)
        commandHandler.execute(createCinemaCMD)
        givenCinema = cinemaRepository.findByNameAndCityName(showTime, cityName)!!

        val movieName = "Cool Movie"
        val createMovieCMD = CreateMovieCMD(movieName, givenCinema.id)
        commandHandler.execute(createMovieCMD)
        givenMovie = movieRepository.findByName(movieName)

        val expectedShowName = "Cool Movie Show"
        val givenCost = BigDecimal(15)

        val createShowCMD = CreateShowCMD(expectedShowName, givenMovie.id.value, givenCinema.id.value, givenCost, of(today, of(19, 30)))
        commandHandler.execute(createShowCMD)
        givenShow = showRepository.findByNameAndCinemaId(expectedShowName, givenCinema.id)
    }

    @And("^(\\d+)-th seat at (\\d+)-th row on show <showName> is reserved by person with first name <firstName> and last name <lastName>$")
    fun thSeatAtThRowOnShowIsReservedByPersonWithFirstNameAndSecondName(seatColumnNumber: Int, seatRowNumber: Int, showName: String, firstName: String, lastName: String) {

    }

    @When("^this person pay to cashier$")
    fun thisPersonPayToCashier() {
    }

    @Then("^the cashier mark (\\d+)-th seat at (\\d+)-th row as <reservationStatus>$")
    fun theCashierMarkThSeatAtThRowAs(seatColumnNumber: Int, seatRowNumber: Int, seatStatus: ReservationStatus) {

    }

    @And("^now is (\\d+) min before given show$")
    fun nowIsMinBeforeGivenShow(minBefore: Int) {
    }

    @When("^person is not pay to cashier$")
    fun personIsNotPayToCashier() {

    }

    @Given("^show <showName>$")
    fun show(showName: String) {

    }

    @And("^(\\d+)-th seat at (\\d+)-th row on show <showName> is <notReservedStatus> yet$")
    fun thSeatAtThRowOnShowIsYet(seatColumnNumber: Int, rowColumnNumber: Int, showName: String, seatStatus: ReservationStatus) {

    }

    @When("^person pay for cashier and order this place$")
    fun personPayForCashierAndOrderThisPlace() {

    }
}
