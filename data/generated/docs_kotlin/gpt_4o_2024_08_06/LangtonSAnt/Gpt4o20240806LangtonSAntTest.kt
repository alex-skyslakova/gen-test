import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LangtonsAntTest {

    @Test
    fun testInitialPositionAndDirection() {
        val width = 75
        val height = 52
        val x = width / 2
        val y = height / 2
        val dir = Direction.UP

        assertEquals(37, x, "Initial X position should be at the center")
        assertEquals(26, y, "Initial Y position should be at the center")
        assertEquals(Direction.UP, dir, "Initial direction should be UP")
    }

    @Test
    fun testAntTurnsAndMoves() {
        val width = 75
        val height = 52
        val m = Array(height) { IntArray(width) }
        var x = width / 2
        var y = height / 2
        var dir = Direction.UP

        // Simulate one step
        val turn = m[y][x] == BLACK
        val index = (dir.ordinal + if (turn) 1 else -1) and 3
        dir = Direction.values()[index]
        m[y][x] = if (m[y][x] == BLACK) WHITE else BLACK
        when (dir) {
            Direction.UP -> y--
            Direction.RIGHT -> x--
            Direction.DOWN -> y++
            Direction.LEFT -> x++
        }

        // Check the ant's new position and direction
        assertEquals(37, x, "Ant should move left when turning right from UP")
        assertEquals(25, y, "Ant should move up when turning right from UP")
        assertEquals(Direction.LEFT, dir, "Ant should turn right from UP to LEFT")
    }

    @Test
    fun testGridColorChange() {
        val width = 75
        val height = 52
        val m = Array(height) { IntArray(width) }
        var x = width / 2
        var y = height / 2

        // Initial color should be WHITE
        assertEquals(WHITE, m[y][x], "Initial cell color should be WHITE")

        // Simulate one step
        m[y][x] = if (m[y][x] == BLACK) WHITE else BLACK

        // Color should change to BLACK
        assertEquals(BLACK, m[y][x], "Cell color should change to BLACK after first step")
    }

    @Test
    fun testAntExitsGrid() {
        val width = 75
        val height = 52
        val maxSteps = 12_000
        var x = width / 2
        var y = height / 2
        val m = Array(height) { IntArray(width) }
        var dir = Direction.UP
        var i = 0

        while (i < maxSteps && x in 0 until width && y in 0 until height) {
            val turn = m[y][x] == BLACK
            val index = (dir.ordinal + if (turn) 1 else -1) and 3
            dir = Direction.values()[index]
            m[y][x] = if (m[y][x] == BLACK) WHITE else BLACK
            when (dir) {
                Direction.UP -> y--
                Direction.RIGHT -> x--
                Direction.DOWN -> y++
                Direction.LEFT -> x++
            }
            i++
        }

        // Check if the ant exits the grid
        assertEquals(false, x in 0 until width && y in 0 until height, "Ant should exit the grid after max steps")
    }
}
