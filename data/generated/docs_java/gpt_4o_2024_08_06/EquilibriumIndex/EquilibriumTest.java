import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class EquilibriumTest {

    public List<Integer> getEquilibriumIndices(int[] sequence) {
        List<Integer> indices = new ArrayList<>();
        int totalSum = 0;
        for (int n : sequence) {
            totalSum += n;
        }
        int runningSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            int n = sequence[i];
            if (totalSum - runningSum - n == runningSum) {
                indices.add(i);
            }
            runningSum += n;
        }
        return indices;
    }

    @Test
    public void testEquilibriumIndices() {
        int[] sequence = {-7, 1, 5, 2, -4, 3, 0};
        List<Integer> expected = List.of(3, 6);
        assertEquals(expected, getEquilibriumIndices(sequence));
    }

    @Test
    public void testNoEquilibriumIndices() {
        int[] sequence = {1, 2, 3};
        List<Integer> expected = List.of();
        assertEquals(expected, getEquilibriumIndices(sequence));
    }

    @Test
    public void testSingleElement() {
        int[] sequence = {1};
        List<Integer> expected = List.of(0);
        assertEquals(expected, getEquilibriumIndices(sequence));
    }

    @Test
    public void testAllZeros() {
        int[] sequence = {0, 0, 0, 0};
        List<Integer> expected = List.of(0, 1, 2, 3);
        assertEquals(expected, getEquilibriumIndices(sequence));
    }

    @Test
    public void testNegativeNumbers() {
        int[] sequence = {-1, -1, -1, -1, -1, -1};
        List<Integer> expected = List.of();
        assertEquals(expected, getEquilibriumIndices(sequence));
    }

    @Test
    public void testLargeNumbers() {
        int[] sequence = {Integer.MAX_VALUE, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
        List<Integer> expected = List.of(2);
        assertEquals(expected, getEquilibriumIndices(sequence));
    }
}
