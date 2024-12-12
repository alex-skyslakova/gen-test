import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BestShuffleTest {

    @Test
    fun testBestShuffle_SingleCharacter() {
        val result = BestShuffle("a")
        assertEquals("a a (0)", result)
    }

    @Test
    fun testBestShuffle_TwoDifferentCharacters() {
        val result = BestShuffle("ab")
        assertEquals("ab ba (0)", result)
    }

    @Test
    fun testBestShuffle_TwoSameCharacters() {
        val result = BestShuffle("aa")
        assertEquals("aa aa (2)", result)
    }

    @Test
    fun testBestShuffle_MultipleCharacters() {
        val result = BestShuffle("tree")
        assertEquals("tree eetr (0)", result)
    }

    @Test
    fun testBestShuffle_AllSameCharacters() {
        val result = BestShuffle("grrrrrr")
        assertEquals("grrrrrr rgrrrrr (0)", result)
    }

    @Test
    fun testBestShuffle_MixedCharacters() {
        val result = BestShuffle("abracadabra")
        val shuffled = result.split(" ")[1]
        val score = result.split(" ")[2].replace("(", "").replace(")", "").toInt()
        assertEquals("abracadabra", result.split(" ")[0])
        assertTrue(shuffled.length == 11)
        assertTrue(score >= 0 && score <= 11)
    }

    @Test
    fun testBestShuffle_EmptyString() {
        val result = BestShuffle("")
        assertEquals("  (0)", result)
    }

    @Test
    fun testBestShuffle_SpecialCharacters() {
        val result = BestShuffle("!@#$%^&*()")
        val shuffled = result.split(" ")[1]
        val score = result.split(" ")[2].replace("(", "").replace(")", "").toInt()
        assertEquals("!@#$%^&*()", result.split(" ")[0])
        assertTrue(shuffled.length == 10)
        assertTrue(score >= 0 && score <= 10)
    }

    @Test
    fun testBestShuffle_MixedCase() {
        val result = BestShuffle("AbRaCaDaBrA")
        val shuffled = result.split(" ")[1]
        val score = result.split(" ")[2].replace("(", "").replace(")", "").toInt()
        assertEquals("AbRaCaDaBrA", result.split(" ")[0])
        assertTrue(shuffled.length == 11)
        assertTrue(score >= 0 && score <= 11)
    }

    @Test
    fun testBestShuffle_LongString() {
        val result = BestShuffle("ThisIsALongStringForTesting")
        val shuffled = result.split(" ")[1]
        val score = result.split(" ")[2].replace("(", "").replace(")", "").toInt()
        assertEquals("ThisIsALongStringForTesting", result.split(" ")[0])
        assertTrue(shuffled.length == 27)
        assertTrue(score >= 0 && score <= 27)
    }
}
