import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FlippingBitsGameTest {

    @BeforeEach
    fun setUp() {
        // Reset the board and target before each test
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = 0
                target[i][j] = 0
            }
        }
    }

    @Test
    fun testFlipRow() {
        // Set up a known board state
        board[0] = intArrayOf(0, 1, 0)
        board[1] = intArrayOf(1, 0, 1)
        board[2] = intArrayOf(0, 0, 0)

        // Flip the first row
        flipRow(0)

        // Check the result
        assertArrayEquals(intArrayOf(1, 0, 1), board[0])
        assertArrayEquals(intArrayOf(1, 0, 1), board[1])
        assertArrayEquals(intArrayOf(0, 0, 0), board[2])
    }

    @Test
    fun testFlipCol() {
        // Set up a known board state
        board[0] = intArrayOf(0, 1, 0)
        board[1] = intArrayOf(1, 0, 1)
        board[2] = intArrayOf(0, 0, 0)

        // Flip the second column
        flipCol(1)

        // Check the result
        assertArrayEquals(intArrayOf(0, 0, 0), board[0])
        assertArrayEquals(intArrayOf(1, 1, 1), board[1])
        assertArrayEquals(intArrayOf(0, 1, 0), board[2])
    }

    @Test
    fun testInitBoard() {
        // Initialize the board
        initBoard()

        // Ensure the board is not the same as the target
        assertFalse(gameOver())
    }

    @Test
    fun testGameOver() {
        // Set up a board that matches the target
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = target[i][j]
            }
        }

        // Check that the game is over
        assertTrue(gameOver())

        // Change one element to ensure the game is not over
        board[0][0] = if (board[0][0] == 0) 1 else 0

        // Check that the game is not over
        assertFalse(gameOver())
    }
}
