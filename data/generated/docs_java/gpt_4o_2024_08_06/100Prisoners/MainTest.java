import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testPlayOptimalSuccess() {
        // Test the optimal strategy with a known configuration that should succeed
        assertTrue(Main.playOptimal(100));
    }

    @Test
    public void testPlayOptimalFailure() {
        // Test the optimal strategy with a known configuration that should fail
        // Since the playOptimal method uses randomness, we can't directly test for failure
        // Instead, we will run it multiple times and expect at least one failure
        boolean hasFailed = false;
        for (int i = 0; i < 1000; i++) {
            if (!Main.playOptimal(100)) {
                hasFailed = true;
                break;
            }
        }
        assertTrue(hasFailed, "Expected at least one failure in 1000 runs");
    }

    @Test
    public void testPlayRandomSuccess() {
        // Test the random strategy with a known configuration that should succeed
        // Since the playRandom method uses randomness, we can't directly test for success
        // Instead, we will run it multiple times and expect at least one success
        boolean hasSucceeded = false;
        for (int i = 0; i < 1000; i++) {
            if (Main.playRandom(100)) {
                hasSucceeded = true;
                break;
            }
        }
        assertTrue(hasSucceeded, "Expected at least one success in 1000 runs");
    }

    @Test
    public void testPlayRandomFailure() {
        // Test the random strategy with a known configuration that should fail
        assertFalse(Main.playRandom(100));
    }

    @Test
    public void testExecOptimal() {
        // Test the exec method with the optimal strategy
        double successRate = Main.exec(1000, 100, Main::playOptimal);
        assertTrue(successRate > 0, "Expected success rate greater than 0 for optimal strategy");
    }

    @Test
    public void testExecRandom() {
        // Test the exec method with the random strategy
        double successRate = Main.exec(1000, 100, Main::playRandom);
        assertTrue(successRate >= 0, "Expected success rate greater than or equal to 0 for random strategy");
    }
}
