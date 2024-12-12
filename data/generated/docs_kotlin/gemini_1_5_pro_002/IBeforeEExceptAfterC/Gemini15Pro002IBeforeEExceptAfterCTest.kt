import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader
import java.net.URL
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IBeforeETest {

    private fun testPlausible(n1: Int, n2: Int) = n1 > 2 * n2

    private fun processWords(reader: BufferedReader, regexes: Array<Regex>): IntArray {
        val counts = IntArray(4)
        var word = reader.readLine()
        while (word != null) {
            for (i in 0..3) counts[i] += regexes[i].findAll(word).count()
            word = reader.readLine()
        }
        return counts
    }
    
    @Test
    fun testIsPlausible() {
        assertTrue(testPlausible(3, 1))
        assertFalse(testPlausible(2, 1))
        assertFalse(testPlausible(1, 1))
    }

    @Test
    fun testUnixDict() {
        val sampleData = """
            tie
            their
            ceiling
            believe
            receive
            weird
            science
        """.trimIndent()

        val reader = BufferedReader(StringReader(sampleData))
        val regexes = arrayOf(
            Regex("(^|[^c])ie"),
            Regex("(^|[^c])ei"),
            Regex("cei"),
            Regex("cie")
        )
        val counts = processWords(reader, regexes)
        assertEquals(2, counts[0]) // tie, believe
        assertEquals(1, counts[1]) // their
        assertEquals(2, counts[2]) // ceiling, receive
        assertEquals(1, counts[3]) // science

        assertTrue(testPlausible(counts[0], counts[1]))
        assertTrue(testPlausible(counts[2], counts[3]))
    }


    @Test
    fun testBritishNationalCorpus() {
        val sampleData = """
            Rank	Word	Freq	Range
            1	the	2206719	A
            2	of	1301747	A
            3	and	1221763	A
            4	to	1026278	A
            5	a	997660	A
            6	in	858516	A
            7	is	637724	A
            8	you	489672	A
            9	that	468950	A
            10	it	442591	A
            11	he	409868	A
            12	was	394875	A
            13	for	363469	A
            14	on	312363	A
            15	are	299107	A
            16	with	289471	A
            17	as	272299	A
            18	I	268314	A
            
            2500 their 13739 A
            2501 ceiling 13732 A
            2502 believe 13725 A
            2503 receive 13718 A
            2504 weird 13711 A
            2505 science 13704 A
        """.trimIndent()

        val reader = BufferedReader(StringReader(sampleData))
        val regexes = arrayOf(
            Regex("(^|[^c])ie"),
            Regex("(^|[^c])ei"),
            Regex("cei"),
            Regex("cie")
        )
        val counts = IntArray(4)
        reader.readLine() // Skip header
        var line = reader.readLine()
        val splitter = Regex("""(\t+|\s+)""")
        while (line != null) {
            val words = line.split(splitter)
            if (words.size == 4) { // first element is empty after split due to leading whitespace
                for (i in 0..3) counts[i] += regexes[i].findAll(words[1]).count() * words[3].replace("[^0-9]".toRegex(), "").toIntOrNull() ?: 0
            }
            line = reader.readLine()
        }

        assertTrue(testPlausible(counts[0], counts[1])) // Likely to pass with large enough corpus data
        assertTrue(testPlausible(counts[2], counts[3])) // Likely to pass with large enough corpus data
    }




}
