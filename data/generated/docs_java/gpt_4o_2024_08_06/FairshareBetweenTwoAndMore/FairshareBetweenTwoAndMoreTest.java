import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;

public class FairshareBetweenTwoAndMoreTest {

    @Test
    public void testThueMorseSequenceBase2() {
        List<Integer> expected = Arrays.asList(0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 2));
    }

    @Test
    public void testThueMorseSequenceBase3() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 1, 1, 2, 2, 0, 1, 0, 1, 2, 0, 2, 0, 1);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 3));
    }

    @Test
    public void testThueMorseSequenceBase5() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 5));
    }

    @Test
    public void testThueMorseSequenceBase11() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 11));
    }
}
