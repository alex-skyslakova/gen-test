import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FreeCellTest {

    @Test
    fun `test Lcg nextInt`() {
        val lcg = Lcg(214013, 2531011, 1 shl 31, 1 shl 16, 1)
        val firstRandom = lcg.nextInt()
        assertEquals(31529, firstRandom) // Expected value based on the algorithm
    }

    @Test
    fun `test deal function`() {
        val expectedCards = arrayOf(
            "A♣", "A♦", "A♥", "A♠", "2♣", "2♦", "2♥", "2♠",
            "3♣", "3♦", "3♥", "3♠", "4♣", "4♦", "4♥", "4♠",
            "5♣", "5♦", "5♥", "5♠", "6♣", "6♦", "6♥", "6♠",
            "7♣", "7♦", "7♥", "7♠", "8♣", "8♦", "8♥", "8♠",
            "9♣", "9♦", "9♥", "9♠", "T♣", "T♦", "T♥", "T♠",
            "J♣", "J♦", "J♥", "J♠", "Q♣", "Q♦", "Q♥", "Q♠",
            "K♣", "K♦", "K♥", "K♠"
        )
        val cards = deal()
        assertArrayEquals(expectedCards, cards)
    }

    @Test
    fun `test game function with game number 1`() {
        val expectedOutput = """
            Game #1:
            JD  2D  9H  JC  5D  7H  7C  5H  
            KD  KC  9S  5S  AD  QC  KH  3H  
            2S  KS  9D  QD  JS  AS  AH  3C  
            4C  5C  TS  QH  4H  AC  4D  7S  
            3S  TD  4S  TH  8H  2C  JH  7D  
            6D  8S  8D  QS  6C  3D  8C  TC  
            6S  9C  2H  6H  

        """.trimIndent()
        val output = captureOutput { game(1) }
        assertEquals(expectedOutput, output)
    }

    @Test
    fun `test game function with game number 617`() {
        val expectedOutput = """
            Game #617:
            7D  AD  5C  3S  5S  8C  2D  AH  
            TD  7S  QD  AC  6D  8H  AS  KH  
            TH  QC  3H  9D  6S  8D  3D  TC  
            KD  5H  9S  3C  8S  7H  4D  JS  
            4C  QS  9C  9H  7C  6H  2C  2S  
            4S  TS  2H  5D  JC  6C  JH  QH  
            JD  KS  KC  4H  

        """.trimIndent()
        val output = captureOutput { game(617) }
        assertEquals(expectedOutput, output)
    }

    private fun captureOutput(block: () -> Unit): String {
        val outputStream = java.io.ByteArrayOutputStream()
        val printStream = java.io.PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)
        block()
        System.out.flush()
        System.setOut(originalOut)
        return outputStream.toString().trim()
    }
}
