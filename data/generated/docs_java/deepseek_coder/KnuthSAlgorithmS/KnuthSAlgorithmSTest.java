import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class SOfNTest {

    private static final int NUM_TRIALS = 100000;
    private static final int SAMPLE_SIZE = 3;
    private static final int ITEM_COUNT = 10;

    @Test
    void testSOfN_EqualProbability() {
        int[] bin = new int[ITEM_COUNT];

        for (int trial = 0; trial < NUM_TRIALS; trial++) {
            SOfN<Integer> s_of_n = new SOfN<>(SAMPLE_SIZE);
            for (int i = 0; i < ITEM_COUNT - 1; i++) {
                s_of_n.process(i);
            }
            for (int s : s_of_n.process(ITEM_COUNT - 1)) {
                bin[s]++;
            }
        }

        // Calculate the expected frequency
        double expectedFrequency = (double) NUM_TRIALS * SAMPLE_SIZE / ITEM_COUNT;
        double tolerance = expectedFrequency * 0.1; // 10% tolerance

        for (int i = 0; i < ITEM_COUNT; i++) {
            assertTrue(Math.abs(bin[i] - expectedFrequency) <= tolerance,
                    "Frequency of item " + i + " is outside the acceptable range: " + bin[i]);
        }
    }

    @Test
    void testSOfN_InitialSample() {
        SOfN<Integer> s_of_n = new SOfN<>(SAMPLE_SIZE);
        for (int i = 0; i < SAMPLE_SIZE; i++) {
            List<Integer> sample = s_of_n.process(i);
            assertEquals(i + 1, sample.size());
            assertTrue(sample.contains(i));
        }
    }

    @Test
    void testSOfN_Replacement() {
        SOfN<Integer> s_of_n = new SOfN<>(SAMPLE_SIZE);
        for (int i = 0; i < ITEM_COUNT; i++) {
            s_of_n.process(i);
        }
        List<Integer> finalSample = s_of_n.process(ITEM_COUNT);
        assertEquals(SAMPLE_SIZE, finalSample.size());
        for (int item : finalSample) {
            assertTrue(item >= 0 && item < ITEM_COUNT);
        }
    }

    @Test
    void testSOfN_Randomness() {
        SOfN<Integer> s_of_n = new SOfN<>(SAMPLE_SIZE);
        Random rand = new Random();
        for (int i = 0; i < ITEM_COUNT; i++) {
            s_of_n.process(rand.nextInt(100));
        }
        List<Integer> finalSample = s_of_n.process(rand.nextInt(100));
        assertEquals(SAMPLE_SIZE, finalSample.size());
    }
}
