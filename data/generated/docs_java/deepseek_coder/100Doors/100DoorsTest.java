import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HundredDoorsTest {

    @Test
    void testInitialDoorsState() {
        boolean[] doors = new boolean[101];
        for (int i = 1; i < doors.length; i++) {
            assertFalse(doors[i], "Door " + i + " should be closed initially.");
        }
    }

    @Test
    void testDoorsAfterPasses() {
        boolean[] doors = new boolean[101];

        for (int i = 1; i < doors.length; i++) {
            for (int j = i; j < doors.length; j += i) {
                doors[j] = !doors[j];
            }
        }

        // Verify that only doors with perfect square numbers are open
        for (int i = 1; i < doors.length; i++) {
            if (isPerfectSquare(i)) {
                assertTrue(doors[i], "Door " + i + " should be open.");
            } else {
                assertFalse(doors[i], "Door " + i + " should be closed.");
            }
        }
    }

    private boolean isPerfectSquare(int number) {
        int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }
}
