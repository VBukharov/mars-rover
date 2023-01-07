package org.bukharov.rover

import org.bukharov.rover.model.*

class Rover (
    private var location: Location,
    private var direction: Direction
) {

    private var field = EMPTY_FIELD

    fun getLocation() = location
    fun getDirection() = direction

    fun onField(field: Field): Rover {
        if (location.x > field.x || location.y > field.y)
            throw IllegalArgumentException("Couldn't set rover on field")
        this.field = field
        return this
    }

    fun currentField() = field
    fun applyCommands(commands: List<Command>): Rover {
        commands.forEach{command ->
            when (command) {
                Command.L -> applyLeftRotation()
                Command.R -> applyRightRotation()
                Command.M -> applyMove()
            }
        }
        return this
    }

    private fun applyMove() {
        val newLocation = when (direction) {
            Direction.N -> Location(location.x, location.y + 1)
            Direction.E -> Location(location.x + 1, location.y)
            Direction.W -> Location(location.x - 1, location.y)
            Direction.S -> Location(location.x, location.y - 1)
        }
        if (newLocation.x < 0 || newLocation.y < 0 || newLocation.x > field.x || newLocation.y > field.y)
            throw IllegalAccessException("Rover is out of field")
        location = newLocation
    }

    private fun applyRightRotation() {
        direction = when (direction) {
            Direction.N -> Direction.E
            Direction.E -> Direction.S
            Direction.W -> Direction.N
            Direction.S -> Direction.W
        }
    }

    private fun applyLeftRotation() {
        direction = when (direction) {
            Direction.N -> Direction.W
            Direction.E -> Direction.N
            Direction.W -> Direction.S
            Direction.S -> Direction.E
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rover

        if (location != other.location) return false
        if (direction != other.direction) return false
        if (field != other.field) return false

        return true
    }

    override fun hashCode(): Int {
        var result = location.hashCode()
        result = 31 * result + direction.hashCode()
        result = 31 * result + field.hashCode()
        return result
    }
}
