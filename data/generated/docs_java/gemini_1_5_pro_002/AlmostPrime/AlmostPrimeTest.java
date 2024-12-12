import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AlmostPrimeTest {

    @Test
    void testKprime_k1() {
        assertTrue(AlmostPrime.kprime(2, 1));
        assertTrue(AlmostPrime.kprime(3, 1));
        assertTrue(AlmostPrime.kprime(5, 1));
        assertTrue(AlmostPrime.kprime(7, 1));
        assertTrue(AlmostPrime.kprime(11, 1));
        assertFalse(AlmostPrime.kprime(4, 1));
        assertFalse(AlmostPrime.kprime(6, 1));
        assertFalse(AlmostPrime.kprime(8, 1));
        assertFalse(AlmostPrime.kprime(9, 1));
        assertFalse(AlmostPrime.kprime(10, 1));

    }

    @Test
    void testKprime_k2() {
        assertTrue(AlmostPrime.kprime(4, 2));
        assertTrue(AlmostPrime.kprime(6, 2));
        assertTrue(AlmostPrime.kprime(9, 2));
        assertTrue(AlmostPrime.kprime(10, 2));
        assertTrue(AlmostPrime.kprime(14, 2));
        assertFalse(AlmostPrime.kprime(2, 2));
        assertFalse(AlmostPrime.kprime(3, 2));
        assertFalse(AlmostPrime.kprime(5, 2));
        assertFalse(AlmostPrime.kprime(7, 2));
        assertFalse(AlmostPrime.kprime(8, 2));


    }

    @Test
    void testKprime_k3() {
        assertTrue(AlmostPrime.kprime(8, 3));
        assertTrue(AlmostPrime.kprime(12, 3));
        assertTrue(AlmostPrime.kprime(18, 3));
        assertTrue(AlmostPrime.kprime(20, 3));
        assertTrue(AlmostPrime.kprime(27, 3));
        assertFalse(AlmostPrime.kprime(28,3));
        assertFalse(AlmostPrime.kprime(4, 3));

    }

    @Test
    void testKprime_k4() {
        assertTrue(AlmostPrime.kprime(16, 4));
        assertTrue(AlmostPrime.kprime(24, 4));
        assertTrue(AlmostPrime.kprime(36, 4));
        assertTrue(AlmostPrime.kprime(40, 4));
        assertTrue(AlmostPrime.kprime(54, 4));
    }



    @Test
    void testKprime_k5() {
        assertTrue(AlmostPrime.kprime(32, 5));
        assertTrue(AlmostPrime.kprime(48, 5));
        assertTrue(AlmostPrime.kprime(72, 5));
        assertTrue(AlmostPrime.kprime(80, 5));
        assertTrue(AlmostPrime.kprime(108, 5));


    }


    @Test
    void testKprime_EdgeCases(){
        assertFalse(AlmostPrime.kprime(1,1));
        assertFalse(AlmostPrime.kprime(1,2));
        assertFalse(AlmostPrime.kprime(0,0));

    }

}
