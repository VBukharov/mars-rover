package org.bukharov.rover.parser

import org.bukharov.rover.model.Field

class FieldParser(private val input: String) {
    private val regex = "[0-9]{2}".toRegex()
    fun parse(): Field {
        if (!regex.matches(input))
            throw IllegalArgumentException("Input doesn't match to pattern")

        return Field(input[0].digitToInt(), input[1].digitToInt())
    }

}
