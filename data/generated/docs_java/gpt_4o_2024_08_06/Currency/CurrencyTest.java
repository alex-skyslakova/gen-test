import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrencyTest {

    @Test
    public void testCurrencyCalculations() {
        Locale.setDefault(Locale.ENGLISH);
        MathContext mc = MathContext.DECIMAL128;

        Map<Currency.MenuItem, BigDecimal> order = new HashMap<>();
        order.put(Currency.MenuItem.Hamburger, new BigDecimal("4000000000000000"));
        order.put(Currency.MenuItem.Milkshake, new BigDecimal("2"));

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Currency.MenuItem it : order.keySet()) {
            subtotal = subtotal.add(it.price.multiply(order.get(it), mc));
        }

        BigDecimal taxRate = new BigDecimal(Currency.taxrate, mc);
        taxRate = taxRate.divide(new BigDecimal("100"), mc);
        BigDecimal tax = subtotal.multiply(taxRate, mc);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);

        BigDecimal total = subtotal.add(tax);

        assertEquals(new BigDecimal("22000000000000005.72"), subtotal);
        assertEquals(new BigDecimal("1683000000000000.44"), tax);
        assertEquals(new BigDecimal("23683000000000006.16"), total);
    }
}
