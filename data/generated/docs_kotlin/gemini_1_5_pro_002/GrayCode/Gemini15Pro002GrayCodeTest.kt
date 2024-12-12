import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class GrayTest {

    @Test
    fun testEncodeDecodeZero() {
        val input = 0
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }

    @Test
    fun testEncodeDecodeOne() {
        val input = 1
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }

    @Test
    fun testEncodeDecodeTwo() {
        val input = 2
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }

    @Test
    fun testEncodeDecodeThree() {
        val input = 3
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }
    
    @Test
    fun testEncodeDecodeFour() {
        val input = 4
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }

    @Test
    fun testEncodeDecodeSeven() {
        val input = 7
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }

    @Test
    fun testEncodeDecodeFifteen() {
        val input = 15
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }

    @Test
    fun testEncodeDecodeThirtyOne() {
        val input = 31
        val encoded = Gray.encode(input)
        val decoded = Gray.decode(encoded)
        assertEquals(input, decoded)
    }


    @Test
    fun testEncodeAllNumbersUpTo31() {
        for (i in 0..31) {
            val encoded = Gray.encode(i)
            val decoded = Gray.decode(encoded)
            assertEquals(i, decoded)
        }
    }


    @Test
    fun testSpecificEncodeCases(){
        assertEquals(0b11111, Gray.encode(15)) // Example from provided code output
        assertEquals(0b10000, Gray.encode(16))
    }
    
    @Test
    fun testSpecificDecodeCases(){
        assertEquals(15, Gray.decode(0b11111)) // Example from provided code output
        assertEquals(16, Gray.decode(0b10000))

    }

}
