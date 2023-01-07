package org.bukharov.rover.parser

import org.bukharov.rover.model.Direction
import org.bukharov.rover.Rover
import org.bukharov.rover.model.Location

class RoverParser(private val input: String) {
    private val directionValues = Direction.values().toList().joinToString("")
    private val regex = "[0-9]{2}[$directionValues]".toRegex()

    fun parse(): Rover {
        if (!regex.matches(input))
            throw IllegalArgumentException("Input value doesn't match to pattern")
        return Rover(Location(input[0].digitToInt(), input[1].digitToInt()), Direction.valueOf(input[2].toString()))
    }
}
