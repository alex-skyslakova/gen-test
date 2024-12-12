import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class LISTest {

    @Test
    void testLisEmptyList() {
        List<Integer> emptyList = new ArrayList<>();
        List<Integer> result = LIS.lis(emptyList);
        assertTrue(result.isEmpty());
    }

    @Test
    void testLisExample1() {
        List<Integer> input = Arrays.asList(3, 2, 6, 4, 5, 1);
        List<Integer> expected = Arrays.asList(2, 4, 5);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    void testLisExample2() {
        List<Integer> input = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        List<Integer> expected = Arrays.asList(0, 2, 6, 9, 11, 15);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    void testLisSortedList() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    void testLisReverseSortedList() {
        List<Integer> input = Arrays.asList(6, 5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(6); // or any single element
        List<Integer> result = LIS.lis(input);
        assertEquals(1, result.size());
    }

    @Test
    void testLisWithDuplicates() {
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);

    }
    @Test
    void testLisWithNegativeNumbers() {
        List<Integer> input = Arrays.asList(-5, -2, 0, 3, 6);
        List<Integer> expected = Arrays.asList(-5, -2, 0, 3, 6);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }


}
