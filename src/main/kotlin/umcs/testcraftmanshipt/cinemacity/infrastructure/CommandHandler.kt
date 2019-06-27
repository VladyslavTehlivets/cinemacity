package umcs.testcraftmanshipt.cinemacity.infrastructure

import org.springframework.stereotype.Service
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.Handler

@Service
class CommandHandler(private val commandsHandlers: List<Handler<out Command>>) {

    fun <CommandT : Command> execute(command: CommandT) {
        val handler: Handler<CommandT> = commandsHandlers.first { it.isHandlerForCommand(command) } as Handler<CommandT>
        handler.handle(command)
    }
}
