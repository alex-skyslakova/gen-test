import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext
import kotlin.test.assertEquals

class CurrencyTest {

    @Test
    fun testCalculations() {
        val mc = MathContext.DECIMAL128
        val nHamburger = BigDecimal("4000000000000000", mc)
        val pHamburger = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        val taxRate = BigDecimal("0.0765")

        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate


        assertEquals(BigDecimal("22000000000000005.72"), price)
        assertEquals(BigDecimal("1683000000000000.4388"), tax) // Test the unrounded tax value
        assertEquals(BigDecimal("23683000000000006.1588"), price + tax) //Test unrounded final value



        val fmt = "%20.2f"

        // Test formatted output for display, important for verifying rounding behavior
        assertEquals("22000000000000005.72", fmt.format(price))
        assertEquals("1683000000000000.44", fmt.format(tax))
        assertEquals("23683000000000006.16", fmt.format(price + tax))

    }

    @Test
    fun testZeroValues() {
         val mc = MathContext.DECIMAL128
        val nHamburger = BigDecimal("0", mc)
        val pHamburger = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("0", mc)
        val pMilkshakes = BigDecimal("2.86")
        val taxRate = BigDecimal("0.0765")

        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate
        val fmt = "%20.2f"

       assertEquals(BigDecimal("0.00"), price)
        assertEquals(BigDecimal("0.00"), tax) // Test the unrounded tax value
        assertEquals(BigDecimal("0.00"), price + tax)
        assertEquals("                0.00", fmt.format(price))
        assertEquals("                0.00", fmt.format(tax))
        assertEquals("                0.00", fmt.format(price + tax))
    }



    @Test
    fun testLargeQuantitySmallPrice(){
        val mc = MathContext.DECIMAL128
        val nHamburger = BigDecimal("4000000000000000", mc)
        val pHamburger = BigDecimal("0.01")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("0.01")
        val taxRate = BigDecimal("0.0765")

        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate
        val fmt = "%20.2f"

       assertEquals(BigDecimal("40000000000000.02"), price)
        assertEquals(BigDecimal("3060000000000.001530"), tax) // Test the unrounded tax value
        assertEquals(BigDecimal("40306000000000.021530"), price + tax)
        assertEquals(" 40000000000000.02", fmt.format(price))
        assertEquals("  3060000000000.00", fmt.format(tax))
        assertEquals(" 40306000000000.02", fmt.format(price + tax))
    }
}

