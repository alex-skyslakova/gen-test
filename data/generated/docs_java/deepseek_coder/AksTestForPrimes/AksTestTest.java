import static org.junit.Assert.*;
import org.junit.Test;

public class AksTestTest {

    @Test
    public void testCoeff() {
        AksTest.coeff(0);
        assertArrayEquals(new long[]{1}, AksTest.c);

        AksTest.coeff(1);
        assertArrayEquals(new long[]{-1, 1}, AksTest.c);

        AksTest.coeff(2);
        assertArrayEquals(new long[]{1, -2, 1}, AksTest.c);

        AksTest.coeff(3);
        assertArrayEquals(new long[]{-1, 3, -3, 1}, AksTest.c);

        AksTest.coeff(4);
        assertArrayEquals(new long[]{1, -4, 6, -4, 1}, AksTest.c);

        AksTest.coeff(5);
        assertArrayEquals(new long[]{-1, 5, -10, 10, -5, 1}, AksTest.c);

        AksTest.coeff(6);
        assertArrayEquals(new long[]{1, -6, 15, -20, 15, -6, 1}, AksTest.c);

        AksTest.coeff(7);
        assertArrayEquals(new long[]{-1, 7, -21, 35, -35, 21, -7, 1}, AksTest.c);
    }

    @Test
    public void testIsPrime() {
        assertFalse(AksTest.isPrime(0));
        assertFalse(AksTest.isPrime(1));
        assertTrue(AksTest.isPrime(2));
        assertTrue(AksTest.isPrime(3));
        assertFalse(AksTest.isPrime(4));
        assertTrue(AksTest.isPrime(5));
        assertFalse(AksTest.isPrime(6));
        assertTrue(AksTest.isPrime(7));
        assertFalse(AksTest.isPrime(8));
        assertFalse(AksTest.isPrime(9));
        assertFalse(AksTest.isPrime(10));
        assertTrue(AksTest.isPrime(11));
        assertFalse(AksTest.isPrime(12));
        assertTrue(AksTest.isPrime(13));
        assertFalse(AksTest.isPrime(14));
        assertFalse(AksTest.isPrime(15));
        assertFalse(AksTest.isPrime(16));
        assertTrue(AksTest.isPrime(17));
        assertFalse(AksTest.isPrime(18));
        assertTrue(AksTest.isPrime(19));
        assertFalse(AksTest.isPrime(20));
        assertFalse(AksTest.isPrime(21));
        assertFalse(AksTest.isPrime(22));
        assertTrue(AksTest.isPrime(23));
        assertFalse(AksTest.isPrime(24));
        assertFalse(AksTest.isPrime(25));
        assertFalse(AksTest.isPrime(26));
        assertFalse(AksTest.isPrime(27));
        assertFalse(AksTest.isPrime(28));
        assertTrue(AksTest.isPrime(29));
        assertFalse(AksTest.isPrime(30));
        assertTrue(AksTest.isPrime(31));
        assertFalse(AksTest.isPrime(32));
        assertFalse(AksTest.isPrime(33));
        assertFalse(AksTest.isPrime(34));
    }

    @Test
    public void testShow() {
        // This test is more complex to automate since it involves console output.
        // You can manually verify the output by running the main method.
    }
}
