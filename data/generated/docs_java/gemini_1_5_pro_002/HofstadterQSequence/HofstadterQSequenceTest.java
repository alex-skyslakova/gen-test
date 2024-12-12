import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HofQTest {

    @Test
    void testQFirstTen() {
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 6};
        for (int i = 1; i <= 10; i++) {
            assertEquals(expected[i - 1], HofQ.Q(i));
        }
    }

    @Test
    void testQ1000() {
        assertEquals(502, HofQ.Q(1000));
    }


    @Test
    void testQLessThanPreviousUpTo100000() {
        int count = 0;
        int last = HofQ.Q(10); // Start from the 11th term

        for (int i = 11; i <= 100000; i++) {
            int current = HofQ.Q(i);
            if (current < last) {
                count++;
            }
            last = current;
        }
      assertEquals(49594,count); //previously: assertEquals(49594, count);

    }


    @Test
    void testQLargeN() {

        //Testing with a larger 'n' than 100,000  to ensure it handles large inputs gracefully.
        // We are not asserting the exact value (as it grows quickly), but rather ensuring the method completes without crashing/exceptions due to recursion or memory issues.
        // We assert it is greater than the 100000th term.
        int largeN = 200000; 
        int resultLargeN = HofQ.Q(largeN);
        assertTrue(resultLargeN > HofQ.Q(100000));

    }


     //A basic test to ensure that the caching mechanism within HofQ.Q is functioning correctly.
    @Test
    void testCaching() {
        // Call Q(5) multiple times to populate the cache
        HofQ.Q(5);
        HofQ.Q(5);

        // Access the internal cache using reflection (not ideal, but necessary for this test)
        try {
            java.lang.reflect.Field qField = HofQ.class.getDeclaredField("q");
            qField.setAccessible(true);
            Map<Integer, Integer> qCache = (Map<Integer, Integer>) qField.get(null);

            // Assert that the cache contains the expected value for Q(5)
            assertTrue(qCache.containsKey(5));
            assertEquals(3, qCache.get(5));

        } catch (Exception e) {
            fail("Failed to access or test the cache: " + e.getMessage());
        }

    }

}
