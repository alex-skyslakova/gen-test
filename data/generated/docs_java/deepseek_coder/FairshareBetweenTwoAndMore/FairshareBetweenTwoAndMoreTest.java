import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FairshareBetweenTwoAndMoreTest {

    @Test
    public void testThueMorseSequenceForTwoPeople() {
        List<Integer> expected = Arrays.asList(0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0);
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(25, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testThueMorseSequenceForThreePeople() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 1, 2, 0, 2, 0, 1, 1, 2, 0, 2, 0, 1, 0, 1, 2, 2, 0, 1, 0, 1, 2, 1);
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(25, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void testThueMorseSequenceForFivePeople() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 1, 2, 3, 4, 0, 2, 3, 4, 0, 1, 3, 4, 0, 1, 2, 4, 0, 1, 2, 3);
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(25, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testThueMorseSequenceForElevenPeople() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 2, 3, 4);
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(25, 11);
        assertEquals(expected, actual);
    }

    @Test
    public void testThueMorseSequenceForSingleTerm() {
        List<Integer> expected = Arrays.asList(0);
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testThueMorseSequenceForZeroTerms() {
        List<Integer> expected = Arrays.asList();
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(0, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void testThueMorseSequenceForLargeBase() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24);
        List<Integer> actual = FairshareBetweenTwoAndMore.thueMorseSequence(25, 25);
        assertEquals(expected, actual);
    }
}
