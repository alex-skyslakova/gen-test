import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class DinesmanMultipleDwellingTest {

    @Test
    void testGeneratePermutations() {
        Set<String> expected = new HashSet<>();
        expected.add("BCFSM");
        expected.add("BCFS"); // Example of incomplete permutation - test will fail
        // ... (add all 120 permutations) ...  This is impractical for manual entry

        Set<String> actual = new HashSet<>();
        DinesmanMultipleDwelling.generatePermutations(new String[]{"B", "C", "F", "S", "M"}, actual, "");

        assertEquals(120, actual.size()); //Check for total number of permutations
    }


    @Test
    void testTopFloor() {
        assertTrue(DinesmanMultipleDwelling.topFloor("BCFSM", "M"));
        assertFalse(DinesmanMultipleDwelling.topFloor("BCFSM", "B"));
    }

    @Test
    void testBottomFloor() {
        assertTrue(DinesmanMultipleDwelling.bottomFloor("BCFSM", "B"));
        assertFalse(DinesmanMultipleDwelling.bottomFloor("BCFSM", "M"));
    }

    @Test
    void testLivesAbove() {
        assertTrue(DinesmanMultipleDwelling.livesAbove("BCFSM", "M", "C"));
        assertFalse(DinesmanMultipleDwelling.livesAbove("BCFSM", "C", "M"));
    }

    @Test
    void testAdjacent() {
        assertTrue(DinesmanMultipleDwelling.adjacent("BCFSM", "B", "C"));
        assertFalse(DinesmanMultipleDwelling.adjacent("BCFSM", "B", "M"));
    }

    @Test
    void testIsPossible() {
        assertTrue(DinesmanMultipleDwelling.isPossible("MFCBS")); // The correct solution
        assertFalse(DinesmanMultipleDwelling.isPossible("BCFSM")); // Example of an invalid solution
        assertFalse(DinesmanMultipleDwelling.isPossible("CBFSM")); // Violates Cooper not on bottom
        assertFalse(DinesmanMultipleDwelling.isPossible("MBCSF")); // Violates Miller above Cooper
        assertFalse(DinesmanMultipleDwelling.isPossible("MCBFS")); // Violates Smith not adjacent Fletcher, Fletcher not adjacent Cooper
    }


     @Test
     void testMain(){
        //The main method prints to the console, so testing this directly is difficult.
        //Refactor the main method to return a collection of results to make it easier to test directly.

        //For now we can just ensure that it runs without exceptions
         assertDoesNotThrow(() -> DinesmanMultipleDwelling.main(new String[]{}));



     }

}
