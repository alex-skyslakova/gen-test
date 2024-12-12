import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AksTestTest {

    @Test
    void coeffTest_n0() {
        AksTest.coeff(0);
        assertArrayEquals(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, AksTest.c);
    }

    @Test
    void coeffTest_n1() {
        AksTest.coeff(1);
        assertArrayEquals(new long[]{-1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, AksTest.c);
    }

    @Test
    void coeffTest_n2() {
        AksTest.coeff(2);
        assertArrayEquals(new long[]{1, -2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, AksTest.c);
    }

    @Test
    void coeffTest_n3() {
        AksTest.coeff(3);
        assertArrayEquals(new long[]{-1, 3, -3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, AksTest.c);

    }
    @Test
    void coeffTest_n7() {
        AksTest.coeff(7);
        assertArrayEquals(new long[]{-1, 7, -21, 35, -35, 21, -7, 1, 0, 0, 0, 0, 0, 0, 0, 0}, AksTest.c);
    }



    @Test
    void isPrimeTest_true() {
        assertTrue(AksTest.isPrime(2));
        assertTrue(AksTest.isPrime(3));
        assertTrue(AksTest.isPrime(5));
        assertTrue(AksTest.isPrime(7));
        assertTrue(AksTest.isPrime(13));
        assertTrue(AksTest.isPrime(31));
    }


    @Test
    void isPrimeTest_false() {

        assertFalse(AksTest.isPrime(1));
        assertFalse(AksTest.isPrime(4));
        assertFalse(AksTest.isPrime(6));
        assertFalse(AksTest.isPrime(8));
        assertFalse(AksTest.isPrime(9));
        assertFalse(AksTest.isPrime(10));
        assertFalse(AksTest.isPrime(25));

    }


    @Test
    void primesUnder35() {
        StringBuilder primes = new StringBuilder();
        for (int n = 1; n < 35; n++)
            if (AksTest.isPrime(n))
                primes.append(" ").append(n);

        assertEquals(" 2 3 5 7 11 13 17 19 23 29 31", primes.toString());
    }


}
