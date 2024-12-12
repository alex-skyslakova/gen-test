import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.MathContext

class CurrencyTest {

    @Test
    fun testTotalPriceBeforeTax() {
        val mc = MathContext.DECIMAL128
        val nHamburger = BigDecimal("4000000000000000", mc)
        val pHamburger = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        
        val expectedTotalPriceBeforeTax = BigDecimal("22000000000000005.72")
        
        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        assertEquals(expectedTotalPriceBeforeTax, price.setScale(2, BigDecimal.ROUND_HALF_UP))
    }

    @Test
    fun testTax() {
        val mc = MathContext.DECIMAL128
        val nHamburger = BigDecimal("4000000000000000", mc)
        val pHamburger = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        val taxRate = BigDecimal("0.0765")
        
        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate
        val expectedTax = BigDecimal("1683000000000000.44")
        
        assertEquals(expectedTax, tax.setScale(2, BigDecimal.ROUND_HALF_UP))
    }

    @Test
    fun testTotalPriceAfterTax() {
        val mc = MathContext.DECIMAL128
        val nHamburger = BigDecimal("4000000000000000", mc)
        val pHamburger = BigDecimal("5.50")
        val nMilkshakes = BigDecimal("2", mc)
        val pMilkshakes = BigDecimal("2.86")
        val taxRate = BigDecimal("0.0765")
        
        val price = nHamburger * pHamburger + nMilkshakes * pMilkshakes
        val tax = price * taxRate
        val totalPriceAfterTax = price + tax
        val expectedTotalPriceAfterTax = BigDecimal("23683000000000006.16")
        
        assertEquals(expectedTotalPriceAfterTax, totalPriceAfterTax.setScale(2, BigDecimal.ROUND_HALF_UP))
    }
}
