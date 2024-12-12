import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {

    @Test
    void testCalculations() {
        Locale.setDefault(Locale.ENGLISH);
        MathContext mc = MathContext.DECIMAL128;

        Map<Currency.MenuItem, BigDecimal> order = new HashMap<>();
        order.put(Currency.MenuItem.Hamburger, new BigDecimal("4000000000000000"));
        order.put(Currency.MenuItem.Milkshake, new BigDecimal("2"));

        BigDecimal subtotal = BigDecimal.ZERO;
        for (Currency.MenuItem it : order.keySet())
            subtotal = subtotal.add(it.price.multiply(order.get(it), mc));

        BigDecimal tax = new BigDecimal(Currency.taxrate, mc);
        tax = tax.divide(new BigDecimal("100"), mc);
        tax = subtotal.multiply(tax, mc);

         assertEquals(new BigDecimal("22000000000000005.72"), subtotal);
         assertEquals(new BigDecimal("1683000000000000.4386"), tax); // Note: Original prompt output has a rounding error.
         assertEquals(new BigDecimal("23683000000000006.1586"), subtotal.add(tax));
    }



    @Test
    void testEmptyOrder() {
        Locale.setDefault(Locale.ENGLISH);
        MathContext mc = MathContext.DECIMAL128;

        Map<Currency.MenuItem, BigDecimal> order = new HashMap<>();


        BigDecimal subtotal = BigDecimal.ZERO;
        for (Currency.MenuItem it : order.keySet())
            subtotal = subtotal.add(it.price.multiply(order.get(it), mc));

        BigDecimal tax = new BigDecimal(Currency.taxrate, mc);
        tax = tax.divide(new BigDecimal("100"), mc);
        tax = subtotal.multiply(tax, mc);

        assertEquals(BigDecimal.ZERO, subtotal);
        assertEquals(BigDecimal.ZERO, tax);
        assertEquals(BigDecimal.ZERO, subtotal.add(tax));
    }

    @Test
    void testSingleItemOrder() {
        Locale.setDefault(Locale.ENGLISH);
        MathContext mc = MathContext.DECIMAL128;

        Map<Currency.MenuItem, BigDecimal> order = new HashMap<>();
        order.put(Currency.MenuItem.Milkshake, new BigDecimal("5"));


        BigDecimal subtotal = BigDecimal.ZERO;
        for (Currency.MenuItem it : order.keySet())
            subtotal = subtotal.add(it.price.multiply(order.get(it), mc));

        BigDecimal tax = new BigDecimal(Currency.taxrate, mc);
        tax = tax.divide(new BigDecimal("100"), mc);
        tax = subtotal.multiply(tax, mc);

        assertEquals(new BigDecimal("14.30"), subtotal);
        assertEquals(new BigDecimal("1.09145"), tax);
        assertEquals(new BigDecimal("15.39145"), subtotal.add(tax));

    }

}
