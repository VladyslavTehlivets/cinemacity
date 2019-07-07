package umcs.testcraftmanshipt.cinemacity.cashier

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.api.java8.En
import junit.framework.Assert.assertTrue
import org.junit.BeforeClass
import org.springframework.boot.test.context.SpringBootTest
import umcs.testcraftmanshipt.cinemacity.application.commands.AcceptReservationCMD
import umcs.testcraftmanshipt.cinemacity.application.commands.ReserveTicketsCMD
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.Movie
import umcs.testcraftmanshipt.cinemacity.domain.movie.MovieRepository
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfoId
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.UserShowReservationRepo
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.Reservation
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.ReservationStatus
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketBoardQueryRepo
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketsStatusQuery
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime.of
import java.time.LocalTime.of

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CashierAcceptReservation(private val commandHandler: CommandHandler, private val cinemaRepository: CinemaRepository,
                               private val showRepository: ShowRepository,
                               private val movieRepository: MovieRepository,
                               private val ticketBoardQueryRepo: TicketBoardQueryRepo,
                               private val userShowReservRepo: UserShowReservationRepo) : En {

    private lateinit var reservationInfoId: ReservationInfoId
    private lateinit var givenMovie: Movie
    private lateinit var today: LocalDate
    private lateinit var givenShow: Show
    private lateinit var givenCinema: Cinema

    @BeforeClass
    fun setUp() {
        today = LocalDate.now()
    }

    @Given("^show <showName> defined to be at <time>$")
    fun showAtToday(showName: String, showTime: String) {
        val cityName = "Lublin"
        val cinemaName = "Plaza"
        val createCinemaCMD = CreateCinemaCMD(cinemaName, cityName)
        commandHandler.execute(createCinemaCMD)
        givenCinema = cinemaRepository.findByNameAndCityName(cinemaName, cityName)!!

        val movieName = "Cool Movie"
        val createMovieCMD = CreateMovieCMD(movieName, givenCinema.id.value)
        commandHandler.execute(createMovieCMD)
        givenMovie = movieRepository.findByName(movieName)

        val expectedShowName = "Cool Movie Show"
        val givenCost = BigDecimal(15)

        val createShowCMD = CreateShowCMD(expectedShowName, givenMovie.id.value, givenCinema.id.value, givenCost, of(today, of(19, 30)))
        commandHandler.execute(createShowCMD)
        givenShow = showRepository.findByNameAndCinemaId(expectedShowName, givenCinema.id)!!
    }

    @And("^<seatColumn>-th seat at <seatRow>-th row on show <showName> is reserved by person with <userName>$")
    fun thSeatAtThRowOnShowIsReservedByPersonWithFirstNameAndSecondName(seatColumn: Int, seatRow: Int, showName: String, userName: String) {
        val reserveTicketsCMD = ReserveTicketsCMD(givenShow.id.value, userName, mutableListOf(Reservation(seatColumn, seatRow)))
        val domainObjectID = commandHandler.execute(reserveTicketsCMD)

        reservationInfoId = domainObjectID as ReservationInfoId
    }

    @When("^this person pay to cashier$")
    fun thisPersonPayToCashier() {
    }

    @And("^the cashier accepts reservation for <userName>$")
    fun theCashierAcceptsReservationFor(userName: String) {
        val reservationInfo = userShowReservRepo.findByReservationInfoId(reservationInfoId)!!
        val acceptReservationCMD = AcceptReservationCMD(givenShow.id.value, userName, reservationInfo.reservationNumber)
        commandHandler.execute(acceptReservationCMD)
    }

    @Then("^reservation for <userName> is accepted$")
    fun reservationForUserIsAccepted(userName: String) {
        val reservationInfo = userShowReservRepo.findByReservationInfoId(reservationInfoId)!!
        val userTicketsStatuses = ticketBoardQueryRepo.getResponseFor(TicketsStatusQuery(reservationInfo.reservationNumber))
        assertTrue(userTicketsStatuses.all { it.reservationStatus == ReservationStatus.PAID.name })
        assertTrue(userTicketsStatuses.all { it.reservedBy == userName })
    }
}
