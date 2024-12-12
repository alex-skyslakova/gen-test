import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class IBeforeEExceptAfterCTest {

    @Test
    fun testIsPlausible() {
        assertTrue(isPlausible(10, 3))
        assertFalse(isPlausible(5, 3))
        assertTrue(isPlausible(6, 2))
        assertFalse(isPlausible(4, 2))
    }

    @Test
    fun testPrintResults() {
        val counts = intArrayOf(10, 3, 6, 2)
        printResults("Test Source", counts)
    }

    @Test
    fun testMainUnixDict() {
        val url = URL("http://wiki.puzzlers.org/pub/wordlists/unixdict.txt")
        val isr = InputStreamReader(url.openStream())
        val reader = BufferedReader(isr)
        val regexes = arrayOf(
            Regex("(^|[^c])ie"),
            Regex("(^|[^c])ei"),
            Regex("cei"),
            Regex("cie")
        )
        val counts = IntArray(4)
        var word = reader.readLine()
        while (word != null) {
            for (i in 0..3) counts[i] += regexes[i].findAll(word).toList().size
            word = reader.readLine()
        }
        reader.close()
        assertTrue(isPlausible(counts[0], counts[1]))
        assertTrue(isPlausible(counts[2], counts[3]))
    }

    @Test
    fun testMainBNC() {
        val url = URL("http://ucrel.lancs.ac.uk/bncfreq/lists/1_2_all_freq.txt")
        val isr = InputStreamReader(url.openStream())
        val reader = BufferedReader(isr)
        val regexes = arrayOf(
            Regex("(^|[^c])ie"),
            Regex("(^|[^c])ei"),
            Regex("cei"),
            Regex("cie")
        )
        val counts = IntArray(4)
        reader.readLine() // read header line
        var line = reader.readLine()
        val splitter = Regex("""(\t+|\s+)""")
        while (line != null) {
            val words = line.split(splitter)
            if (words.size == 4) {
                for (i in 0..3) counts[i] += regexes[i].findAll(words[1]).toList().size * words[3].toInt()
            }
            line = reader.readLine()
        }
        reader.close()
        assertTrue(isPlausible(counts[0], counts[1]))
        assertTrue(isPlausible(counts[2], counts[3]))
    }
}
