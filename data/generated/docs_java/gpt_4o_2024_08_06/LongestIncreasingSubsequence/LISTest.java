import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

public class LISTest {

    @Test
    public void testLISWithExample1() {
        List<Integer> input = Arrays.asList(3, 2, 6, 4, 5, 1);
        List<Integer> expected = Arrays.asList(2, 4, 5);
        assertEquals(expected, LIS.lis(input));
    }

    @Test
    public void testLISWithExample2() {
        List<Integer> input = Arrays.asList(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        List<Integer> expected = Arrays.asList(0, 2, 6, 9, 11, 15);
        assertEquals(expected, LIS.lis(input));
    }

    @Test
    public void testLISWithEmptyList() {
        List<Integer> input = Arrays.asList();
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, LIS.lis(input));
    }

    @Test
    public void testLISWithSingleElement() {
        List<Integer> input = Arrays.asList(5);
        List<Integer> expected = Arrays.asList(5);
        assertEquals(expected, LIS.lis(input));
    }

    @Test
    public void testLISWithAllIncreasing() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, LIS.lis(input));
    }

    @Test
    public void testLISWithAllDecreasing() {
        List<Integer> input = Arrays.asList(5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, LIS.lis(input));
    }

    @Test
    public void testLISWithDuplicates() {
        List<Integer> input = Arrays.asList(1, 2, 2, 3, 3, 4, 4, 5, 5);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, LIS.lis(input));
    }
}
