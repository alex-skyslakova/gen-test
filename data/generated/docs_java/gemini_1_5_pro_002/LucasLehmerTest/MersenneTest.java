import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;
import static Mersenne.isPrime;
import static Mersenne.isMersennePrime;

public class MersenneTest {

    @Test
    void testIsPrime() {
        assertTrue(isPrime(2));
        assertTrue(isPrime(3));
        assertTrue(isPrime(5));
        assertTrue(isPrime(7));
        assertTrue(isPrime(13));
        assertTrue(isPrime(17));
        assertTrue(isPrime(19));
        assertTrue(isPrime(31));
        assertTrue(isPrime(61));
        assertTrue(isPrime(89));
        assertTrue(isPrime(107));
        assertTrue(isPrime(127));
        assertTrue(isPrime(521));
        assertTrue(isPrime(607));


        assertFalse(isPrime(1));
        assertFalse(isPrime(0));
        assertFalse(isPrime(-1));
        assertFalse(isPrime(4));
        assertFalse(isPrime(6));
        assertFalse(isPrime(8));
        assertFalse(isPrime(9));
        assertFalse(isPrime(10));
        assertFalse(isPrime(15));

    }

    @Test
    void testIsMersennePrime() {
        assertTrue(isMersennePrime(2));
        assertTrue(isMersennePrime(3));
        assertTrue(isMersennePrime(5));
        assertTrue(isMersennePrime(7));
        assertTrue(isMersennePrime(13));
        assertTrue(isMersennePrime(17));
        assertTrue(isMersennePrime(19));
        assertTrue(isMersennePrime(31));
        assertTrue(isMersennePrime(61));
        assertTrue(isMersennePrime(89));
        assertTrue(isMersennePrime(107));
        assertTrue(isMersennePrime(127));
        assertTrue(isMersennePrime(521));
        assertTrue(isMersennePrime(607));



        assertFalse(isMersennePrime(11));
        assertFalse(isMersennePrime(23));
        assertFalse(isMersennePrime(29));
        assertFalse(isMersennePrime(1));
        assertFalse(isMersennePrime(4));
    }


    @Test
    void testLargeMersennePrime(){
         assertTrue(isMersennePrime(1279));
    }

    @Test
    void testIsMersennePrimeEdgeCases() {
        assertFalse(isMersennePrime(1));
        assertFalse(isMersennePrime(0));
        assertFalse(isMersennePrime(-5));
    }

    @Test
    void testIsPrimeEdgeCases(){
         assertFalse(isPrime(1));
        assertFalse(isPrime(0));
        assertFalse(isPrime(-5));
    }

}
