import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class HammingNumbersTest {

    @Test
    fun testFirstTwentyHammingNumbers() {
        val expected = listOf(
            BigInteger.valueOf(1), BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(4),
            BigInteger.valueOf(5), BigInteger.valueOf(6), BigInteger.valueOf(8), BigInteger.valueOf(9),
            BigInteger.valueOf(10), BigInteger.valueOf(12), BigInteger.valueOf(15), BigInteger.valueOf(16),
            BigInteger.valueOf(18), BigInteger.valueOf(20), BigInteger.valueOf(24), BigInteger.valueOf(25),
            BigInteger.valueOf(27), BigInteger.valueOf(30), BigInteger.valueOf(32), BigInteger.valueOf(36)
        )
        for (i in 1..20) {
            assertEquals(expected[i - 1], hamming(i), "Hamming number at position $i is incorrect")
        }
    }

    @Test
    fun test1691stHammingNumber() {
        val expected = BigInteger.valueOf(2125764000)
        assertEquals(expected, hamming(1691), "1691st Hamming number is incorrect")
    }

    @Test
    fun testOneMillionthHammingNumber() {
        // This test assumes that the system can handle large numbers and computations
        val expected = BigInteger("519312780448000")
        assertEquals(expected, hamming(1000000), "One millionth Hamming number is incorrect")
    }
}
