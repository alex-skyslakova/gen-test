import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LevenshteinDistanceTest {

    @Test
    fun testEmptyStrings() {
        assertEquals(0, levenshtein("", ""))
    }

    @Test
    fun testEmptyStringAndNonEmptyString() {
        assertEquals(5, levenshtein("", "hello"))
        assertEquals(3, levenshtein("abc", ""))
    }

    @Test
    fun testEqualStrings() {
        assertEquals(0, levenshtein("hello", "hello"))
        assertEquals(0, levenshtein("world", "world"))
    }

    @Test
    fun testKittenSitting() {
        assertEquals(3, levenshtein("kitten", "sitting"))
    }

    @Test
    fun testRosettacodeRaisethysword() {
        assertEquals(8, levenshtein("rosettacode", "raisethysword"))
    }

    @Test
    fun testSleepFleeting() {
        assertEquals(5, levenshtein("sleep", "fleeting"))
    }

    @Test
    fun testSubstitution() {
        assertEquals(1, levenshtein("cat", "hat"))
    }
    
    @Test
    fun testInsertion() {
        assertEquals(1, levenshtein("cat", "cats"))
    }

    @Test
    fun testDeletion() {
        assertEquals(1, levenshtein("cats", "cat"))
    }

    @Test
    fun testReversedStrings() {
        assertEquals(levenshtein("hello", "world"), levenshtein("olleh", "dlrow"))
    }


    @Test
    fun testLongStrings() {
        val str1 = "This is a very long string"
        val str2 = "This is another long string, slightly different"
        assertEquals(11, levenshtein(str1, str2))
    }

}
