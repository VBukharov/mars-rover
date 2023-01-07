package org.bukharov.rover.parser

import org.bukharov.rover.model.Direction
import org.bukharov.rover.Rover
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class RoverParserTest {

    @ParameterizedTest
    @ValueSource(strings = ["12N", "34E", "54W", "99S"])
    fun `Parser should accept input`(value: String) {
        val rover: Rover = RoverParser(value).parse()
        assertEquals(value[0].digitToInt(), rover.getLocation().x)
        assertEquals(value[1].digitToInt(), rover.getLocation().y)
        assertEquals(Direction.valueOf(value[2].toString()), rover.getDirection())
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "123", "12M", "M3N", "4", "N", "-23E", "12n", "44s", "33e", "34w"])
    fun `Parser should reject input`(value: String) {
        assertThrows<IllegalArgumentException> {
            RoverParser(value).parse()
        }
    }
}
