import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class LeftTruncatablePrimeTest {

    @Test
    void testGetNextLeftTruncatablePrimes_emptyInput() {
        List<BigInteger> result = LeftTruncatablePrime.getNextLeftTruncatablePrimes(BigInteger.ZERO, 3, 10);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).intValue());
    }

    @Test
    void testGetNextLeftTruncatablePrimes_base3() {
        List<BigInteger> result = LeftTruncatablePrime.getNextLeftTruncatablePrimes(new BigInteger("2", 3), 3, 10);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).intValue());

        result = LeftTruncatablePrime.getNextLeftTruncatablePrimes(new BigInteger("12",3), 3, 10);
        assertEquals(0, result.size());
    }
    
        @Test
    void testGetNextLeftTruncatablePrimes_base10() {
        List<BigInteger> result = LeftTruncatablePrime.getNextLeftTruncatablePrimes(BigInteger.valueOf(3), 10, 10);
      List<BigInteger> expected = new ArrayList<>();
        expected.add(BigInteger.valueOf(13));
        expected.add(BigInteger.valueOf(23));
        expected.add(BigInteger.valueOf(43));
        expected.add(BigInteger.valueOf(53));
        expected.add(BigInteger.valueOf(73));
        expected.add(BigInteger.valueOf(83));

      assertEquals(expected.size(), result.size());

      for(BigInteger b: expected){
        assertTrue(result.contains(b));
      }
    }



    @Test
    void testGetLargestLeftTruncatablePrime_base3() {
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(3, 10);
        assertEquals(5, result.intValue());
    }

    @Test
    void testGetLargestLeftTruncatablePrime_base10() {
        BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(10, 10);
        assertEquals(357686312646216567629137, result.longValue());
    }

    @Test
    void testGetLargestLeftTruncatablePrime_noPrime() {
       BigInteger result = LeftTruncatablePrime.getLargestLeftTruncatablePrime(2,10);
        assertNull(result);
    }

}
