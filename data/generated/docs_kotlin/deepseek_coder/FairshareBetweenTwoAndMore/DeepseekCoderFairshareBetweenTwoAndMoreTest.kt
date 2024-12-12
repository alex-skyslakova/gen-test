import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FairshareBetweenTwoAndMoreTest {

    @Test
    fun testTurn() {
        // Test cases for base 2
        assertEquals(0, turn(2, 0))
        assertEquals(1, turn(2, 1))
        assertEquals(1, turn(2, 2))
        assertEquals(0, turn(2, 3))
        assertEquals(0, turn(2, 4))
        assertEquals(1, turn(2, 5))
        assertEquals(1, turn(2, 6))
        assertEquals(0, turn(2, 7))

        // Test cases for base 3
        assertEquals(0, turn(3, 0))
        assertEquals(1, turn(3, 1))
        assertEquals(2, turn(3, 2))
        assertEquals(1, turn(3, 3))
        assertEquals(2, turn(3, 4))
        assertEquals(0, turn(3, 5))
        assertEquals(2, turn(3, 6))
        assertEquals(0, turn(3, 7))

        // Test cases for base 5
        assertEquals(0, turn(5, 0))
        assertEquals(1, turn(5, 1))
        assertEquals(2, turn(5, 2))
        assertEquals(3, turn(5, 3))
        assertEquals(4, turn(5, 4))
        assertEquals(1, turn(5, 5))
        assertEquals(2, turn(5, 6))
        assertEquals(3, turn(5, 7))

        // Test cases for base 11
        assertEquals(0, turn(11, 0))
        assertEquals(1, turn(11, 1))
        assertEquals(2, turn(11, 2))
        assertEquals(3, turn(11, 3))
        assertEquals(4, turn(11, 4))
        assertEquals(5, turn(11, 5))
        assertEquals(6, turn(11, 6))
        assertEquals(7, turn(11, 7))
        assertEquals(8, turn(11, 8))
        assertEquals(9, turn(11, 9))
        assertEquals(10, turn(11, 10))
        assertEquals(1, turn(11, 11))
    }

    @Test
    fun testFairShare() {
        // Test the first 25 terms for base 2
        val base2Expected = "Base  2:  0  1  1  0  1  0  0  1  1  0  0  1  0  1  1  0  1  0  0  1  0  1  1  0  0"
        val base2Actual = captureOutput { fairShare(2, 25) }
        assertEquals(base2Expected, base2Actual.trim())

        // Test the first 25 terms for base 3
        val base3Expected = "Base  3:  0  1  2  1  2  0  2  0  1  1  2  0  2  0  1  0  1  2  2  0  1  0  1  2  1"
        val base3Actual = captureOutput { fairShare(3, 25) }
        assertEquals(base3Expected, base3Actual.trim())

        // Test the first 25 terms for base 5
        val base5Expected = "Base  5:  0  1  2  3  4  1  2  3  4  0  2  3  4  0  1  3  4  0  1  2  4  0  1  2  3"
        val base5Actual = captureOutput { fairShare(5, 25) }
        assertEquals(base5Expected, base5Actual.trim())

        // Test the first 25 terms for base 11
        val base11Expected = "Base 11:  0  1  2  3  4  5  6  7  8  9 10  1  2  3  4  5  6  7  8  9 10  0  2  3  4"
        val base11Actual = captureOutput { fairShare(11, 25) }
        assertEquals(base11Expected, base11Actual.trim())
    }

    @Test
    fun testTurnCount() {
        // Test turnCount for base 191
        val base191Expected = "  With 191 people: 261 or 262"
        val base191Actual = captureOutput { turnCount(191, 50000) }
        assertEquals(base191Expected, base191Actual.trim())

        // Test turnCount for base 1377
        val base1377Expected = "  With 1377 people: 36 or 37"
        val base1377Actual = captureOutput { turnCount(1377, 50000) }
        assertEquals(base1377Expected, base1377Actual.trim())

        // Test turnCount for base 49999
        val base49999Expected = "  With 49999 people: Only 1 have a turn"
        val base49999Actual = captureOutput { turnCount(49999, 50000) }
        assertEquals(base49999Expected, base49999Actual.trim())

        // Test turnCount for base 50000
        val base50000Expected = "  With 50000 people: 1"
        val base50000Actual = captureOutput { turnCount(50000, 50000) }
        assertEquals(base50000Expected, base50000Actual.trim())

        // Test turnCount for base 50001
        val base50001Expected = "  With 50001 people: Only 1 have a turn"
        val base50001Actual = captureOutput { turnCount(50001, 50000) }
        assertEquals(base50001Expected, base50001Actual.trim())
    }

    private fun captureOutput(block: () -> Unit): String {
        val out = System.out
        val output = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(output))
        block()
        System.setOut(out)
        return output.toString()
    }
}
