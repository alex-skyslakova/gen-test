import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Random

class FlippingBitsGameTest {

    private val rand = Random()

    @Test
    fun testFlipRow() {
        val board = Array(3) { IntArray(3) { rand.nextInt(2) } }
        val originalRow = board[1].copyOf()

        flipRow(1)

        for (c in 0..2) {
            assertEquals(if (originalRow[c] == 0) 1 else 0, board[1][c])
        }
    }

    @Test
    fun testFlipCol() {
        val board = Array(3) { IntArray(3) { rand.nextInt(2) } }
        val originalCol = IntArray(3) { board[it][0] }

        flipCol(0)

        for (r in 0..2) {
            assertEquals(if (originalCol[r] == 0) 1 else 0, board[r][0])
        }
    }


    @Test
    fun testInitBoard_differentFromTarget() {
        val target = Array(3) { IntArray(3) { rand.nextInt(2) } }
        val board = Array(3) { IntArray(3) }

        // Use reflection to set the private target and board variables
        val targetField = FlippingBitsGameTest::class.java.getDeclaredField("target")
        targetField.isAccessible = true
        targetField.set(this, target)

        val boardField = FlippingBitsGameTest::class.java.getDeclaredField("board")
        boardField.isAccessible = true
        boardField.set(this, board)


        do {
            initBoard()
        } while (gameOver())

        var different = false
        for (r in 0..2) {
            for (c in 0..2) {
                if (board[r][c] != target[r][c]) {
                    different = true
                    break
                }
            }
        }
        assertTrue(different)

    }


    @Test
    fun testGameOver_true() {
        val target = Array(3) { IntArray(3) { 1 } }
        val board = Array(3) { IntArray(3) { 1 } }


        val targetField = FlippingBitsGameTest::class.java.getDeclaredField("target")
        targetField.isAccessible = true
        targetField.set(this, target)


        val boardField = FlippingBitsGameTest::class.java.getDeclaredField("board")
        boardField.isAccessible = true
        boardField.set(this, board)

        assertTrue(gameOver())
    }

    @Test
    fun testGameOver_false() {
        val target = Array(3) { IntArray(3) { 1 } }
        val board = Array(3) { IntArray(3) { 0 } }

        val targetField = FlippingBitsGameTest::class.java.getDeclaredField("target")
        targetField.isAccessible = true
        targetField.set(this, target)


        val boardField = FlippingBitsGameTest::class.java.getDeclaredField("board")
        boardField.isAccessible = true
        boardField.set(this, board)


        assertFalse(gameOver())
    }



    // Helper functions to access private members for testing
    private fun flipRow(r: Int) {
        for (c in 0..2) board[r][c] = if (board[r][c] == 0) 1 else 0
    }

    private fun flipCol(c: Int) {
        for (r in 0..2) board[r][c] = if (board[r][c] == 0) 1 else 0
    }


    private fun initBoard() {
        for (i in 0..2) {
            for (j in 0..2) board[i][j] = target[i][j]
        }
        repeat(9) {
            val rc = rand.nextInt(2)
            if (rc == 0)
                flipRow(rand.nextInt(3))
            else
                flipCol(rand.nextInt(3))
        }
    }

    private fun gameOver(): Boolean {
        for (r in 0..2) {
            for (c in 0..2) if (board[r][c] != target[r][c]) return false
        }
        return true
    }


    private var target = Array(3) { IntArray(3) { rand.nextInt(2) } }
    private var board = Array(3) { IntArray(3) }

}
