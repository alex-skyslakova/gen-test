import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class EntropyNarcissistTest {

    @Test
    fun testLog2() {
        assertEquals(0.0, log2(1.0), 0.0001)
        assertEquals(1.0, log2(2.0), 0.0001)
        assertEquals(2.0, log2(4.0), 0.0001)
        assertEquals(3.0, log2(8.0), 0.0001)
        assertEquals(0.5, log2(Math.sqrt(2.0)), 0.0001)
    }

    @Test
    fun testShannon() {
        assertEquals(0.0, shannon(""), 0.0001)
        assertEquals(0.0, shannon("a"), 0.0001)
        assertEquals(1.0, shannon("01"), 0.0001)
        assertEquals(2.0, shannon("0123"), 0.0001)
        assertEquals(3.0, shannon("01234567"), 0.0001)
        assertEquals(1.58496, shannon("00001111"), 0.0001)
        assertEquals(2.0, shannon("00110011"), 0.0001)
    }

    @Test
    fun testShannonWithSpecialCharacters() {
        assertEquals(1.58496, shannon("!@#$%^&*"), 0.0001)
        assertEquals(2.0, shannon("!@#$%^&*()"), 0.0001)
        assertEquals(3.0, shannon("!@#$%^&*()_+"), 0.0001)
    }

    @Test
    fun testShannonWithMixedCharacters() {
        assertEquals(3.0, shannon("aA1!bB2@cC3#"), 0.0001)
        assertEquals(4.0, shannon("aA1!bB2@cC3#dD4$"), 0.0001)
    }

    @Test
    fun testShannonWithLargeInput() {
        val largeInput = "a".repeat(1000) + "b".repeat(1000) + "c".repeat(1000)
        assertEquals(1.58496, shannon(largeInput), 0.0001)
    }

    @Test
    fun testShannonWithEmptyString() {
        assertEquals(0.0, shannon(""), 0.0001)
    }

    @Test
    fun testShannonWithSingleCharacter() {
        assertEquals(0.0, shannon("a"), 0.0001)
    }

    @Test
    fun testShannonWithUniformDistribution() {
        assertEquals(1.0, shannon("01"), 0.0001)
        assertEquals(2.0, shannon("0123"), 0.0001)
        assertEquals(3.0, shannon("01234567"), 0.0001)
    }

    @Test
    fun testShannonWithNonUniformDistribution() {
        assertEquals(1.58496, shannon("00001111"), 0.0001)
        assertEquals(2.0, shannon("00110011"), 0.0001)
    }
}
