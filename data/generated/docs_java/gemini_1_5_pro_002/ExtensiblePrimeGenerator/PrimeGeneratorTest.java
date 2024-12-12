import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class PrimeGeneratorTest {

    @Test
    void testFirstTwentyPrimes() {
        PrimeGenerator pgen = new PrimeGenerator(20, 20);
        List<Integer> firstTwenty = new ArrayList<>();
        for (int i = 0; i < 20; ++i) {
            firstTwenty.add(pgen.nextPrime());
        }
        assertArrayEquals(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71}, firstTwenty.stream().mapToInt(i -> i).toArray());
    }

    @Test
    void testPrimesBetween100And150() {
        PrimeGenerator pgen = new PrimeGenerator(20, 20);
        List<Integer> primesBetween = new ArrayList<>();
        int prime;
        do {
            prime = pgen.nextPrime();
            if (prime >= 100 && prime <= 150) {
                primesBetween.add(prime);
            }
        } while (prime <= 150);

        assertArrayEquals(new int[]{101, 103, 107, 109, 113, 127, 131, 137, 139, 149}, primesBetween.stream().mapToInt(i -> i).toArray());
    }


    @Test
    void testNumberOfPrimesBetween7700And8000() {
        PrimeGenerator pgen = new PrimeGenerator(20, 20);
        int count = 0;
        int prime;
        do {
            prime = pgen.nextPrime();
            if (prime >= 7700 && prime <= 8000) {
                ++count;
            }
        } while (prime <= 8000);
        assertEquals(16, count);

    }

    @Test
    void testTenThousandthPrime() {
        PrimeGenerator pgen = new PrimeGenerator(20, 200000); // Increased increment to avoid excessive iterations
        int n = 10000;
        int prime = 0;
        for(int i = 0; i < n; ++i) {
            prime = pgen.nextPrime();
        }
        
        assertEquals(104729, prime);
    }


    @Test
    void testNextPrimeOverflowHandling() {
      PrimeGenerator pgen = new PrimeGenerator(Integer.MAX_VALUE - 10, 20);
      assertEquals(0, pgen.nextPrime());
    }
}


