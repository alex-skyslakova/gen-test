import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class HailstoneTest {

    @Test
    void testGetHailstoneSequence_InvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> Hailstone.getHailstoneSequence(0));
        assertThrows(IllegalArgumentException.class, () -> Hailstone.getHailstoneSequence(-1));
    }

    @Test
    void testGetHailstoneSequence_ValidInput() {
        List<Long> sequence = Hailstone.getHailstoneSequence(1);
        assertEquals(1, sequence.size());
        assertEquals(List.of(1L), sequence);

        sequence = Hailstone.getHailstoneSequence(2);
        assertEquals(2, sequence.size());
        assertEquals(List.of(2L, 1L), sequence);

        sequence = Hailstone.getHailstoneSequence(3);
        assertEquals(8, sequence.size());
        assertEquals(List.of(3L, 10L, 5L, 16L, 8L, 4L, 2L, 1L), sequence);

        sequence = Hailstone.getHailstoneSequence(27);
        assertEquals(112, sequence.size());
        assertEquals(List.of(27L, 82L, 41L, 124L), sequence.subList(0, 4));
        assertEquals(List.of(8L, 4L, 2L, 1L), sequence.subList(sequence.size() - 4, sequence.size()));
    }

    @Test
    void testLongestHailstoneSequence() {
        long MAX = 100000;
        long highestNumber = 1;
        int highestCount = 1;

        for (long i = 2; i < MAX; i++) {
            int count = Hailstone.getHailstoneSequence(i).size();
            if (count > highestCount) {
                highestCount = count;
                highestNumber = i;
            }
        }

        // The number with the longest sequence under 100,000 is 77031 with a length of 351
        assertEquals(77031, highestNumber);
        assertEquals(351, highestCount);
    }
}
