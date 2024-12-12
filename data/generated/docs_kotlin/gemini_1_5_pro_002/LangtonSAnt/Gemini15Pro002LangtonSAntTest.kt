import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LangtonsAntTest {

    @Test
    fun testInitialConditions() {
        val width = 5
        val height = 5
        val m = Array(height) { IntArray(width) }
        for (j in 0 until height) {
            for (k in 0 until width) {
                assertEquals(0, m[j][k]) // Initially all cells are white
            }
        }
    }

    @Test
    fun testSingleStepWhiteCell() {
        val width = 5
        val height = 5
        val m = Array(height) { IntArray(width) }
        var x = 2
        var y = 2
        var dir = Direction.UP

        val turn = m[y][x] == BLACK
        val index = (dir.ordinal + if (turn) 1 else -1) and 3
        dir = Direction.values()[index]
        m[y][x] = if (m[y][x] == BLACK) WHITE else BLACK
        when (dir) {
            Direction.UP    -> y--
            Direction.RIGHT -> x--
            Direction.DOWN  -> y++
            Direction.LEFT  -> x++
        }

        assertEquals(1, m[2][2]) // Cell turned black
        assertEquals(2, x)
        assertEquals(1, y)
        assertEquals(Direction.RIGHT, dir) // Ant turned right
    }


    @Test
    fun testSingleStepBlackCell() {
        val width = 5
        val height = 5
        val m = Array(height) { IntArray(width) }
        var x = 2
        var y = 2
        var dir = Direction.UP
        m[y][x] = BLACK

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

        assertEquals(0, m[2][2]) // Cell turned white
        assertEquals(2, x)
        assertEquals(1, y)
        assertEquals(Direction.LEFT, dir) // Ant turned left
    }


    @Test
    fun testDirectionWrapping() {
        val indexRightWrap = (Direction.LEFT.ordinal - 1) and 3
        assertEquals(Direction.DOWN, Direction.values()[indexRightWrap])

        val indexLeftWrap = (Direction.UP.ordinal + 1) and 3
        assertEquals(Direction.RIGHT, Direction.values()[indexLeftWrap])

    }


}
