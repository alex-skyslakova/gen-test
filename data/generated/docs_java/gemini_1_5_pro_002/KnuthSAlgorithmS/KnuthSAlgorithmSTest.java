import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmSTest {

    interface SOfN<T> extends Function<T, List<T>> {}


    static <T> SOfN<T> s_of_n_creator(int n) {
        return new SOfN<T>() {
            private final Random rand = new Random();
            private final List<T> sample = new ArrayList<>(n);
            private int i = 0;
            private final int sampleSize = n;

            @Override
            public List<T> apply(T item) {
                if (++i <= sampleSize) {
                    sample.add(item);
                } else if (rand.nextInt(i) < sampleSize) {
                    sample.set(rand.nextInt(sampleSize), item);
                }
                return new ArrayList<>(sample); // Return a copy to avoid modification from outside
            }
        };
    }

    @Test
    void testSOfN_initialItems() {
        SOfN<Integer> s_of_3 = s_of_n_creator(3);
        assertEquals(List.of(0), s_of_3.apply(0));
        assertEquals(List.of(0, 1), s_of_3.apply(1));
        assertEquals(List.of(0, 1, 2), s_of_3.apply(2));
    }

    @Test
    void testSOfN_replacement() {
        SOfN<Integer> s_of_3 = s_of_n_creator(3);
        for(int i = 0; i < 3; i++) s_of_3.apply(i);


        // While probabilistic, ensure the apply method functions without error
        assertDoesNotThrow(() -> s_of_3.apply(3));
        assertDoesNotThrow(() -> s_of_3.apply(4));
    }

    @Test
    void testSOfN_distribution() {
        int[] bin = new int[10];
        int numTrials = 100000;
        for (int trial = 0; trial < numTrials; trial++) {
            SOfN<Integer> s_of_n = s_of_n_creator(3);
            for (int i = 0; i <= 9; i++) {
                List<Integer> sample = s_of_n.apply(i);
                if (i == 9) { // Count only the final sample
                    for (int s : sample) {
                        bin[s]++;
                    }
                }

            }
        }

        // Check distribution. Not a strict test, allows for some random variation
        for (int count : bin) {
            assertTrue(Math.abs(count - (numTrials / 10.0 * 3)) < numTrials * 0.01 ); 
        }
    }



    @Test
    void testSOfN_emptyList() {

      SOfN<Integer> s_of_0 = s_of_n_creator(0);
      assertEquals(List.of(), s_of_0.apply(1));

    }


    @Test
    void testSOfN_immutabilityOfReturn() {
        SOfN<Integer> s_of_3 = s_of_n_creator(3);
        List<Integer> initialSample = s_of_3.apply(0);
        List<Integer> secondSample = s_of_3.apply(1);
        assertNotSame(initialSample, secondSample);  // Check if different list objects
        assertEquals(1, secondSample.size()); 

        // Modify the initial sample and verify the second sample is not affected
        initialSample.clear();
        assertEquals(1, secondSample.size());

    }



}
