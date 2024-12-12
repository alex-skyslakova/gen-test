import org.junit.Test
import java.math.BigDecimal
import java.math.MathContext
import kotlin.test.assertEquals

class CurrencyTest {

    @Test
    fun testTotalPriceBeforeTax() {
        val mc = MathContext.DECIMAL128
        val nHamburger  = BigDecimal("4000000000000000", mc)
        val pHamburger  = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val expected = BigDecimal("22000000000000005.72")
        assertEquals(expected, price)
    }

    @Test
    fun testTax() {
        val mc = MathContext.DECIMAL128
        val nHamburger  = BigDecimal("4000000000000000", mc)
        val pHamburger  = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        val taxRate     = BigDecimal("0.0765")
        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate
        val expected = BigDecimal("1683000000000000.44")
        assertEquals(expected, tax)
    }

    @Test
    fun testTotalPriceAfterTax() {
        val mc = MathContext.DECIMAL128
        val nHamburger  = BigDecimal("4000000000000000", mc)
        val pHamburger  = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        val taxRate     = BigDecimal("0.0765")
        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate
        val totalWithTax = price + tax
        val expected = BigDecimal("23683000000000006.16")
        assertEquals(expected, totalWithTax)
    }
}
