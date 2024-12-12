import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FirstPowerOf2LeadingDigitsTest {

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
        assertEquals(207237, p(123, 12345))
    }

    @Test
    fun testP123_678910() {
        assertEquals(11279428, p(123, 678910))
    }


}
