package org.bukharov

import org.bukharov.rover.Rover
import org.bukharov.rover.model.Command
import org.bukharov.rover.parser.CommandsParser
import org.bukharov.rover.parser.FieldParser
import org.bukharov.rover.parser.RoverParser

fun main() {
    val doneCommand = "done"
    print("Enter size of field in format 12: ")
    val field = FieldParser(readln()).parse()

    println()
    println("To finish input enter '$doneCommand'")
    val parsedInput = mutableListOf<Pair<Rover, List<Command>>>()
    while (true) {
        print("Enter mars rover in format 12N: ")
        val roverValue = readln()
        if (roverValue.lowercase() == doneCommand)
            break
        val rover = RoverParser(roverValue).parse().onField(field)

        print("Enter commands in format LRM: ")
        val commandsValue = readln()
        if (commandsValue.lowercase() == doneCommand)
            break

        val commands = CommandsParser(commandsValue).parse()
        parsedInput.add(Pair(rover, commands))
    }
    parsedInput.forEach {
        val rover = it.first.applyCommands(it.second)
        println("${rover.getLocation().x}${rover.getLocation().y}${rover.getDirection()}")
    }
}
