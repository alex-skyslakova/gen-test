import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class CatlanNumbersTest {

    @Test
    void catlan1_test() {
        CatlanNumbers.Catlan catlan1 = new CatlanNumbers.Catlan1();
        assertEquals(BigInteger.ONE, catlan1.catlin(0));
        assertEquals(BigInteger.ONE, catlan1.catlin(1));
        assertEquals(BigInteger.valueOf(2), catlan1.catlin(2));
        assertEquals(BigInteger.valueOf(5), catlan1.catlin(3));
        assertEquals(BigInteger.valueOf(14), catlan1.catlin(4));
        assertEquals(BigInteger.valueOf(42), catlan1.catlin(5));
        assertEquals(BigInteger.valueOf(132), catlan1.catlin(6));
        assertEquals(BigInteger.valueOf(429), catlan1.catlin(7));
        assertEquals(BigInteger.valueOf(1430), catlan1.catlin(8));
        assertEquals(BigInteger.valueOf(4862), catlan1.catlin(9));
        assertEquals(BigInteger.valueOf(16796), catlan1.catlin(10));

    }


    @Test
    void catlan2_test() {
        CatlanNumbers.Catlan catlan2 = new CatlanNumbers.Catlan2();
        assertEquals(BigInteger.ONE, catlan2.catlin(0));
        assertEquals(BigInteger.ONE, catlan2.catlin(1));
        assertEquals(BigInteger.valueOf(2), catlan2.catlin(2));
        assertEquals(BigInteger.valueOf(5), catlan2.catlin(3));
        assertEquals(BigInteger.valueOf(14), catlan2.catlin(4));
        assertEquals(BigInteger.valueOf(42), catlan2.catlin(5));
        assertEquals(BigInteger.valueOf(132), catlan2.catlin(6));
        assertEquals(BigInteger.valueOf(429), catlan2.catlin(7));
        assertEquals(BigInteger.valueOf(1430), catlan2.catlin(8));
        assertEquals(BigInteger.valueOf(4862), catlan2.catlin(9));
        assertEquals(BigInteger.valueOf(16796), catlan2.catlin(10));
    }

    @Test
    void catlan3_test() {
        CatlanNumbers.Catlan catlan3 = new CatlanNumbers.Catlan3();
        assertEquals(BigInteger.ONE, catlan3.catlin(0));
        assertEquals(BigInteger.ONE, catlan3.catlin(1));
        assertEquals(BigInteger.valueOf(2), catlan3.catlin(2));
        assertEquals(BigInteger.valueOf(5), catlan3.catlin(3));
        assertEquals(BigInteger.valueOf(14), catlan3.catlin(4));
        assertEquals(BigInteger.valueOf(42), catlan3.catlin(5));
        assertEquals(BigInteger.valueOf(132), catlan3.catlin(6));
        assertEquals(BigInteger.valueOf(429), catlan3.catlin(7));
        assertEquals(BigInteger.valueOf(1430), catlan3.catlin(8));
        assertEquals(BigInteger.valueOf(4862), catlan3.catlin(9));
        assertEquals(BigInteger.valueOf(16796), catlan3.catlin(10));
    }
}

