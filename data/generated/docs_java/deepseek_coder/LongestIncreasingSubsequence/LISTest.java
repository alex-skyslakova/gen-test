import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LISTest {

    @Test
    public void testLISWithSimpleList() {
        List<Integer> input = Arrays.asList(3, 2, 6, 4, 5, 1);
        List<Integer> expected = Arrays.asList(2, 4, 5);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithComplexList() {
        List<Integer> input = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        List<Integer> expected = Arrays.asList(0, 2, 6, 9, 11, 15);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithEmptyList() {
        List<Integer> input = Arrays.asList();
        List<Integer> expected = Arrays.asList();
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithSingleElementList() {
        List<Integer> input = Arrays.asList(1);
        List<Integer> expected = Arrays.asList(1);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithAllElementsSame() {
        List<Integer> input = Arrays.asList(1, 1, 1, 1, 1);
        List<Integer> expected = Arrays.asList(1);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithDecreasingOrder() {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithIncreasingOrder() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithMultipleSubsequences() {
        List<Integer> input = Arrays.asList(10, 22, 9, 33, 21, 50, 41, 60, 80);
        List<Integer> expected = Arrays.asList(10, 22, 33, 50, 60, 80);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithNegativeNumbers() {
        List<Integer> input = Arrays.asList(-2, -1, -3, -5, -4, -6);
        List<Integer> expected = Arrays.asList(-2, -1);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }

    @Test
    public void testLISWithMixedNumbers() {
        List<Integer> input = Arrays.asList(10, -1, 2, -3, 4, -5, 6, -7, 8);
        List<Integer> expected = Arrays.asList(-1, 2, 4, 6, 8);
        List<Integer> result = LIS.lis(input);
        assertEquals(expected, result);
    }
}
