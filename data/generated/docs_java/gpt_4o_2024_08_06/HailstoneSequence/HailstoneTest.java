import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class HailstoneTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGetHailstoneSequenceWithZero() {
        Hailstone.getHailstoneSequence(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetHailstoneSequenceWithNegative() {
        Hailstone.getHailstoneSequence(-5);
    }

    @Test
    public void testGetHailstoneSequenceWithOne() {
        List<Long> sequence = Hailstone.getHailstoneSequence(1);
        assertEquals(1, sequence.size());
        assertEquals(Long.valueOf(1), sequence.get(0));
    }

    @Test
    public void testGetHailstoneSequenceWithEvenNumber() {
        List<Long> sequence = Hailstone.getHailstoneSequence(4);
        assertEquals(3, sequence.size());
        assertEquals(Long.valueOf(4), sequence.get(0));
        assertEquals(Long.valueOf(2), sequence.get(1));
        assertEquals(Long.valueOf(1), sequence.get(2));
    }

    @Test
    public void testGetHailstoneSequenceWithOddNumber() {
        List<Long> sequence = Hailstone.getHailstoneSequence(3);
        assertEquals(8, sequence.size());
        assertEquals(Long.valueOf(3), sequence.get(0));
        assertEquals(Long.valueOf(10), sequence.get(1));
        assertEquals(Long.valueOf(5), sequence.get(2));
        assertEquals(Long.valueOf(16), sequence.get(3));
        assertEquals(Long.valueOf(8), sequence.get(4));
        assertEquals(Long.valueOf(4), sequence.get(5));
        assertEquals(Long.valueOf(2), sequence.get(6));
        assertEquals(Long.valueOf(1), sequence.get(7));
    }

    @Test
    public void testGetHailstoneSequenceWith27() {
        List<Long> sequence = Hailstone.getHailstoneSequence(27);
        assertEquals(112, sequence.size());
        assertEquals(Long.valueOf(27), sequence.get(0));
        assertEquals(Long.valueOf(82), sequence.get(1));
        assertEquals(Long.valueOf(41), sequence.get(2));
        assertEquals(Long.valueOf(124), sequence.get(3));
        assertEquals(Long.valueOf(8), sequence.get(sequence.size() - 4));
        assertEquals(Long.valueOf(4), sequence.get(sequence.size() - 3));
        assertEquals(Long.valueOf(2), sequence.get(sequence.size() - 2));
        assertEquals(Long.valueOf(1), sequence.get(sequence.size() - 1));
    }

    @Test
    public void testLongestHailstoneSequenceUnder100000() {
        long maxNumber = 1;
        int maxLength = 1;
        for (long i = 2; i < 100000; i++) {
            int length = Hailstone.getHailstoneSequence(i).size();
            if (length > maxLength) {
                maxLength = length;
                maxNumber = i;
            }
        }
        assertEquals(77031, maxNumber);
        assertEquals(351, maxLength);
    }
}
