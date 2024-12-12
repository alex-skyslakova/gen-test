import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LevenshteinDistanceTest {

    @Test
    fun testIdenticalStrings() {
        assertEquals(0, levenshtein("same", "same"))
    }

    @Test
    fun testEmptyFirstString() {
        assertEquals(5, levenshtein("", "hello"))
    }

    @Test
    fun testEmptySecondString() {
        assertEquals(5, levenshtein("world", ""))
    }

    @Test
    fun testBothEmptyStrings() {
        assertEquals(0, levenshtein("", ""))
    }

    @Test
    fun testSingleCharacterDifference() {
        assertEquals(1, levenshtein("cat", "bat"))
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
    fun testSubstitution() {
        assertEquals(1, levenshtein("cat", "cut"))
    }

    @Test
    fun testComplexCase1() {
        assertEquals(3, levenshtein("kitten", "sitting"))
    }

    @Test
    fun testComplexCase2() {
        assertEquals(8, levenshtein("rosettacode", "raisethysword"))
    }

    @Test
    fun testComplexCase3() {
        assertEquals(5, levenshtein("sleep", "fleeting"))
    }

    @Test
    fun testReversedStrings() {
        assertEquals(3, levenshtein("kitten", "sitting"))
        assertEquals(3, levenshtein("sitting", "kitten"))
    }
}
