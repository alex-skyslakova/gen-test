import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LinearCombinationTest {

    @Test
    void testLinearCombo1() {
        assertEquals("e(1) + 2*e(2) + 3*e(3)", LinearCombination.linearCombo(new int[]{1, 2, 3}));
    }

    @Test
    void testLinearCombo2() {
        assertEquals("e(2) + 2*e(3) + 3*e(4)", LinearCombination.linearCombo(new int[]{0, 1, 2, 3}));
    }

    @Test
    void testLinearCombo3() {
        assertEquals("e(1) + 3*e(3) + 4*e(4)", LinearCombination.linearCombo(new int[]{1, 0, 3, 4}));
    }

    @Test
    void testLinearCombo4() {
        assertEquals("e(1) + 2*e(2)", LinearCombination.linearCombo(new int[]{1, 2, 0}));
    }

    @Test
    void testLinearCombo5() {
        assertEquals("0", LinearCombination.linearCombo(new int[]{0, 0, 0}));
    }

    @Test
    void testLinearCombo6() {
        assertEquals("0", LinearCombination.linearCombo(new int[]{0}));
    }

    @Test
    void testLinearCombo7() {
        assertEquals("e(1) + e(2) + e(3)", LinearCombination.linearCombo(new int[]{1, 1, 1}));
    }

    @Test
    void testLinearCombo8() {
        assertEquals("-e(1) - e(2) - e(3)", LinearCombination.linearCombo(new int[]{-1, -1, -1}));
    }

    @Test
    void testLinearCombo9() {
        assertEquals("-e(1) - 2*e(2) - 3*e(4)", LinearCombination.linearCombo(new int[]{-1, -2, 0, -3}));
    }

    @Test
    void testLinearCombo10() {
        assertEquals("-e(1)", LinearCombination.linearCombo(new int[]{-1}));
    }

    @Test
    void testLinearComboEmpty() {
        assertEquals("0", LinearCombination.linearCombo(new int[]{}));
    }

    @Test
    void testLinearComboMixedSigns() {
        assertEquals("e(1) - 2*e(2) + 3*e(3) - e(4)", LinearCombination.linearCombo(new int[]{1, -2, 3, -1}));
    }

     @Test
    void testLinearComboLeadingZeroCoefficient() {
        assertEquals("2*e(2)", LinearCombination.linearCombo(new int[]{0, 2}));
    }

    @Test
    void testLinearComboAllZerosExceptLast() {
         assertEquals("3*e(4)", LinearCombination.linearCombo(new int[]{0, 0, 0, 3}));
    }


}
