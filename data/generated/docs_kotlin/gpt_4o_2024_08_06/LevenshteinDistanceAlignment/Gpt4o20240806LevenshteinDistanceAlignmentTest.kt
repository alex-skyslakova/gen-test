import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class LevenshteinDistanceAlignmentTest {

    @Test
    fun testAlignmentPlacePalace() {
        val result = levenshteinAlign("place", "palace")
        val expected = arrayOf("P-LACE", "PALACE")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentRosettaCodeRaiseThySword() {
        val result = levenshteinAlign("rosettacode", "raisethysword")
        val expected = arrayOf("ro-settacode", "raisethysword")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentEmptyStrings() {
        val result = levenshteinAlign("", "")
        val expected = arrayOf("", "")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentFirstStringEmpty() {
        val result = levenshteinAlign("", "nonempty")
        val expected = arrayOf("--------", "nonempty")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentSecondStringEmpty() {
        val result = levenshteinAlign("nonempty", "")
        val expected = arrayOf("nonempty", "--------")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentIdenticalStrings() {
        val result = levenshteinAlign("identical", "identical")
        val expected = arrayOf("identical", "identical")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentSingleCharacterDifference() {
        val result = levenshteinAlign("cat", "cut")
        val expected = arrayOf("cat", "cut")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentInsertion() {
        val result = levenshteinAlign("abc", "abxc")
        val expected = arrayOf("ab-c", "abxc")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentDeletion() {
        val result = levenshteinAlign("abxc", "abc")
        val expected = arrayOf("abxc", "ab-c")
        assertArrayEquals(expected, result)
    }

    @Test
    fun testAlignmentComplex() {
        val result = levenshteinAlign("kitten", "sitting")
        val expected = arrayOf("kitten-", "sitt-ing")
        assertArrayEquals(expected, result)
    }
}
