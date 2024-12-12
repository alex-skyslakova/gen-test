import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class LudicTest {

    @Test
    void testLudicUpTo_first25() {
        List<Integer> expected = List.of(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89);
        List<Integer> actual = Ludic.ludicUpTo(110);
        assertEquals(expected, actual);
    }

    @Test
    void testLudicUpTo_countUpTo1000() {
        int expectedCount = 142;
        int actualCount = Ludic.ludicUpTo(1000).size();
        assertEquals(expectedCount, actualCount);
    }


    @Test
    void testLudicUpTo_2000thTo2005th() {
        List<Integer> expected = List.of(21253, 21259, 21267, 21281, 21291, 21297);
        List<Integer> actual = Ludic.ludicUpTo(22000).subList(1999, 2005);
        assertEquals(expected, actual);

    }

    @Test
    void testGetTriplets_upTo250() {
        List<List<Integer>> expectedTriplets = new ArrayList<>();
        expectedTriplets.add(List.of(1, 3, 7));
        expectedTriplets.add(List.of(7, 9, 13));
        expectedTriplets.add(List.of(13, 15, 19));
        expectedTriplets.add(List.of(37, 39, 43));
        expectedTriplets.add(List.of(43, 45, 49));
        expectedTriplets.add(List.of(67, 69, 73));
        expectedTriplets.add(List.of(79, 81, 85));
        expectedTriplets.add(List.of(97, 99, 103));
        expectedTriplets.add(List.of(103, 105, 109));
        expectedTriplets.add(List.of(109, 111, 115));
        expectedTriplets.add(List.of(127, 129, 133));
        expectedTriplets.add(List.of(163, 165, 169));
        expectedTriplets.add(List.of(193, 195, 199));
        expectedTriplets.add(List.of(223, 225, 229));

        List<List<Integer>> actualTriplets = Ludic.getTriplets(Ludic.ludicUpTo(250));
        assertEquals(expectedTriplets, actualTriplets);

    }

     @Test
    void testLudicUpTo_empty() {
        List<Integer> expected = List.of();
        List<Integer> actual = Ludic.ludicUpTo(0);
        assertEquals(expected, actual);
    }


    @Test
    void testGetTriplets_empty() {
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> actual = Ludic.getTriplets(Ludic.ludicUpTo(1)); 
        assertEquals(expected, actual);
    }

}
