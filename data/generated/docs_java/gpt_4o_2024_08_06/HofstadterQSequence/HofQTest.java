import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HofQTest {

    @Test
    public void testFirstTenTerms() {
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 6};
        for (int i = 1; i <= 10; i++) {
            assertEquals(expected[i - 1], HofQ.Q(i), "Q(" + i + ") should be " + expected[i - 1]);
        }
    }

    @Test
    public void testThousandthTerm() {
        assertEquals(502, HofQ.Q(1000), "Q(1000) should be 502");
    }

    @Test
    public void testCountLessThanPreceding() {
        int last = 6; // value for Q(10)
        int count = 0;
        for (int i = 11; i <= 100000; i++) {
            int curr = HofQ.Q(i);
            if (curr < last) count++;
            last = curr;
        }
        assertEquals(4969, count, "Q(i) is less than Q(i-1) for i <= 100000 should be 4969 times");
    }
}
