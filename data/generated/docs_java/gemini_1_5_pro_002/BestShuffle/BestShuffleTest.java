import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BestShuffleTest {

    @Test
    void testAbracadabra() {
        String expectedOutputPattern = "abracadabra [a-z]+ \\(\\d+\\)";
        String actualOutput = BestShuffle.bestShuffle("abracadabra");
        assertTrue(actualOutput.matches(expectedOutputPattern));
    }

    @Test
    void testSeesaw() {
        String expectedOutputPattern = "seesaw [aesw]+ \\(\\d+\\)";
        String actualOutput = BestShuffle.bestShuffle("seesaw");
        assertTrue(actualOutput.matches(expectedOutputPattern));
    }

    @Test
    void testElk() {
        String expectedOutputPattern = "elk [ekl]+ \\(\\d+\\)";
        String actualOutput = BestShuffle.bestShuffle("elk");
        assertTrue(actualOutput.matches(expectedOutputPattern));
    }

    @Test
    void testGrrrrrr() {
        String expectedOutputPattern = "grrrrrr r+ \\(\\d+\\)";
        String actualOutput = BestShuffle.bestShuffle("grrrrrr");
        assertTrue(actualOutput.matches(expectedOutputPattern));
    }

    @Test
    void testUp() {
        String expectedOutputPattern = "up pu \\(0\\)";
        String actualOutput = BestShuffle.bestShuffle("up");
        assertEquals(expectedOutputPattern, actualOutput); // Expect perfect derangement
    }

    @Test
    void testA() {
        String expectedOutputPattern = "a a \\(1\\)";
        String actualOutput = BestShuffle.bestShuffle("a");
        assertEquals(expectedOutputPattern, actualOutput); // Single character remains in place
    }

    @Test
    void testPop(){
        String expectedOutputPattern = "pop [opp]+ \\(\\d+\\)";
        String actualOutput = BestShuffle.bestShuffle("pop");
        assertTrue(actualOutput.matches(expectedOutputPattern));
    }


    @Test
    void testEmptyString() {
        String expectedOutputPattern = "  \\(0\\)";
        String actualOutput = BestShuffle.bestShuffle("");
        assertEquals(expectedOutputPattern, actualOutput); 
    }

}
