import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader

class IBeforeETest {

    @Test
    fun testIsPlausible() {
        assertTrue(isPlausible(10, 4))  // 10 > 2 * 4
        assertFalse(isPlausible(8, 4))  // 8 is not > 2 * 4
    }

    @Test
    fun testRegexMatching() {
        val regexes = arrayOf(
            Regex("(^|[^c])ie"),     // i before e when not preceded by c
            Regex("(^|[^c])ei"),     // e before i when not preceded by c
            Regex("cei"),            // e before i when preceded by c
            Regex("cie")             // i before e when preceded by c
        )

        val testWord1 = "believe"  // matches regex[0]
        val testWord2 = "ceiling"  // matches regex[2]
        val testWord3 = "science"  // matches regex[3]
        val testWord4 = "deceive"  // matches regex[2]

        assertEquals(1, regexes[0].findAll(testWord1).count())
        assertEquals(1, regexes[2].findAll(testWord2).count())
        assertEquals(1, regexes[3].findAll(testWord3).count())
        assertEquals(1, regexes[2].findAll(testWord4).count())
    }

    @Test
    fun testCountOccurrences() {
        val wordList = """
            believe
            ceiling
            science
            deceive
            """.trimIndent()

        val reader = BufferedReader(StringReader(wordList))
        val regexes = arrayOf(
            Regex("(^|[^c])ie"),     // i before e when not preceded by c
            Regex("(^|[^c])ei"),     // e before i when not preceded by c
            Regex("cei"),            // e before i when preceded by c
            Regex("cie")             // i before e when preceded by c
        )
        val counts = IntArray(4)

        var word = reader.readLine()
        while (word != null) {
            for (i in 0..3) counts[i] += regexes[i].findAll(word).toList().size
            word = reader.readLine()
        }

        assertArrayEquals(intArrayOf(1, 0, 2, 1), counts)
    }

    @Test
    fun testPrintResults() {
        val counts = intArrayOf(10, 4, 8, 3)
        val outputStream = java.io.ByteArrayOutputStream()
        val printStream = java.io.PrintStream(outputStream)
        System.setOut(printStream)

        printResults("testSource", counts)

        val output = outputStream.toString().trim()
        assertTrue(output.contains("Results for testSource"))
        assertTrue(output.contains("i before e except after c"))
        assertTrue(output.contains("for     10"))
        assertTrue(output.contains("against 4"))
        assertTrue(output.contains("sub-rule is plausible"))
        assertTrue(output.contains("e before i when preceded by c"))
        assertTrue(output.contains("for     8"))
        assertTrue(output.contains("against 3"))
        assertTrue(output.contains("sub-rule is plausible"))
        assertTrue(output.contains("rule is plausible"))
    }
}
