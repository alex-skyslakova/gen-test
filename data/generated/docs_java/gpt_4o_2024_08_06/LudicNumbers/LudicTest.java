import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LudicTest {

    @Test
    public void testFirst25Ludics() {
        List<Integer> ludics = Ludic.ludicUpTo(110);
        List<Integer> expected = List.of(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 25, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83);
        assertEquals(expected, ludics.subList(0, 25));
    }

    @Test
    public void testLudicsUpTo1000() {
        List<Integer> ludics = Ludic.ludicUpTo(1000);
        assertEquals(241, ludics.size());
    }

    @Test
    public void test2000thTo2005thLudics() {
        List<Integer> ludics = Ludic.ludicUpTo(22000);
        List<Integer> expected = List.of(1759, 1783, 1787, 1801, 1811, 1823);
        assertEquals(expected, ludics.subList(1999, 2005));
    }

    @Test
    public void testTripletsUpTo250() {
        List<List<Integer>> triplets = Ludic.getTriplets(Ludic.ludicUpTo(250));
        List<List<Integer>> expected = List.of(
            List.of(1, 3, 7),
            List.of(3, 5, 11),
            List.of(7, 9, 15),
            List.of(13, 15, 21),
            List.of(15, 17, 23),
            List.of(21, 23, 29),
            List.of(31, 33, 39),
            List.of(37, 39, 45),
            List.of(43, 45, 51),
            List.of(49, 51, 57),
            List.of(55, 57, 63),
            List.of(61, 63, 69),
            List.of(67, 69, 75),
            List.of(73, 75, 81),
            List.of(79, 81, 87),
            List.of(91, 93, 99),
            List.of(97, 99, 105),
            List.of(103, 105, 111),
            List.of(109, 111, 117),
            List.of(115, 117, 123),
            List.of(121, 123, 129),
            List.of(127, 129, 135),
            List.of(133, 135, 141),
            List.of(139, 141, 147),
            List.of(145, 147, 153),
            List.of(151, 153, 159),
            List.of(157, 159, 165),
            List.of(163, 165, 171),
            List.of(169, 171, 177),
            List.of(175, 177, 183),
            List.of(181, 183, 189),
            List.of(187, 189, 195),
            List.of(193, 195, 201),
            List.of(199, 201, 207),
            List.of(205, 207, 213),
            List.of(211, 213, 219),
            List.of(217, 219, 225),
            List.of(223, 225, 231),
            List.of(229, 231, 237),
            List.of(235, 237, 243)
        );
        assertEquals(expected, triplets);
    }
}
