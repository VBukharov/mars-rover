package org.bukharov.rover

import org.bukharov.rover.model.Command
import org.bukharov.rover.model.Direction
import org.bukharov.rover.model.Field
import org.bukharov.rover.model.Location
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class RoverRotationTest {

    private val field = Field(5, 5)
    private val initialLocation = Location(field.x - 2, field.y - 3)
    private val leftRotationCommand = listOf(Command.L)
    private val rightRotationCommand = listOf(Command.R)

    @ParameterizedTest
    @MethodSource(value = ["leftRotationSource"])
    fun `Rover should apply left rotation`(value: RotationSource) {
        assertEquals(
            Rover(initialLocation, value.expectedDirection).onField(field),
            Rover(initialLocation, value.initialDirection).onField(field).applyCommands(leftRotationCommand)
        )
    }

    @ParameterizedTest
    @MethodSource(value = ["rightRotationSource"])
    fun `Rover should apply right rotation`(value: RotationSource) {
        assertEquals(
            Rover(initialLocation, value.expectedDirection).onField(field),
            Rover(initialLocation, value.initialDirection).onField(field).applyCommands(rightRotationCommand)
        )
    }

    data class RotationSource (val expectedDirection: Direction, val initialDirection: Direction)
    companion object {
        @JvmStatic
        fun leftRotationSource(): List<RotationSource> {
            return listOf(
                RotationSource(Direction.W, Direction.N),
                RotationSource(Direction.S, Direction.W),
                RotationSource(Direction.E, Direction.S),
                RotationSource(Direction.N, Direction.E),
            )
        }

        @JvmStatic
        fun rightRotationSource(): List<RotationSource> {
            return listOf(
                RotationSource(Direction.W, Direction.S),
                RotationSource(Direction.S, Direction.E),
                RotationSource(Direction.E, Direction.N),
                RotationSource(Direction.N, Direction.W),
            )
        }
    }
}
