package umcs.testcraftmanshipt.cinemacity.domain.show.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.show.Show
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD

@Service
class CreateShowHandler(private val showRepository: ShowRepository) : Handler<CreateShowCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateShowCMD::class
    }

    override fun handle(command: CreateShowCMD) {
        val movie = Show(command.expectedShowName, command.cinemaId)
        showRepository.save(movie)
    }
}
