import org.junit.Test
import kotlin.test.assertEquals

class GrayCodeTest {

    @Test
    fun testEncode() {
        // Test encoding of various numbers
        assertEquals(0b000, Gray.encode(0b000))
        assertEquals(0b001, Gray.encode(0b001))
        assertEquals(0b011, Gray.encode(0b010))
        assertEquals(0b010, Gray.encode(0b011))
        assertEquals(0b110, Gray.encode(0b100))
        assertEquals(0b111, Gray.encode(0b101))
        assertEquals(0b101, Gray.encode(0b110))
        assertEquals(0b100, Gray.encode(0b111))

        // Test encoding of boundary values
        assertEquals(0b00000, Gray.encode(0b00000))
        assertEquals(0b10000, Gray.encode(0b11111))
    }

    @Test
    fun testDecode() {
        // Test decoding of various Gray codes
        assertEquals(0b000, Gray.decode(0b000))
        assertEquals(0b001, Gray.decode(0b001))
        assertEquals(0b010, Gray.decode(0b011))
        assertEquals(0b011, Gray.decode(0b010))
        assertEquals(0b100, Gray.decode(0b110))
        assertEquals(0b101, Gray.decode(0b111))
        assertEquals(0b110, Gray.decode(0b101))
        assertEquals(0b111, Gray.decode(0b100))

        // Test decoding of boundary values
        assertEquals(0b00000, Gray.decode(0b00000))
        assertEquals(0b11111, Gray.decode(0b10000))
    }

    @Test
    fun testEncodeDecodeConsistency() {
        // Test that encoding and then decoding returns the original number
        for (i in 0..31) {
            val encoded = Gray.encode(i)
            val decoded = Gray.decode(encoded)
            assertEquals(i, decoded)
        }
    }

    @Test
    fun testDecodeEncodeConsistency() {
        // Test that decoding and then encoding returns the original Gray code
        for (i in 0..31) {
            val decoded = Gray.decode(i)
            val encoded = Gray.encode(decoded)
            assertEquals(i, encoded)
        }
    }
}
