package org.bukharov.rover.parser

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.IllegalArgumentException
import kotlin.test.assertEquals

class FieldParserTest {
    @ParameterizedTest
    @ValueSource(strings = ["12", "34", "65", "99"])
    fun `Parser should accept input`(value: String) {
        val fieldSize = FieldParser(value).parse()
        assertEquals(value[0].digitToInt(), fieldSize.x)
        assertEquals(value[1].digitToInt(), fieldSize.y)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "#4", "#$", "sd", "S5", "-1-3"])
    fun `Parser should reject input`(value: String) {
        assertThrows<IllegalArgumentException> {
            FieldParser(value).parse()
        }
    }
}
