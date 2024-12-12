import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearCombinationTest {

    @Test
    public void testLinearCombo_Case1() {
        int[] c = {1, 2, 3};
        String expected = "e(1) + 2*e(2) + 3*e(3)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case2() {
        int[] c = {0, 1, 2, 3};
        String expected = "e(2) + 2*e(3) + 3*e(4)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case3() {
        int[] c = {1, 0, 3, 4};
        String expected = "e(1) + 3*e(3) + 4*e(4)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case4() {
        int[] c = {1, 2, 0};
        String expected = "e(1) + 2*e(2)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case5() {
        int[] c = {0, 0, 0};
        String expected = "0";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case6() {
        int[] c = {0};
        String expected = "0";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case7() {
        int[] c = {1, 1, 1};
        String expected = "e(1) + e(2) + e(3)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case8() {
        int[] c = {-1, -1, -1};
        String expected = "-e(1) - e(2) - e(3)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case9() {
        int[] c = {-1, -2, 0, -3};
        String expected = "-e(1) - 2*e(2) - 3*e(4)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }

    @Test
    public void testLinearCombo_Case10() {
        int[] c = {-1};
        String expected = "-e(1)";
        assertEquals(expected, LinearCombination.linearCombo(c));
    }
}
