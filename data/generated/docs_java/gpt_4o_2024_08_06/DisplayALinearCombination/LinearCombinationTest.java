import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearCombinationTest {

    @Test
    public void testLinearCombination() {
        assertEquals("e(1) + 2*e(2) + 3*e(3)", LinearCombination.linearCombo(new int[]{1, 2, 3}));
        assertEquals("e(2) + 2*e(3) + 3*e(4)", LinearCombination.linearCombo(new int[]{0, 1, 2, 3}));
        assertEquals("e(1) + 3*e(3) + 4*e(4)", LinearCombination.linearCombo(new int[]{1, 0, 3, 4}));
        assertEquals("e(1) + 2*e(2)", LinearCombination.linearCombo(new int[]{1, 2, 0}));
        assertEquals("0", LinearCombination.linearCombo(new int[]{0, 0, 0}));
        assertEquals("0", LinearCombination.linearCombo(new int[]{0}));
        assertEquals("e(1) + e(2) + e(3)", LinearCombination.linearCombo(new int[]{1, 1, 1}));
        assertEquals("-e(1) - e(2) - e(3)", LinearCombination.linearCombo(new int[]{-1, -1, -1}));
        assertEquals("-e(1) - 2*e(2) - 3*e(4)", LinearCombination.linearCombo(new int[]{-1, -2, 0, -3}));
        assertEquals("-e(1)", LinearCombination.linearCombo(new int[]{-1}));
    }
}
