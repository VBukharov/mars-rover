package org.bukharov.rover

import org.bukharov.rover.model.Command
import org.bukharov.rover.model.Direction
import org.bukharov.rover.model.Field
import org.bukharov.rover.model.Location
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class RoverTest {

    @Test
    fun `Rover shouldn't fit into the field`() {
        val field = Field(1, 1)
        assertThrows<IllegalArgumentException> {
            Rover(Location(3, 3), Direction.N).onField(field)
        }
    }

    @ParameterizedTest
    @MethodSource(value = ["fieldRoverSource"])
    fun `Rover should fit into the field`(source: Pair<Field, Rover>) {
        source.second.onField(source.first)
        assertEquals(source.first, source.second.currentField())
    }

    @ParameterizedTest
    @MethodSource(value = ["roverCommandsSource"])
    fun `Rover should apply commands`(value: RoverCommandsSource) {
        assertEquals(
            value.expectedRover.onField(value.field),
            value.initialRover.onField(value.field).applyCommands(value.commands)
        )
    }

    data class RoverCommandsSource(
        val field: Field,
        val expectedRover: Rover,
        val initialRover: Rover,
        val commands: List<Command>
    )
    companion object {
        @JvmStatic
        private fun fieldRoverSource() = listOf(
            Pair(Field(4, 4), Rover(Location(1, 2), Direction.N)),
            Pair(Field(4, 4), Rover(Location(0, 0), Direction.N)),
            Pair(Field(4, 4), Rover(Location(0, 4), Direction.N)),
            Pair(Field(4, 4), Rover(Location(4, 0), Direction.N)),
            Pair(Field(4, 4), Rover(Location(4, 4), Direction.N)),
        )

        @JvmStatic
        private fun roverCommandsSource() = listOf(
            RoverCommandsSource(
                Field(5, 5),
                Rover(Location(1, 3), Direction.N),
                Rover(Location(1, 2), Direction.N),
                listOf(
                    Command.L,
                    Command.M,
                    Command.L,
                    Command.M,
                    Command.L,
                    Command.M,
                    Command.L,
                    Command.M,
                    Command.M
                )
            ),
            RoverCommandsSource(
                Field(5, 5),
                Rover(Location(5, 1), Direction.E),
                Rover(Location(3, 3), Direction.E),
                listOf(
                    Command.M,
                    Command.M,
                    Command.R,
                    Command.M,
                    Command.M,
                    Command.R,
                    Command.M,
                    Command.R,
                    Command.R,
                    Command.M
                )
            )
        )
    }
}
