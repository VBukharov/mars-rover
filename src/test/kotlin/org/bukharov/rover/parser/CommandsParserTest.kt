package org.bukharov.rover.parser

import org.bukharov.rover.model.Command
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import kotlin.test.Test
import kotlin.test.assertEquals

class CommandsParserTest {

    @Test
    fun `Commands size should be equal to input length`() {
        val input = "LMLMRRRMMM"
        val commands = CommandsParser(input).parse()
        assertEquals(commands.size, input.length)
    }

    @ParameterizedTest
    @ValueSource(strings = ["LRMMMRL", "RRRR", "MMM", "L", "R", "M", "LRMLRMLRM"])
    fun `Parser should accept input`(value: String) {
        val commands: List<Command> = CommandsParser(value).parse()
        commands.forEachIndexed { index, command ->
            assertEquals(Command.valueOf(value[index].toString()), command)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "lrmllrrmm", "1RM", "1", "#", "#$@32", "MR#4L"])
    fun `Parser should reject input`(value: String) {
        assertThrows<IllegalArgumentException> {
            CommandsParser(value).parse()
        }
    }
}
