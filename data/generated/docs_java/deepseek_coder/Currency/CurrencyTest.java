import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {

    @Test
    public void testSubtotalCalculation() {
        MathContext mc = MathContext.DECIMAL128;
        Map<Currency.MenuItem, BigDecimal> order = new HashMap<>();
        order.put(Currency.MenuItem.Hamburger, new BigDecimal("4000000000000000"));
        order.put(Currency.MenuItem.Milkshake, new BigDecimal("2"));

        BigDecimal expectedSubtotal = new BigDecimal("22000000000000005.72");

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Currency.MenuItem it : order.keySet())
            subtotal = subtotal.add(it.price.multiply(order.get(it), mc));

        assertEquals(expectedSubtotal, subtotal);
    }

    @Test
    public void testTaxCalculation() {
        MathContext mc = MathContext.DECIMAL128;
        BigDecimal subtotal = new BigDecimal("22000000000000005.72");
        BigDecimal taxRate = new BigDecimal("7.65");

        BigDecimal expectedTax = new BigDecimal("1683000000000000.44");

        BigDecimal tax = taxRate.divide(new BigDecimal("100"), mc);
        tax = subtotal.multiply(tax, mc);

        assertEquals(expectedTax, tax);
    }

    @Test
    public void testTotalCalculation() {
        MathContext mc = MathContext.DECIMAL128;
        BigDecimal subtotal = new BigDecimal("22000000000000005.72");
        BigDecimal tax = new BigDecimal("1683000000000000.44");

        BigDecimal expectedTotal = new BigDecimal("23683000000000006.16");

        BigDecimal total = subtotal.add(tax);

        assertEquals(expectedTotal, total);
    }
}
