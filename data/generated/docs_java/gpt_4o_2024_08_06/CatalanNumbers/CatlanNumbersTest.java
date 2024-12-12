import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatlanNumbersTest {

    private CatlanNumbers.Catlan f1;
    private CatlanNumbers.Catlan f2;
    private CatlanNumbers.Catlan f3;

    @BeforeEach
    public void setUp() {
        f1 = new CatlanNumbers.Catlan1();
        f2 = new CatlanNumbers.Catlan2();
        f3 = new CatlanNumbers.Catlan3();
    }

    @Test
    public void testCatlan1() {
        testCatlanImplementation(f1);
    }

    @Test
    public void testCatlan2() {
        testCatlanImplementation(f2);
    }

    @Test
    public void testCatlan3() {
        testCatlanImplementation(f3);
    }

    private void testCatlanImplementation(CatlanNumbers.Catlan catlan) {
        BigInteger[] expectedCatalanNumbers = {
            BigInteger.valueOf(1),
            BigInteger.valueOf(1),
            BigInteger.valueOf(2),
            BigInteger.valueOf(5),
            BigInteger.valueOf(14),
            BigInteger.valueOf(42),
            BigInteger.valueOf(132),
            BigInteger.valueOf(429),
            BigInteger.valueOf(1430),
            BigInteger.valueOf(4862),
            BigInteger.valueOf(16796),
            BigInteger.valueOf(58786),
            BigInteger.valueOf(208012),
            BigInteger.valueOf(742900),
            BigInteger.valueOf(2674440),
            BigInteger.valueOf(9694845)
        };

        for (int n = 0; n <= 15; n++) {
            assertEquals(expectedCatalanNumbers[n], catlan.catlin(n), "Failed at n = " + n);
        }
    }
}
