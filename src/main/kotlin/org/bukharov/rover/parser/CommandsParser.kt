package org.bukharov.rover.parser

import org.bukharov.rover.model.Command

class CommandsParser(private val input: String) {
    private val commandValues = Command.values().toList().joinToString("")
    private val regex = "[$commandValues]+".toRegex()

    fun parse(): List<Command> {
        if (!input.matches(regex))
            throw IllegalArgumentException("Input doesn't match to pattern")
        return input.map { it.toString() }.map { Command.valueOf(it) }.toList()
    }
}
