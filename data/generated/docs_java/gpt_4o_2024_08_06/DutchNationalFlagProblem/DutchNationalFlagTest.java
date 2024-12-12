import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

public class DutchNationalFlagTest {

    enum DutchColors {
        RED, WHITE, BLUE
    }

    @Test
    public void testDutchNationalFlagSorting() {
        // Generate a randomized order of balls
        DutchColors[] balls = new DutchColors[12];
        DutchColors[] values = DutchColors.values();
        Random rand = new Random();

        for (int i = 0; i < balls.length; i++) {
            balls[i] = values[rand.nextInt(values.length)];
        }

        // Ensure the balls are not initially sorted
        boolean initiallySorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                initiallySorted = false;
                break;
            }
        }
        assertFalse(initiallySorted, "Balls should not be initially sorted");

        // Sort the balls
        Arrays.sort(balls);

        // Check the sorted balls are in the order of the Dutch national flag
        boolean sorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                sorted = false;
                break;
            }
        }
        assertTrue(sorted, "Balls should be sorted in the order of the Dutch national flag");
    }
}
