import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class FairshareBetweenTwoAndMoreTest {

    @Test
    void testThueMorseSequenceBase2() {
        List<Integer> expected = Arrays.asList(0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 2));
    }

    @Test
    void testThueMorseSequenceBase3() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 2, 0, 1, 1, 2, 0, 0, 1, 2, 1, 0, 2, 2, 0, 1, 2, 1, 0, 0, 1, 2, 0);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 3));
    }

    @Test
    void testThueMorseSequenceBase5() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 4, 0, 1, 2, 3, 3, 4, 0, 1, 2, 2, 3, 4, 0, 1, 1, 2, 3, 4, 0);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 5));
    }

    @Test
    void testThueMorseSequenceBase11() {
        List<Integer> expected = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 0);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(25, 11));
    }

    @Test
    void testThueMorseSequenceZeroTerms() {
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(0, 2));
    }

    @Test
    void testThueMorseSequenceOneTerm(){
        List<Integer> expected = Arrays.asList(0);
        assertEquals(expected, FairshareBetweenTwoAndMore.thueMorseSequence(1, 7));

    }



}
