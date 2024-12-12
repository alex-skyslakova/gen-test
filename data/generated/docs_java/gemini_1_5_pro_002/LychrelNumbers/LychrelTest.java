import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LychrelTest {

    private Map<BigInteger, Lychrel.Tuple> cache = new HashMap<>();

    private BigInteger rev(BigInteger bi) {
        String s = new StringBuilder(bi.toString()).reverse().toString();
        return new BigInteger(s);
    }


    private Lychrel.Tuple lychrel(BigInteger n) {
        Lychrel.Tuple res;
        if ((res = cache.get(n)) != null)
            return res;

        BigInteger r = rev(n);
        res = new Lychrel.Tuple(true, n);
        List<BigInteger> seen = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            n = n.add(r);
            r = rev(n);

            if (n.equals(r)) {
                res = new Lychrel.Tuple(false, BigInteger.ZERO);
                break;
            }

            if (cache.containsKey(n)) {
                res = cache.get(n);
                break;
            }

            seen.add(n);
        }

        for (BigInteger bi : seen)
            cache.put(bi, res);

        return res;
    }


    @Test
    void testLychrel12() {
        Lychrel.Tuple result = lychrel(BigInteger.valueOf(12));
        assertFalse(result.flag);
    }

    @Test
    void testLychrel55() {
        Lychrel.Tuple result = lychrel(BigInteger.valueOf(55));
        assertFalse(result.flag);
    }

    @Test
    void testLychrel196() {
        Lychrel.Tuple result = lychrel(BigInteger.valueOf(196));
        assertTrue(result.flag);
        assertEquals(BigInteger.valueOf(196), result.bi);
    }

    @Test
    void testLychrel689() {
        Lychrel.Tuple result = lychrel(BigInteger.valueOf(689));
        assertTrue(result.flag);
        assertEquals(BigInteger.valueOf(196), result.bi); // Converges to 196
    }

    @Test
    void testLychrel4994(){
        Lychrel.Tuple result = lychrel(BigInteger.valueOf(4994));
        assertFalse(result.flag); // it is a palindrome
    }

    @Test
    void testRev(){
        BigInteger reversed = rev(BigInteger.valueOf(12345));
        assertEquals(BigInteger.valueOf(54321), reversed);
    }


}
