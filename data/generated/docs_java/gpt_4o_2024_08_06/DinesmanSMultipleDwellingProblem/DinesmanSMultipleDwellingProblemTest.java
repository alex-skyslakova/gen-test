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
        assertTrue(DinesmanMultipleDwelling.adjacent("BCFMS", "C", "F"));
        assertFalse(DinesmanMultipleDwelling.adjacent("BCFMS", "B", "M"));
    }

    @Test
    void testIsPossible() {
        assertTrue(DinesmanMultipleDwelling.isPossible("MBCFS"));
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFMS"));
    }

    @Test
    void testGeneratePermutations() {
        Set<String> set = new HashSet<>();
        DinesmanMultipleDwelling.generatePermutations(new String[]{"B", "C", "F", "M", "S"}, set, "");
        assertEquals(120, set.size()); // 5! = 120 permutations
    }

    @Test
    void testMain() {
        // Capture the output of the main method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DinesmanMultipleDwelling.main(new String[]{});

        String output = outContent.toString().trim();
        assertTrue(output.contains("Possible arrangement: MBCFS"));

        // Reset the standard output
        System.setOut(System.out);
    }
}
