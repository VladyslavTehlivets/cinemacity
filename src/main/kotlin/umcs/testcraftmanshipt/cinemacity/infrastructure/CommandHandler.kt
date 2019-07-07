package umcs.testcraftmanshipt.cinemacity.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import umcs.testcraftmanshipt.cinemacity.domain.Command
import umcs.testcraftmanshipt.cinemacity.domain.DomainObjectID
import umcs.testcraftmanshipt.cinemacity.domain.Handler

@Service
class CommandHandler(@Autowired private val commandsHandlers: List<Handler<out Command>>) {

    @Transactional
    fun <CommandT : Command> execute(command: CommandT): DomainObjectID {
        val handler: Handler<CommandT> = commandsHandlers.first { it.isHandlerForCommand(command) } as Handler<CommandT>
        return handler.handle(command)
    }
}
