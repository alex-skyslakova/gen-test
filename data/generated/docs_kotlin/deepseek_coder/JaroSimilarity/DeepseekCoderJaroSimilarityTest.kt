import org.junit.Assert.assertEquals
import org.junit.Test

class JaroTest {

    @Test
    fun testExactMatch() {
        assertEquals(1.0, Jaro.distance("MARTHA", "MARTHA"), 0.001)
    }

    @Test
    fun testNoMatch() {
        assertEquals(0.0, Jaro.distance("AAAA", "BBBB"), 0.001)
    }

    @Test
    fun testPartialMatch() {
        assertEquals(0.822, Jaro.distance("MARTHA", "MARHTA"), 0.001)
    }

    @Test
    fun testDifferentLengths() {
        assertEquals(0.767, Jaro.distance("DIXON", "DICKSONX"), 0.001)
    }

    @Test
    fun testTranspositions() {
        assertEquals(0.896, Jaro.distance("JELLYFISH", "SMELLYFISH"), 0.001)
    }

    @Test
    fun testEmptyStrings() {
        assertEquals(1.0, Jaro.distance("", ""), 0.001)
    }

    @Test
    fun testOneEmptyString() {
        assertEquals(0.0, Jaro.distance("MARTHA", ""), 0.001)
        assertEquals(0.0, Jaro.distance("", "MARTHA"), 0.001)
    }

    @Test
    fun testSingleCharacterMatch() {
        assertEquals(1.0, Jaro.distance("A", "A"), 0.001)
    }

    @Test
    fun testSingleCharacterNoMatch() {
        assertEquals(0.0, Jaro.distance("A", "B"), 0.001)
    }

    @Test
    fun testCaseInsensitivity() {
        assertEquals(0.822, Jaro.distance("martha", "MARHTA"), 0.001)
    }

    @Test
    fun testDifferentCharacterSets() {
        assertEquals(0.0, Jaro.distance("1234", "ABCD"), 0.001)
    }

    @Test
    fun testSpecialCharacters() {
        assertEquals(0.822, Jaro.distance("M@RTHA", "M@RHTA"), 0.001)
    }
}
