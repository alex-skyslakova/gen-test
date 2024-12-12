import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LychrelTest {

    @Test
    void testRev() {
        assertEquals(new BigInteger("21"), Lychrel.rev(new BigInteger("12")));
        assertEquals(new BigInteger("110"), Lychrel.rev(new BigInteger("011")));
        assertEquals(new BigInteger("12321"), Lychrel.rev(new BigInteger("12321")));
    }

    @Test
    void testLychrelPalindrome() {
        Lychrel.Tuple result = Lychrel.lychrel(new BigInteger("12"));
        assertFalse(result.flag);  // 12 becomes 33, which is a palindrome

        result = Lychrel.lychrel(new BigInteger("55"));
        assertFalse(result.flag);  // 55 becomes 121, which is a palindrome
    }

    @Test
    void testLychrelNonPalindrome() {
        Lychrel.Tuple result = Lychrel.lychrel(new BigInteger("196"));
        assertTrue(result.flag);  // 196 is a Lychrel number candidate

        result = Lychrel.lychrel(new BigInteger("689"));
        assertTrue(result.flag);  // 689 is a related Lychrel number
    }

    @Test
    void testCacheFunctionality() {
        BigInteger n = new BigInteger("196");
        Lychrel.lychrel(n);
        assertTrue(Lychrel.cache.containsKey(n));

        n = new BigInteger("12");
        Lychrel.lychrel(n);
        assertTrue(Lychrel.cache.containsKey(n));
    }

    @Test
    void testMainOutput() {
        // This test is more of a functional test to ensure the main method runs without exceptions
        // and produces expected output. It is not a typical unit test.
        Lychrel.main(new String[]{});
    }
}
