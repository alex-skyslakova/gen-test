import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class JaroSimilarityTest {

    @Test
    fun testExactMatch() {
        assertEquals(1.0, Jaro.distance("IDENTICAL", "IDENTICAL"), 0.0001)
    }

    @Test
    fun testCompletelyDifferent() {
        assertEquals(0.0, Jaro.distance("ABC", "XYZ"), 0.0001)
    }

    @Test
    fun testExample1() {
        assertEquals(0.9444, Jaro.distance("MARTHA", "MARHTA"), 0.0001)
    }

    @Test
    fun testExample2() {
        assertEquals(0.7667, Jaro.distance("DIXON", "DICKSONX"), 0.0001)
    }

    @Test
    fun testExample3() {
        assertEquals(0.8963, Jaro.distance("JELLYFISH", "SMELLYFISH"), 0.0001)
    }

    @Test
    fun testEmptyStrings() {
        assertEquals(1.0, Jaro.distance("", ""), 0.0001)
    }

    @Test
    fun testOneEmptyString() {
        assertEquals(0.0, Jaro.distance("NONEMPTY", ""), 0.0001)
        assertEquals(0.0, Jaro.distance("", "NONEMPTY"), 0.0001)
    }

    @Test
    fun testSingleCharacterMatch() {
        assertEquals(1.0, Jaro.distance("A", "A"), 0.0001)
    }

    @Test
    fun testSingleCharacterNoMatch() {
        assertEquals(0.0, Jaro.distance("A", "B"), 0.0001)
    }

    @Test
    fun testPartialMatch() {
        assertEquals(0.7333, Jaro.distance("CRATE", "TRACE"), 0.0001)
    }
}
