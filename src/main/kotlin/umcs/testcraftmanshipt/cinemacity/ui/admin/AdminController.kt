package umcs.testcraftmanshipt.cinemacity.ui.admin

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD
import umcs.testcraftmanshipt.cinemacity.domain.movie.commands.CreateMovieCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD
import umcs.testcraftmanshipt.cinemacity.domain.show.ticketDiscount.commands.CreateShowTicketDiscountCMD
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.ui.admin.dto.CreateCinemaDTO
import umcs.testcraftmanshipt.cinemacity.ui.admin.dto.CreateMovieDTO
import umcs.testcraftmanshipt.cinemacity.ui.admin.dto.CreateShowDTO
import umcs.testcraftmanshipt.cinemacity.ui.admin.dto.CreateShowTicketDiscountDTO

@RestController
@RequestMapping("admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
class AdminController(val commandHandler: CommandHandler) {

    @PostMapping("createMovie")
    fun createMovie(@RequestBody createMovieDTO: CreateMovieDTO): ResponseEntity<String> {
        val createMovieCMD = CreateMovieCMD(createMovieDTO.movieName, createMovieDTO.cinemaId)

        return ResponseEntity
                .ok(commandHandler.execute(createMovieCMD).value)
    }

    @PostMapping("createCinema")
    fun createCinema(@RequestBody createCinemaDTO: CreateCinemaDTO): ResponseEntity<String> {
        val createCinemaCMD = CreateCinemaCMD(createCinemaDTO.cinemaName, createCinemaDTO.cityName)

        return ResponseEntity
                .ok(commandHandler.execute(createCinemaCMD).value)
    }

    @PostMapping("createShow")
    fun createShow(@RequestBody createShowDTO: CreateShowDTO): ResponseEntity<String> {
        val createShowCMD = CreateShowCMD(createShowDTO.showName, createShowDTO.movieId, createShowDTO.cinemaId, createShowDTO.cost, createShowDTO.dateTime)

        return ResponseEntity
                .ok(commandHandler.execute(createShowCMD).value)
    }

    @PostMapping("defineShowDiscount")
    fun defineShowPrice(@RequestBody createShowTicketDiscountDTO: CreateShowTicketDiscountDTO): ResponseEntity<String> {
        val createShowTicketDiscountCMD = CreateShowTicketDiscountCMD(createShowTicketDiscountDTO.showId, createShowTicketDiscountDTO.discountName, createShowTicketDiscountDTO.percentFromNormalCost)

        return ResponseEntity
                .ok(commandHandler.execute(createShowTicketDiscountCMD).value)

    }
}
