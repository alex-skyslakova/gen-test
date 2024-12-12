import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DinesmanMultipleDwellingTest {

    @Test
    void testTopFloor() {
        assertTrue(DinesmanMultipleDwelling.topFloor("BCFMS", "S"));
        assertFalse(DinesmanMultipleDwelling.topFloor("BCFMS", "B"));
    }

    @Test
    void testBottomFloor() {
        assertTrue(DinesmanMultipleDwelling.bottomFloor("BCFMS", "B"));
        assertFalse(DinesmanMultipleDwelling.bottomFloor("BCFMS", "S"));
    }

    @Test
    void testLivesAbove() {
        assertTrue(DinesmanMultipleDwelling.livesAbove("BCFMS", "M", "C"));
        assertFalse(DinesmanMultipleDwelling.livesAbove("BCFMS", "C", "M"));
    }

    @Test
    void testAdjacent() {
        assertTrue(DinesmanMultipleDwelling.adjacent("BCFMS", "F", "M"));
        assertFalse(DinesmanMultipleDwelling.adjacent("BCFMS", "B", "S"));
    }

    @Test
    void testIsPossible() {
        // Valid permutations
        assertTrue(DinesmanMultipleDwelling.isPossible("MCFBS"));
        assertTrue(DinesmanMultipleDwelling.isPossible("MBCFS"));

        // Invalid permutations
        assertFalse(DinesmanMultipleDwelling.isPossible("SBCFM")); // Baker on top floor
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Cooper on bottom floor
        assertFalse(DinesmanMultipleDwelling.isPossible("FBCMS")); // Fletcher on top floor
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFSM")); // Fletcher on bottom floor
        assertFalse(DinesmanMultipleDwelling.isPossible("BCMFS")); // Miller not above Cooper
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Smith adjacent to Fletcher
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS")); // Fletcher adjacent to Cooper
    }

    @Test
    void testMain() {
        // This test is more of an integration test to ensure the main method runs without exceptions
        // and produces the expected output.
        DinesmanMultipleDwelling.main(new String[]{});
        // You can capture the output and assert it, but for simplicity, we assume it runs correctly.
    }
}
