package umcs.testcraftmanshipt.cinemacity.domain.cinema.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.cinema.Cinema
import umcs.testcraftmanshipt.cinemacity.domain.cinema.CinemaRepository
import umcs.testcraftmanshipt.cinemacity.domain.cinema.commands.CreateCinemaCMD

@Service
class CreateCinemaHandler(private val cinemaRepository: CinemaRepository) : Handler<CreateCinemaCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateCinemaCMD::class
    }

    override fun handle(command: CreateCinemaCMD): DomainObjectID {
        val cinema = Cinema(command.cinemaName, command.cityName)
        cinemaRepository.save(cinema)
        return cinema.id
    }
}
