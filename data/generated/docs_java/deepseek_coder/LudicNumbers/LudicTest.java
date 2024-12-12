import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LudicTest {

    @Test
    public void testLudicUpToFirst25() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 5, 7, 11, 13, 17, 23, 25, 29, 37, 41, 43, 47, 53, 61, 67, 71, 77, 83, 89, 97, 107, 113);
        List<Integer> actual = Ludic.ludicUpTo(110);
        assertEquals(expected, actual.subList(0, 25));
    }

    @Test
    public void testLudicUpTo1000() {
        int expected = 142; // Number of Ludic numbers <= 1000
        int actual = Ludic.ludicUpTo(1000).size();
        assertEquals(expected, actual);
    }

    @Test
    public void testLudic2000To2005() {
        List<Integer> expected = Arrays.asList(21475, 21481, 21487, 21493, 21503, 21511);
        List<Integer> actual = Ludic.ludicUpTo(22000).subList(1999, 2005);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTriplets() {
        List<List<Integer>> expected = Arrays.asList(
            Arrays.asList(1, 3, 7),
            Arrays.asList(5, 7, 11),
            Arrays.asList(11, 13, 17),
            Arrays.asList(23, 25, 29),
            Arrays.asList(41, 43, 47),
            Arrays.asList(173, 175, 179),
            Arrays.asList(221, 223, 227),
            Arrays.asList(233, 235, 239)
        );
        List<List<Integer>> actual = Ludic.getTriplets(Ludic.ludicUpTo(250));
        assertEquals(expected, actual);
    }

    @Test
    public void testLudicUpToWithSmallNumber() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 5);
        List<Integer> actual = Ludic.ludicUpTo(6);
        assertEquals(expected, actual);
    }

    @Test
    public void testLudicUpToWithSingleNumber() {
        List<Integer> expected = Arrays.asList(1);
        List<Integer> actual = Ludic.ludicUpTo(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testLudicUpToWithNoNumbers() {
        List<Integer> expected = Arrays.asList();
        List<Integer> actual = Ludic.ludicUpTo(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetTripletsWithNoTriplets() {
        List<List<Integer>> expected = Arrays.asList();
        List<List<Integer>> actual = Ludic.getTriplets(Ludic.ludicUpTo(10));
        assertEquals(expected, actual);
    }
}
