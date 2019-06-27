package umcs.testcraftmanshipt.cinemacity.domain

interface Handler<CommandT: Command> {

    fun isHandlerForCommand(command: Command): Boolean

    fun handle(command: CommandT)
}