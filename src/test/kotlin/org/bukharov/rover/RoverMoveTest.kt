package org.bukharov.rover

import org.bukharov.rover.model.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

private val moveCommand = listOf(Command.M)
private val field = Field(5, 5)
class RoverMoveTest {

    @ParameterizedTest
    @MethodSource(value = ["roverMoveSource"])
    fun `Rover should apply MOVE command`(value: RoverMoveData) {
        assertEquals(
            Rover(value.expectedLocation, value.direction).onField(field),
            Rover(value.initLocation, value.direction).onField(field).applyCommands(moveCommand)
        )
    }

    @ParameterizedTest
    @MethodSource(value = ["roverMoveOutOfFieldSource"])
    fun `Should THROW when rover is out of field`(value: RoverMoveData) {
        assertThrows<IllegalAccessException> {
            Rover(value.initLocation, value.direction).onField(field).applyCommands(moveCommand)
        }
    }

    data class RoverMoveData(
        val expectedLocation: Location = EMPTY_LOCATION,
        val initLocation: Location,
        val direction: Direction
    )

    companion object {
        @JvmStatic
        fun roverMoveSource(): List<RoverMoveData> {
            val initLocation = Location(field.x - 2, field.y - 2)
            return listOf(
                RoverMoveData(
                    expectedLocation = Location(initLocation.x, initLocation.y + 1),
                    initLocation = initLocation,
                    direction = Direction.N
                ),
                RoverMoveData(
                    expectedLocation = Location(initLocation.x - 1, initLocation.y),
                    initLocation = initLocation,
                    direction = Direction.W
                ),
                RoverMoveData(
                    expectedLocation = Location(initLocation.x + 1, initLocation.y),
                    initLocation = initLocation,
                    direction = Direction.E
                ),
                RoverMoveData(
                    expectedLocation = Location(initLocation.x, initLocation.y - 1),
                    initLocation = initLocation,
                    direction = Direction.S
                )
            )
        }

        @JvmStatic
        fun roverMoveOutOfFieldSource(): List<RoverMoveData> {
            return listOf(
                RoverMoveData(initLocation = Location(field.x, field.y), direction = Direction.N),
                RoverMoveData(initLocation = Location(field.x, field.y), direction = Direction.E),
                RoverMoveData(initLocation = Location(0, field.y), direction = Direction.W),
                RoverMoveData(initLocation = Location(field.x, 0), direction = Direction.S)
            )
        }
    }
}
