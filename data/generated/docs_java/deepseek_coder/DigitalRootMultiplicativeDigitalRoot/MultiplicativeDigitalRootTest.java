import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiplicativeDigitalRootTest {

    @Test
    public void testMultiplicativeDigitalRoot() {
        // Test cases for multiplicativeDigitalRoot method
        assertArrayEquals(new long[]{123321, 8, 3}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(123321));
        assertArrayEquals(new long[]{7739, 8, 3}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(7739));
        assertArrayEquals(new long[]{893, 2, 4}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(893));
        assertArrayEquals(new long[]{899998, 0, 2}, MultiplicativeDigitalRoot.multiplicativeDigitalRoot(899998));
    }

    @Test
    public void testMainOutput() {
        // Test the main method output by capturing System.out
        // This test is more complex and involves capturing and comparing the output
        // Here we just ensure the main method runs without exceptions
        assertDoesNotThrow(() -> MultiplicativeDigitalRoot.main(new String[]{}));
    }

    @Test
    public void testTableGeneration() {
        // Test the table generation logic
        Map<Long, List<Long>> expectedTable = new HashMap<>();
        expectedTable.put(0L, List.of(0L, 10L, 20L, 25L, 30L));
        expectedTable.put(1L, List.of(1L, 11L, 111L, 1111L, 11111L));
        expectedTable.put(2L, List.of(2L, 12L, 21L, 26L, 34L));
        expectedTable.put(3L, List.of(3L, 13L, 31L, 113L, 131L));
        expectedTable.put(4L, List.of(4L, 14L, 22L, 27L, 39L));
        expectedTable.put(5L, List.of(5L, 15L, 35L, 51L, 53L));
        expectedTable.put(6L, List.of(6L, 16L, 23L, 28L, 32L));
        expectedTable.put(7L, List.of(7L, 17L, 71L, 117L, 171L));
        expectedTable.put(8L, List.of(8L, 18L, 24L, 29L, 36L));
        expectedTable.put(9L, List.of(9L, 19L, 33L, 91L, 119L));

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
