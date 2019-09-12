package umcs.testcraftmanshipt.cinemacity.ui.cashier

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import umcs.testcraftmanshipt.cinemacity.application.commands.AcceptReservationCMD
import umcs.testcraftmanshipt.cinemacity.application.commands.RejectNotPayedReservationsCMD
import umcs.testcraftmanshipt.cinemacity.application.commands.ReserveTicketsCMD
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.ReservationInfoId
import umcs.testcraftmanshipt.cinemacity.domain.reservationInfo.UserShowReservationRepo
import umcs.testcraftmanshipt.cinemacity.domain.show.ticket.Reservation
import umcs.testcraftmanshipt.cinemacity.infrastructure.CommandHandler
import umcs.testcraftmanshipt.cinemacity.ui.cashier.dto.AcceptReservationDTO
import umcs.testcraftmanshipt.cinemacity.ui.cashier.dto.RejectNotPayedReservationsDTO
import umcs.testcraftmanshipt.cinemacity.ui.cashier.dto.ReserveTicketsDTO

@RestController
@RequestMapping("cashier")
@PreAuthorize("hasRole('ROLE_CASHIER')")
class CashierController(val commandHandler: CommandHandler, val userShowReservationRepo: UserShowReservationRepo) {

    @PostMapping("acceptReservation")
    fun createMovie(@RequestBody acceptReservation: AcceptReservationDTO): ResponseEntity<String> {
        val acceptReservationCMD = AcceptReservationCMD(acceptReservation.showId, acceptReservation.userName, acceptReservation.reservationNumber)

        return ResponseEntity
                .ok(commandHandler.execute(acceptReservationCMD).value)
    }

    @PostMapping("reserveTickets")
    fun createCinema(@RequestBody reserveTicketsDTO: ReserveTicketsDTO): ResponseEntity<String> {
        val reserveTicketsCMD = ReserveTicketsCMD(reserveTicketsDTO.showId, reserveTicketsDTO.username, mapToReservations(reserveTicketsDTO))
        val domainObjectID = commandHandler.execute(reserveTicketsCMD)
        val reservationInfoId = domainObjectID as ReservationInfoId

        val reservationInfo = userShowReservationRepo.findByReservationInfoId(reservationInfoId)!!
        val acceptReservationCMD = AcceptReservationCMD(reserveTicketsDTO.showId, reserveTicketsDTO.username, reservationInfo.reservationNumber)

        return ResponseEntity
                .ok(commandHandler.execute(acceptReservationCMD).value)
    }

    private fun mapToReservations(reserveTicketsDTO: ReserveTicketsDTO) =
            reserveTicketsDTO.selectedPlaces.map { Reservation(it.columnSeatPlace, it.rowSeatPlace, it.ticketDiscount) }.toMutableList()

    @PostMapping("rejectNotPayedReservations")
    fun createShow(@RequestBody rejectNotPayedReservationsDTO: RejectNotPayedReservationsDTO): ResponseEntity<String> {
        val rejectNotPayedReservationsCMD = RejectNotPayedReservationsCMD(rejectNotPayedReservationsDTO.showId)

        return ResponseEntity
                .ok(commandHandler.execute(rejectNotPayedReservationsCMD).value)
    }
}
