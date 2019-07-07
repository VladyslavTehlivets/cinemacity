package umcs.testcraftmanshipt.cinemacity.cashier

import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import umcs.testcraftmanshipt.cinemacity.CucumberStepDefinitions
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
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.AvailableTicketsQuery
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketBoardQueryRepo
import umcs.testcraftmanshipt.cinemacity.infrastructure.query.show.ticket.TicketsStatusQuery
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.LocalTime

class CashierCreateAcceptedReservation : CucumberStepDefinitions() {

    @Autowired
    private lateinit var commandHandler: CommandHandler
    @Autowired
    private lateinit var cinemaRepository: CinemaRepository
    @Autowired
    private lateinit var showRepository: ShowRepository
    @Autowired
    private lateinit var movieRepository: MovieRepository
    @Autowired
    private lateinit var ticketBoardQueryRepo: TicketBoardQueryRepo
    @Autowired
    private lateinit var userShowReserveRepo: UserShowReservationRepo

    private lateinit var givenMovie: Movie
    private lateinit var today: LocalDate
    private lateinit var givenShow: Show
    private lateinit var givenCinema: Cinema

    @Given("show {string} is defined")
    fun showIsDefined(showName: String) {
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

        val createShowCMD = CreateShowCMD(expectedShowName, givenMovie.id.value, givenCinema.id.value, givenCost, LocalDateTime.of(now(), LocalTime.of(19, 30)))
        commandHandler.execute(createShowCMD)
        givenShow = showRepository.findByNameAndCinemaId(expectedShowName, givenCinema.id)!!
    }

    @And("{int}-th seat at {int}-th row on show {string} is not reserved yet")
    fun thSeatAtThRowOnShowIsNotReservedYet(seatColumn: Int, rowColumn: Int, showName: String) {
        val availableTickets = ticketBoardQueryRepo.getResponseFor(AvailableTicketsQuery(givenShow.id.value))
        assertNotNull(availableTickets.firstOrNull { it.seatRow == rowColumn && it.seatColumn == seatColumn })
    }

    @When("person pay for cashier and order this place")
    fun personPayForCashierAndOrderThisPlace() {

    }

    @And("the cashier creates and accepts reservation of {int} in {int} for {string}")
    fun theCashierCreatesAndAcceptsReservationOfSeatInRowFor(seatRow: Int, seatColumn: Int, userName: String) {
        val reserveTicketsCMD = ReserveTicketsCMD(givenShow.id.value, userName, mutableListOf(Reservation(seatColumn, seatRow)))
        val domainObjectID = commandHandler.execute(reserveTicketsCMD)
        val reservationInfoId = domainObjectID as ReservationInfoId

        val reservationInfo = userShowReserveRepo.findByReservationInfoId(reservationInfoId)!!
        val acceptReservationCMD = AcceptReservationCMD(givenShow.id.value, userName, reservationInfo.reservationNumber)
        commandHandler.execute(acceptReservationCMD)
    }

    @Then("reservation created by cashier for user with {string} is accepted")
    fun reservationForUserIsAccepted(userName: String) {
        val userTicketsStatuses = ticketBoardQueryRepo.getResponseFor(TicketsStatusQuery(userName))
        assertTrue(userTicketsStatuses.all { it.reservationStatus == ReservationStatus.PAID.name })
    }
}