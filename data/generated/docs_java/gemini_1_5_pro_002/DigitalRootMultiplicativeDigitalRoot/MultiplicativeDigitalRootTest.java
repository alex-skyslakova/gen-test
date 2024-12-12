import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class MultiplicativeDigitalRootTest {

    @Test
    void testMultiplicativeDigitalRoot_singleDigit() {
        assertArrayEquals(new long[]{5, 5, 0}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(5));
    }

    @Test
    void testMultiplicativeDigitalRoot_multipleDigits() {
        assertArrayEquals(new long[]{123321, 0, 2}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(123321));
        assertArrayEquals(new long[]{7739, 0, 2}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(7739));
        assertArrayEquals(new long[]{893, 6, 2}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(893));
        assertArrayEquals(new long[]{899998, 0, 2}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(899998));

    }
    @Test
    void testMultiplicativeDigitalRoot_zero() {
        assertArrayEquals(new long[]{0, 0, 0}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(0));
    }


    @Test
    void testMultiplicativeDigitalRoot_powersOfTen() {
        assertArrayEquals(new long[]{10, 0, 1}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(10));
        assertArrayEquals(new long[]{100, 0, 1}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(100));

    }

    @Test
    void testFirstFiveMDR() {
        Map<Long, List<Long>> expectedTable = new HashMap<>();
        for (long i = 0; i < 10; i++)
            expectedTable.put(i, new ArrayList<>());

        expectedTable.get(0L).addAll(List.of(0L, 10L, 20L, 25L, 30L));
        expectedTable.get(1L).addAll(List.of(1L, 11L, 111L, 1111L, 11111L));
        expectedTable.get(2L).addAll(List.of(2L, 12L, 21L, 26L, 34L));
        expectedTable.get(3L).addAll(List.of(3L, 13L, 31L, 113L, 131L));
        expectedTable.get(4L).addAll(List.of(4L, 14L, 22L, 27L, 39L));
        expectedTable.get(5L).addAll(List.of(5L, 15L, 35L, 51L, 53L));
        expectedTable.get(6L).addAll(List.of(6L, 16L, 23L, 28L, 32L));
        expectedTable.get(7L).addAll(List.of(7L, 17L, 71L, 117L, 171L));
        expectedTable.get(8L).addAll(List.of(8L, 18L, 24L, 29L, 36L));
        expectedTable.get(9L).addAll(List.of(9L, 19L, 33L, 91L, 119L));


        Map<Long, List<Long>> actualTable = new HashMap<>();
        for (long i = 0; i < 10; i++)
            actualTable.put(i, new ArrayList<>());

        for (long cnt = 0, n = 0; cnt < 10;) {
            long[] res = MultiplicativeDigitalRoot.multiplicativeDigitalRoot(n++);
            List<Long> list = actualTable.get(res[1]);
            if (list.size() < 5) {
                list.add(res[0]);
                cnt = list.size() == 5 ? cnt + 1 : cnt;
            }
        }

        assertEquals(expectedTable, actualTable);

    }
}
