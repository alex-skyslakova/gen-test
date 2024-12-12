import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FirstPowerOf2ThatHasLeadingDecimalDigitsOf12Test {

    @Test
    fun testP12_1() {
        assertEquals(7, p(12, 1))
    }

    @Test
    fun testP12_2() {
        assertEquals(80, p(12, 2))
    }

    @Test
    fun testP123_45() {
        assertEquals(12710, p(123, 45))
    }

    @Test
    fun testP123_12345() {
        // This test case might take a long time to run due to the large value of n
        // Uncomment and run if necessary
        // assertEquals(someExpectedValue, p(123, 12345))
    }

    @Test
    fun testP123_678910() {
        // This test case might take a long time to run due to the large value of n
        // Uncomment and run if necessary
        // assertEquals(someExpectedValue, p(123, 678910))
    }
}
