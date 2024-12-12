import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AksTestTest {

    @Test
    public void testCoeff() {
        AksTest aksTest = new AksTest();
        long[][] expectedCoefficients = {
            {1}, // (x-1)^0
            {1, -1}, // (x-1)^1
            {1, -2, 1}, // (x-1)^2
            {1, -3, 3, -1}, // (x-1)^3
            {1, -4, 6, -4, 1}, // (x-1)^4
            {1, -5, 10, -10, 5, -1}, // (x-1)^5
            {1, -6, 15, -20, 15, -6, 1}, // (x-1)^6
            {1, -7, 21, -35, 35, -21, 7, -1}, // (x-1)^7
        };

        for (int n = 0; n <= 7; n++) {
            aksTest.coeff(n);
            for (int i = 0; i <= n; i++) {
                assertEquals(expectedCoefficients[n][i], aksTest.c[i], "Coefficient mismatch at n=" + n + ", i=" + i);
            }
        }
    }

    @Test
    public void testIsPrime() {
        AksTest aksTest = new AksTest();
        int[] primesUnder35 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
        for (int i = 1; i < 35; i++) {
            boolean expected = false;
            for (int prime : primesUnder35) {
                if (i == prime) {
                    expected = true;
                    break;
                }
            }
            assertEquals(expected, aksTest.isPrime(i), "Prime test failed for n=" + i);
        }
    }

    @Test
    public void testPrimesUnder50() {
        AksTest aksTest = new AksTest();
        int[] primesUnder50 = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};
        for (int i = 1; i < 50; i++) {
            boolean expected = false;
            for (int prime : primesUnder50) {
                if (i == prime) {
                    expected = true;
                    break;
                }
            }
            assertEquals(expected, aksTest.isPrime(i), "Prime test failed for n=" + i);
        }
    }
}
