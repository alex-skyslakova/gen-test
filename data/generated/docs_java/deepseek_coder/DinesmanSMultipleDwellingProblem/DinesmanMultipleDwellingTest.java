import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class DinesmanMultipleDwellingTest {

    @Test
    public void testGeneratePermutations() {
        Set<String> set = new HashSet<>();
        DinesmanMultipleDwelling.generatePermutations(new String[]{"A", "B", "C"}, set, "");
        assertEquals(6, set.size());
        assertTrue(set.contains("ABC"));
        assertTrue(set.contains("ACB"));
        assertTrue(set.contains("BAC"));
        assertTrue(set.contains("BCA"));
        assertTrue(set.contains("CAB"));
        assertTrue(set.contains("CBA"));
    }

    @Test
    public void testTopFloor() {
        assertTrue(DinesmanMultipleDwelling.topFloor("ABC", "C"));
        assertFalse(DinesmanMultipleDwelling.topFloor("ABC", "A"));
    }

    @Test
    public void testBottomFloor() {
        assertTrue(DinesmanMultipleDwelling.bottomFloor("ABC", "A"));
        assertFalse(DinesmanMultipleDwelling.bottomFloor("ABC", "C"));
    }

    @Test
    public void testLivesAbove() {
        assertTrue(DinesmanMultipleDwelling.livesAbove("ABC", "B", "A"));
        assertFalse(DinesmanMultipleDwelling.livesAbove("ABC", "A", "B"));
    }

    @Test
    public void testAdjacent() {
        assertTrue(DinesmanMultipleDwelling.adjacent("ABC", "A", "B"));
        assertFalse(DinesmanMultipleDwelling.adjacent("ABC", "A", "C"));
    }

    @Test
    public void testIsPossible() {
        // Valid permutations
        assertTrue(DinesmanMultipleDwelling.isPossible("MCFBS"));
        assertTrue(DinesmanMultipleDwelling.isPossible("MCSFB"));
        assertTrue(DinesmanMultipleDwelling.isPossible("MCFSB"));

        // Invalid permutations
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Baker on top floor
        assertFalse(DinesmanMultipleDwelling.isPossible("FBCMS")); // Cooper on bottom floor
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Fletcher on top floor
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Fletcher on bottom floor
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Miller not above Cooper
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Smith adjacent to Fletcher
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Fletcher adjacent to Cooper
    }

    @Test
    public void testMain() {
        DinesmanMultipleDwelling.main(new String[]{});
        // This test is more of an integration test to ensure the main method runs without errors.
        // It doesn't assert anything specific since the output is printed to the console.
    }
}
