import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*
import kotlin.random.Random

class BestShuffleTest {

    @Test
    fun testAbracadabra() {
        val expectedOutputRegex = Regex("abracadabra\\s+[a-z]{11}\\s+\\(\\d\\)")
        val output = BestShuffle("abracadabra")
        assertTrue(expectedOutputRegex.matches(output))
        val score = output.substringAfterLast("(").substringBefore(")").toInt()
        assertTrue(score <= 2) //  'a' appears 5 times, so at best 2 can match. 'b','r' appear twice, so at best 2 can match.
    }

    @Test
    fun testSeesaw() {
        val expectedOutputRegex = Regex("seesaw\\s+[a-z]{6}\\s+\\(\\d\\)")
        val output = BestShuffle("seesaw")
        assertTrue(expectedOutputRegex.matches(output))

        val score = output.substringAfterLast("(").substringBefore(")").toInt()
        assertTrue(score <= 2) // 's', 'e' appear multiple times
    }

    @Test
    fun testElk() {
        val expectedOutputRegex = Regex("elk\\s+[a-z]{3}\\s+\\(0\\)")
        val output = BestShuffle("elk")
        assertTrue(expectedOutputRegex.matches(output))
        assertEquals("elk kle (0)", output) // Deterministic test - with this small input and the logic, we can predict the output.
    }


    @Test
    fun testGrrrrrr() {
        val expectedOutputRegex = Regex("grrrrrr\\s+[a-z]{7}\\s+\\(0\\)") 
        val output = BestShuffle("grrrrrr")
        assertTrue(expectedOutputRegex.matches(output))

         val score = output.substringAfterLast("(").substringBefore(")").toInt()
        assertTrue(score <= 1) // 'g' can match at most once.
    }

    @Test
    fun testUp() {
         val expectedOutputRegex = Regex("up\\s+[a-z]{2}\\s+\\(0\\)")
        val output = BestShuffle("up")
        assertTrue(expectedOutputRegex.matches(output))
        assertEquals("up pu (0)", output) // Deterministic test
    }

    @Test
    fun testA() {
        val expectedOutputRegex = Regex("a\\s+[a-z]{1}\\s+\\(1\\)")
        val output = BestShuffle("a")
        assertTrue(expectedOutputRegex.matches(output))
        assertEquals("a a (1)", output)
    }

     private fun CharArray.count(s1: String): Int {
        var count = 0
        for (i in indices)
            if (s1[i] == this[i]) count++
        return count
    }
}


