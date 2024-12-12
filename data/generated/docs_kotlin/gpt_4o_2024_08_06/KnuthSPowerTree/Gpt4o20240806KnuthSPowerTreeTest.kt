import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class KnuthSPowerTreeTest {

    @Test
    fun testPowerOfTwo() {
        val expectedResults = listOf(
            BigDecimal(1), BigDecimal(2), BigDecimal(4), BigDecimal(8), BigDecimal(16),
            BigDecimal(32), BigDecimal(64), BigDecimal(128), BigDecimal(256), BigDecimal(512),
            BigDecimal(1024), BigDecimal(2048), BigDecimal(4096), BigDecimal(8192), BigDecimal(16384),
            BigDecimal(32768), BigDecimal(65536), BigDecimal(131072)
        )
        for (n in 0..17) {
            assertEquals(expectedResults[n], treePow(2.0, n))
        }
    }

    @Test
    fun testPowerOf3191() {
        val expected = BigDecimal("3.191").pow(191)
        assertEquals(expected, treePow(3.191, 191))
    }

    @Test
    fun testPowerOf1Point181() {
        val expected = BigDecimal("1.181").pow(81)
        assertEquals(expected, treePow(1.181, 81))
    }

    @Test
    fun testZeroPower() {
        assertEquals(BigDecimal.ONE, treePow(2.0, 0))
        assertEquals(BigDecimal.ONE, treePow(3.0, 0))
        assertEquals(BigDecimal.ONE, treePow(1.1, 0))
    }

    @Test
    fun testNegativePower() {
        // Assuming negative powers are supported
        val expected = BigDecimal.ONE.divide(BigDecimal("2").pow(3))
        assertEquals(expected, treePow(2.0, -3))
    }
}
