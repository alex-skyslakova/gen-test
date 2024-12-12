import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

class HickersonTest {

    @Test
    fun testAlmostInteger_1to17() {
        val expected = listOf(
            true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true
        )
        for (n in 1..17) {
            assertEquals(expected[n - 1], Hickerson.almostInteger(n), "Failed for n = $n")
        }
    }


    @Test
    fun testAlmostInteger_18() {
        assertFalse(Hickerson.almostInteger(18))
    }

    @Test
    fun testAlmostInteger_1() {
        assertTrue(Hickerson.almostInteger(1))
    }

    @Test
    fun testAlmostInteger_2() {
        assertTrue(Hickerson.almostInteger(2))
    }


    @Test
    fun testAlmostInteger_LargeN_NotAlmostInteger(){
        assertFalse(Hickerson.almostInteger(50))
    }

    @Test
    fun testAlmostInteger_EdgeCaseZero(){
         val a = BigDecimal(LN2).pow(0 + 1) * BigDecimal(2)
        var nn = 0
        var f = 1L // factorial of 0 is 1
       val b = BigDecimal(f).divide(a, MathContext.DECIMAL128)
        val c = b.movePointRight(1).toBigInteger() % BigInteger.TEN
        val expected = c.toString().matches(Regex("[09]"))
        assertEquals(expected, Hickerson.almostInteger(0))
    }


    companion object{
        private const val LN2 = "0.693147180559945309417232121458"
    }

}
