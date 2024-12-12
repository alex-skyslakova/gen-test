import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.util.Random

class FlippingBitsGameTest {

    private val rand = Random()
    private val target = Array(3) { IntArray(3) { rand.nextInt(2) } }
    private val board = Array(3) { IntArray(3) }

    @BeforeEach
    fun setUp() {
        // Initialize the board to match the target configuration
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = target[i][j]
            }
        }
    }

    @Test
    fun testFlipRow() {
        // Flip the first row
        flipRow(0)

        // Check that the first row is inverted
        for (c in 0..2) {
            assertEquals(1 - target[0][c], board[0][c])
        }

        // Check that other rows remain unchanged
        for (r in 1..2) {
            for (c in 0..2) {
                assertEquals(target[r][c], board[r][c])
            }
        }
    }

    @Test
    fun testFlipCol() {
        // Flip the first column
        flipCol(0)

        // Check that the first column is inverted
        for (r in 0..2) {
            assertEquals(1 - target[r][0], board[r][0])
        }

        // Check that other columns remain unchanged
        for (r in 0..2) {
            for (c in 1..2) {
                assertEquals(target[r][c], board[r][c])
            }
        }
    }

    @Test
    fun testInitBoard() {
        // Initialize the board with random flips
        initBoard()

        // Check that the board is not the same as the target
        var same = true
        for (r in 0..2) {
            for (c in 0..2) {
                if (board[r][c] != target[r][c]) {
                    same = false
                    break
                }
            }
            if (!same) break
        }
        assertFalse(same)
    }

    @Test
    fun testGameOver() {
        // Initially, the game should not be over
        assertFalse(gameOver())

        // Set the board to match the target
        for (r in 0..2) {
            for (c in 0..2) {
                board[r][c] = target[r][c]
            }
        }

        // Now the game should be over
        assertTrue(gameOver())
    }

    @Test
    fun testGameOverAfterFlips() {
        // Flip the board to match the target
        for (r in 0..2) {
            for (c in 0..2) {
                if (board[r][c] != target[r][c]) {
                    if (r == 0) flipRow(r) else flipCol(c)
                }
            }
        }

        // The game should be over after the flips
        assertTrue(gameOver())
    }
}
