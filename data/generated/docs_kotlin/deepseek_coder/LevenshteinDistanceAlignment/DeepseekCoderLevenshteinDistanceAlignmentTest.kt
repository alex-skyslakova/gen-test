import org.junit.Assert.assertArrayEquals
import org.junit.Test

class LevenshteinDistanceAlignmentTest {

    @Test
    fun testLevenshteinAlign_SameStrings() {
        val result = levenshteinAlign("hello", "hello")
        assertArrayEquals(arrayOf("hello", "hello"), result)
    }

    @Test
    fun testLevenshteinAlign_OneInsertion() {
        val result = levenshteinAlign("place", "palace")
        assertArrayEquals(arrayOf("p-lace", "palace"), result)
    }

    @Test
    fun testLevenshteinAlign_OneDeletion() {
        val result = levenshteinAlign("palace", "place")
        assertArrayEquals(arrayOf("palace", "p-lace"), result)
    }

    @Test
    fun testLevenshteinAlign_OneSubstitution() {
        val result = levenshteinAlign("cat", "bat")
        assertArrayEquals(arrayOf("cat", "bat"), result)
    }

    @Test
    fun testLevenshteinAlign_ComplexExample() {
        val result = levenshteinAlign("rosettacode", "raisethysword")
        assertArrayEquals(arrayOf("r-oset-tacode", "raisethysword"), result)
    }

    @Test
    fun testLevenshteinAlign_EmptyStrings() {
        val result = levenshteinAlign("", "")
        assertArrayEquals(arrayOf("", ""), result)
    }

    @Test
    fun testLevenshteinAlign_OneEmptyString() {
        val result = levenshteinAlign("hello", "")
        assertArrayEquals(arrayOf("hello", "-----"), result)
    }

    @Test
    fun testLevenshteinAlign_CaseInsensitive() {
        val result = levenshteinAlign("Hello", "hello")
        assertArrayEquals(arrayOf("hello", "hello"), result)
    }

    @Test
    fun testLevenshteinAlign_MultipleOperations() {
        val result = levenshteinAlign("kitten", "sitting")
        assertArrayEquals(arrayOf("k-itt-en", "sitting"), result)
    }

    @Test
    fun testLevenshteinAlign_DifferentLengths() {
        val result = levenshteinAlign("abc", "abcdef")
        assertArrayEquals(arrayOf("abc---", "abcdef"), result)
    }
}
