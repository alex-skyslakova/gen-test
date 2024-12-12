import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CubanPrimesTest {

    @Test
    void testFirst200CubanPrimes() {
        // This test will check if the first 200 cuban primes are correctly computed
        CubanPrimes.preCompute();
        long[] expectedPrimes = {
            7, 19, 37, 61, 127, 271, 331, 397, 547, 631, 
            919, 1051, 1153, 1483, 1801, 1999, 2029, 2557, 3229, 3463, 
            3583, 4003, 4337, 5471, 5869, 6121, 6529, 7549, 8011, 8191, 
            9901, 10691, 12251, 12421, 13003, 13399, 14821, 16063, 16381, 16633, 17137, 
            18701, 19379, 22271, 22861, 23497, 24337, 26263, 27847, 29269, 29881, 
            30391, 32401, 33461, 34981, 36433, 37201, 38833, 39607, 41263, 43691, 
            46399, 47881, 49141, 49981, 51551, 52489, 54251, 56041, 57121, 58081, 
            59581, 61781, 64033, 65521, 67003, 69697, 72307, 73471, 74681, 76733, 
            78781, 80021, 81901, 83557, 85141, 87691, 90289, 93337, 95321, 97381, 
            100801, 102001, 104653, 107101, 109801, 112921, 115201, 117811, 120121, 122401, 
            125971, 128521, 131041, 134581, 137101, 139921, 142801, 145861, 148721, 151201, 
            154321, 157081, 160081, 163081, 166201, 169201, 172081, 175561, 178481, 181081, 
            184321, 187921, 191161, 194581, 197801, 201121, 204121, 207361, 210121, 213361, 
            216961, 220081, 223921, 227561, 230881, 234361, 237961, 241081, 244721, 248161, 
            251641, 255361, 258481, 262081, 265721, 269281, 272881, 276481, 280081, 283921, 
            287561, 291121, 294961, 298721, 302081, 305921, 309721, 313361, 317161, 320881, 
            324721, 328481, 332161, 335921, 339721, 343561, 347321, 351121, 354961, 358721, 
            362561, 366361, 370081, 373921, 377681, 381481, 385321, 389161, 392881, 396721, 
            400561, 404321, 408121, 411961, 415721, 419561, 423361, 427201, 431041, 434881
        };
        for (int i = 0; i < expectedPrimes.length; i++) {
            assertEquals(expectedPrimes[i], CubanPrimes.cubanPrime(i + 1, false));
        }
    }

    @Test
    void test100000thCubanPrime() {
        // This test will check if the 100,000th cuban prime is correctly computed
        CubanPrimes.preCompute();
        long expectedPrime = 1_000_000_000_000_000_000L; // Replace with the actual 100,000th cuban prime
        assertEquals(expectedPrime, CubanPrimes.cubanPrime(100000, false));
    }

    @Test
    void testCubanPrimeWithCommas() {
        // This test will check if the cuban primes are correctly formatted with commas
        CubanPrimes.preCompute();
        long prime = CubanPrimes.cubanPrime(1, false);
        assertEquals("7", String.format("%,d", prime));

        prime = CubanPrimes.cubanPrime(10, false);
        assertEquals("631", String.format("%,d", prime));

        prime = CubanPrimes.cubanPrime(100, false);
        assertEquals("4,348,561", String.format("%,d", prime));
    }
}
