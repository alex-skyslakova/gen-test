import org.junit.Test
import kotlin.test.assertEquals

class LevenshteinDistanceTest {

    @Test
    fun testIdenticalStrings() {
        assertEquals(0, levenshtein("kitten", "kitten"))
        assertEquals(0, levenshtein("rosettacode", "rosettacode"))
        assertEquals(0, levenshtein("sleep", "sleep"))
    }

    @Test
    fun testOneEmptyString() {
        assertEquals(6, levenshtein("kitten", ""))
        assertEquals(11, levenshtein("", "rosettacode"))
        assertEquals(6, levenshtein("sleep", ""))
    }

    @Test
    fun testBothEmptyStrings() {
        assertEquals(0, levenshtein("", ""))
    }

    @Test
    fun testDifferentStrings() {
        assertEquals(3, levenshtein("kitten", "sitting"))
        assertEquals(8, levenshtein("rosettacode", "raisethysword"))
        assertEquals(5, levenshtein("sleep", "fleeting"))
    }

    @Test
    fun testReversedStrings() {
        assertEquals(3, levenshtein("kitten", "sitting"))
        assertEquals(3, levenshtein("sitting", "kitten"))
        assertEquals(8, levenshtein("rosettacode", "raisethysword"))
        assertEquals(8, levenshtein("raisethysword", "rosettacode"))
    }

    @Test
    fun testCaseInsensitivity() {
        assertEquals(3, levenshtein("KITTEN", "sitting"))
        assertEquals(3, levenshtein("kitten", "SITTING"))
    }

    @Test
    fun testSpecialCharacters() {
        assertEquals(1, levenshtein("kitten!", "kitten"))
        assertEquals(1, levenshtein("kitten", "kitten!"))
        assertEquals(2, levenshtein("kitten!", "kitten!!"))
    }

    @Test
    fun testLongStrings() {
        val longString1 = "a".repeat(1000)
        val longString2 = "b".repeat(1000)
        assertEquals(1000, levenshtein(longString1, longString2))
    }

    @Test
    fun testMixedCaseAndSpecialCharacters() {
        assertEquals(4, levenshtein("K!TTEN", "kitten!"))
        assertEquals(4, levenshtein("kitten!", "K!TTEN"))
    }
}
