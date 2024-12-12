import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HundredDoorsTest {

    @Test
    void testDoorsStateAfterPasses() {
        boolean[] doors = new boolean[101];

        for (int i = 1; i < doors.length; i++) {
            for (int j = i; j < doors.length; j += i) {
                doors[j] = !doors[j];
            }
        }

        // Only doors with perfect square numbers should be open
        for (int i = 1; i < doors.length; i++) {
            boolean expectedState = isPerfectSquare(i);
            assertEquals(expectedState, doors[i], "Door " + i + " state is incorrect.");
        }
    }

    private boolean isPerfectSquare(int number) {
        int sqrt = (int) Math.sqrt(number);
        return sqrt * sqrt == number;
    }
}
