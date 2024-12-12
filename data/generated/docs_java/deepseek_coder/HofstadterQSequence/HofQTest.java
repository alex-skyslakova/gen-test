import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HofQTest {

    @Test
    public void testFirstTenTerms() {
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 6};
        for (int i = 1; i <= 10; i++) {
            assertEquals(expected[i - 1], HofQ.Q(i), "Q(" + i + ") should be " + expected[i - 1]);
        }
    }

    @Test
    public void test1000thTerm() {
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
        // The exact count is not provided, but we can assert that the count should be greater than 0
        assertTrue(count > 0, "There should be at least one instance where Q(i) is less than Q(i-1)");
    }

    @Test
    public void testLargeNHandling() {
        // Test a large value of n to ensure caching and recursion limits are handled correctly
        assertDoesNotThrow(() -> HofQ.Q(100000), "Q(100000) should not throw an exception");
    }
}
