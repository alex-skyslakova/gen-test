import org.junit.Assert.*
import org.junit.Test

class LangtonSAntTest {

    @Test
    fun testInitialPosition() {
        val width = 75
        val height = 52
        val maxSteps = 12_000
        var x = width / 2
        var y = height / 2
        val m = Array(height) { IntArray(width) }
        var dir = Direction.UP
        var i = 0

        // Initial position should be in the center of the grid
        assertEquals(37, x)
        assertEquals(26, y)
        assertEquals(Direction.UP, dir)
        assertEquals(0, i)
    }

    @Test
    fun testTurnLeft() {
        var dir = Direction.UP
        val turn = true
        val index = (dir.ordinal + if (turn) 1 else -1) and 3
        dir = Direction.values()[index]
        assertEquals(Direction.LEFT, dir)
    }

    @Test
    fun testTurnRight() {
        var dir = Direction.UP
        val turn = false
        val index = (dir.ordinal + if (turn) 1 else -1) and 3
        dir = Direction.values()[index]
        assertEquals(Direction.RIGHT, dir)
    }

    @Test
    fun testMoveForward() {
        val width = 75
        val height = 52
        var x = width / 2
        var y = height / 2
        var dir = Direction.UP

        // Move up
        when (dir) {
            Direction.UP -> y--
            Direction.RIGHT -> x--
            Direction.DOWN -> y++
            Direction.LEFT -> x++
        }
        assertEquals(37, x)
        assertEquals(25, y)

        // Move right
        dir = Direction.RIGHT
        when (dir) {
            Direction.UP -> y--
            Direction.RIGHT -> x--
            Direction.DOWN -> y++
            Direction.LEFT -> x++
        }
        assertEquals(36, x)
        assertEquals(25, y)

        // Move down
        dir = Direction.DOWN
        when (dir) {
            Direction.UP -> y--
            Direction.RIGHT -> x--
            Direction.DOWN -> y++
            Direction.LEFT -> x++
        }
        assertEquals(36, x)
        assertEquals(26, y)

        // Move left
        dir = Direction.LEFT
        when (dir) {
            Direction.UP -> y--
            Direction.RIGHT -> x--
            Direction.DOWN -> y++
            Direction.LEFT -> x++
        }
        assertEquals(37, x)
        assertEquals(26, y)
    }

    @Test
    fun testColorChange() {
        val width = 75
        val height = 52
        val m = Array(height) { IntArray(width) }
        val x = width / 2
        val y = height / 2

        // Initial color should be white
        assertEquals(WHITE, m[y][x])

        // Change to black
        m[y][x] = BLACK
        assertEquals(BLACK, m[y][x])

        // Change back to white
        m[y][x] = WHITE
        assertEquals(WHITE, m[y][x])
    }

    @Test
    fun testOutOfBounds() {
        val width = 75
        val height = 52
        val maxSteps = 12_000
        var x = 0
        var y = 0
        val m = Array(height) { IntArray(width) }
        var dir = Direction.LEFT
        var i = 0

        // Move left out of bounds
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

        // Should be out of bounds
        assertFalse(x in 0 until width && y in 0 until height)
    }
}
