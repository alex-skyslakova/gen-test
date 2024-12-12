import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HundredDoorsTest {

    @Test
    void testDoorsState() {
        boolean[] doors = new boolean[101];

        for (int i = 1; i < doors.length; i++) {
            for (int j = i; j < doors.length; j += i) {
                doors[j] = !doors[j];
            }
        }

        // Check if doors 1, 4, 9, 16, 25, 36, 49, 64, 81, and 100 are open
        assertTrue(doors[1]);
        assertTrue(doors[4]);
        assertTrue(doors[9]);
        assertTrue(doors[16]);
        assertTrue(doors[25]);
        assertTrue(doors[36]);
        assertTrue(doors[49]);
        assertTrue(doors[64]);
        assertTrue(doors[81]);
        assertTrue(doors[100]);


        // Check if a few other doors are closed (e.g., 2, 3, 5, 6, 7, 8)
        assertFalse(doors[2]);
        assertFalse(doors[3]);
        assertFalse(doors[5]);
        assertFalse(doors[6]);
        assertFalse(doors[7]);
        assertFalse(doors[8]);

        //More comprehensive check for all doors.  Only perfect squares should be open
        for (int i = 1; i < doors.length; i++) {
            if (isPerfectSquare(i)) {
                assertTrue(doors[i], "Door " + i + " should be open.");
            } else {
                assertFalse(doors[i], "Door " + i + " should be closed.");
            }
        }
    }


    private boolean isPerfectSquare(int n) {
        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n;
    }
}
