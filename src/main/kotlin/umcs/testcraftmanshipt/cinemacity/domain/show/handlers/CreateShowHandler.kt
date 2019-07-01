package umcs.testcraftmanshipt.cinemacity.domain.show.handlers

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.Handler
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowFactory.Companion.createShow
import umcs.testcraftmanshipt.cinemacity.domain.show.ShowRepository
import umcs.testcraftmanshipt.cinemacity.domain.show.commands.CreateShowCMD

@Service
class CreateShowHandler(private val showRepository: ShowRepository) : Handler<CreateShowCMD> {

    override fun isHandlerForCommand(command: Command): Boolean {
        return command::class == CreateShowCMD::class
    }

    override fun handle(command: CreateShowCMD) {
        val show = createShow(command.expectedShowName, command.cinemaId, command.cost)
        showRepository.save(show)
    }
}
