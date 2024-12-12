import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FairShareTest {

    @Test
    fun testTurnBase2() {
        val expected = listOf(0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1)
        val result = (0 until 25).map { turn(2, it) }
        assertEquals(expected, result)
    }

    @Test
    fun testTurnBase3() {
        val expected = listOf(0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 2, 1, 0, 1, 2, 0, 2, 0, 1, 1)
        val result = (0 until 25).map { turn(3, it) }
        assertEquals(expected, result)
    }

    @Test
    fun testTurnBase5() {
        val expected = listOf(0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3)
        val result = (0 until 25).map { turn(5, it) }
        assertEquals(expected, result)
    }

    @Test
    fun testTurnBase11() {
        val expected = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4)
        val result = (0 until 25).map { turn(11, it) }
        assertEquals(expected, result)
    }

    @Test
    fun testTurnCountBase191() {
        val expected = "  With 191 people: 261 or 262"
        val result = captureOutput { turnCount(191, 50000) }
        assertEquals(expected, result.trim())
    }

    @Test
    fun testTurnCountBase1377() {
        val expected = "  With 1377 people: 36 or 37"
        val result = captureOutput { turnCount(1377, 50000) }
        assertEquals(expected, result.trim())
    }

    @Test
    fun testTurnCountBase49999() {
        val expected = "  With 49999 people: 1 or 2"
        val result = captureOutput { turnCount(49999, 50000) }
        assertEquals(expected, result.trim())
    }

    @Test
    fun testTurnCountBase50000() {
        val expected = "  With 50000 people: 1"
        val result = captureOutput { turnCount(50000, 50000) }
        assertEquals(expected, result.trim())
    }

    @Test
    fun testTurnCountBase50001() {
        val expected = "  With 50001 people: Only 50000 have a turn"
        val result = captureOutput { turnCount(50001, 50000) }
        assertEquals(expected, result.trim())
    }

    private fun captureOutput(block: () -> Unit): String {
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        block()
        return outputStream.toString()
    }
}
