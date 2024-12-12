import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class LychrelTest {

    @Test
    void testRev() {
        assertEquals(new BigInteger("321"), Lychrel.rev(new BigInteger("123")));
        assertEquals(new BigInteger("1"), Lychrel.rev(new BigInteger("1")));
        assertEquals(new BigInteger("0"), Lychrel.rev(new BigInteger("0")));
        assertEquals(new BigInteger("12321"), Lychrel.rev(new BigInteger("12321")));
    }

    @Test
    void testLychrelPalindrome() {
        Lychrel.cache.clear(); // Clear cache before each test
        Tuple result = Lychrel.lychrel(new BigInteger("121"));
        assertFalse(result.flag);
        assertEquals(BigInteger.ZERO, result.bi);
    }

    @Test
    void testLychrelNonPalindrome() {
        Lychrel.cache.clear(); // Clear cache before each test
        Tuple result = Lychrel.lychrel(new BigInteger("196"));
        assertTrue(result.flag);
        assertEquals(new BigInteger("196"), result.bi);
    }

    @Test
    void testLychrelRelated() {
        Lychrel.cache.clear(); // Clear cache before each test
        Tuple result = Lychrel.lychrel(new BigInteger("689"));
        assertTrue(result.flag);
        assertEquals(new BigInteger("196"), result.bi);
    }

    @Test
    void testLychrelCache() {
        Lychrel.cache.clear(); // Clear cache before each test
        Lychrel.lychrel(new BigInteger("196"));
        assertTrue(Lychrel.cache.containsKey(new BigInteger("196")));
        assertTrue(Lychrel.cache.containsKey(new BigInteger("887")));
    }

    @Test
    void testLychrelMain() {
        Lychrel.main(new String[]{});
        // This test is more of an integration test to ensure the main method runs without exceptions
    }

    @Test
    void testLychrelEdgeCases() {
        Lychrel.cache.clear(); // Clear cache before each test
        Tuple result = Lychrel.lychrel(new BigInteger("1"));
        assertFalse(result.flag);
        assertEquals(BigInteger.ZERO, result.bi);

        Lychrel.cache.clear(); // Clear cache before each test
        result = Lychrel.lychrel(new BigInteger("10"));
        assertFalse(result.flag);
        assertEquals(BigInteger.ZERO, result.bi);
    }

    @Test
    void testLychrelLargeNumber() {
        Lychrel.cache.clear(); // Clear cache before each test
        Tuple result = Lychrel.lychrel(new BigInteger("1000000"));
        assertTrue(result.flag);
        assertEquals(new BigInteger("1000000"), result.bi);
    }
}
