import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class KnuthSPowerTreeTest {

    @BeforeEach
    fun setUp() {
        // Reset the global variables before each test
        p = mutableMapOf(1 to 0)
        lvl = mutableListOf(listOf(1))
    }

    @Test
    fun testPathForZero() {
        val result = path(0)
        assertTrue(result.isEmpty())
    }

    @Test
    fun testPathForSmallNumbers() {
        val result = path(5)
        assertEquals(listOf(1, 2, 4, 5), result)
    }

    @Test
    fun testPathForLargeNumber() {
        val result = path(191)
        assertEquals(listOf(1, 2, 3, 5, 10, 20, 40, 80, 160, 191), result)
    }

    @Test
    fun testTreePowForSmallIntegers() {
        val result = treePow(2.0, 5)
        assertEquals(BigDecimal("32"), result)
    }

    @Test
    fun testTreePowForLargeIntegers() {
        val result = treePow(3.0, 191)
        assertEquals(BigDecimal("13494588674281093803728157396523884917402502294030101914066705367021922008906273586058258347"), result)
    }

    @Test
    fun testTreePowForNonIntegralPower() {
        val result = treePow(1.1, 81)
        assertEquals(BigDecimal("2253.24023604401"), result)
    }

    @Test
    fun testShowPowForIntegralPower() {
        val expectedOutput = "5: [1, 2, 4, 5]\n2 ^ 5 = 32\n\n"
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        showPow(2.0, 5)
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testShowPowForNonIntegralPower() {
        val expectedOutput = "81: [1, 2, 3, 5, 10, 20, 40, 80, 81]\n1.100000 ^ 81 = 2253.240236\n\n"
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        showPow(1.1, 81, false)
        assertEquals(expectedOutput, outputStream.toString())
    }
}
