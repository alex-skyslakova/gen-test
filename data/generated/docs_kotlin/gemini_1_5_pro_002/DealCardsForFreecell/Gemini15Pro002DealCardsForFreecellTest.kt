import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FreeCellDealTest {

    @Test
    fun testLcgNextInt() {
        val lcg = Lcg(214013, 2531011, 1 shl 31, 1 shl 16, 1)
        assertEquals(12234, lcg.nextInt())
        assertEquals(23112, lcg.nextInt())
    }

    @Test
    fun testDeal() {
        val cards = deal()
        assertEquals(52, cards.size)
        assertEquals("A♣", cards[0])
        assertEquals("K♠", cards[51])
    }

    @Test
    fun testGameInvalidInput() {
        assertThrows<IllegalArgumentException> { game(0) }
        assertThrows<IllegalArgumentException> { game(-1) }
    }

    @Test
    fun testGame1() {
        val output = captureOutput { game(1) }
        assertTrue(output.contains("Game #1:"))
        assertTrue(output.contains("JD  2D  9H  JC  5D  7H  7C  5H  "))
        assertTrue(output.contains("KD  KC  9S  5S  AD  QC  KH  3H  "))
        // ... (check other lines)
        assertTrue(output.contains("6S  9C  2H  6H  "))
    }

    @Test
    fun testGame617() {
        val output = captureOutput { game(617) }
        assertTrue(output.contains("Game #617:"))
        assertTrue(output.contains("7D  AD  5C  3S  5S  8C  2D  AH  "))
        assertTrue(output.contains("TD  7S  QD  AC  6D  8H  AS  KH  "))
        // ... (check other lines)
        assertTrue(output.contains("JD  KS  KC  4H  "))
    }


    // Helper function to capture console output
    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val writer = StringBuilder()
        System.setOut(System.out.writer())
        try {
             block()
             return writer.toString()

        } finally {
          System.out.flush()
          System.setOut(originalOut)
        }

    }

}
