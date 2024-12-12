import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Random;

public class DutchNationalFlagTest {

    @Test
    public void testDutchNationalFlagSorting() {
        DutchNationalFlag.DutchColors[] balls = new DutchNationalFlag.DutchColors[12];
        DutchNationalFlag.DutchColors[] values = DutchNationalFlag.DutchColors.values();
        Random rand = new Random();

        // Generate a randomized order of balls
        for (int i = 0; i < balls.length; i++) {
            balls[i] = values[rand.nextInt(values.length)];
        }

        // Sort the balls
        Arrays.sort(balls);

        // Check if the sorted balls are in the order of the Dutch national flag
        boolean sorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                sorted = false;
                break;
            }
        }

        // Assert that the balls are correctly sorted
        assertTrue(sorted, "The balls are not correctly sorted in the order of the Dutch national flag.");
    }

    @Test
    public void testDutchNationalFlagSortingWithAllRedBalls() {
        DutchNationalFlag.DutchColors[] balls = new DutchNationalFlag.DutchColors[12];
        Arrays.fill(balls, DutchNationalFlag.DutchColors.RED);

        // Sort the balls
        Arrays.sort(balls);

        // Check if the sorted balls are in the order of the Dutch national flag
        boolean sorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                sorted = false;
                break;
            }
        }

        // Assert that the balls are correctly sorted
        assertTrue(sorted, "The balls are not correctly sorted in the order of the Dutch national flag.");
    }

    @Test
    public void testDutchNationalFlagSortingWithAllWhiteBalls() {
        DutchNationalFlag.DutchColors[] balls = new DutchNationalFlag.DutchColors[12];
        Arrays.fill(balls, DutchNationalFlag.DutchColors.WHITE);

        // Sort the balls
        Arrays.sort(balls);

        // Check if the sorted balls are in the order of the Dutch national flag
        boolean sorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                sorted = false;
                break;
            }
        }

        // Assert that the balls are correctly sorted
        assertTrue(sorted, "The balls are not correctly sorted in the order of the Dutch national flag.");
    }

    @Test
    public void testDutchNationalFlagSortingWithAllBlueBalls() {
        DutchNationalFlag.DutchColors[] balls = new DutchNationalFlag.DutchColors[12];
        Arrays.fill(balls, DutchNationalFlag.DutchColors.BLUE);

        // Sort the balls
        Arrays.sort(balls);

        // Check if the sorted balls are in the order of the Dutch national flag
        boolean sorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                sorted = false;
                break;
            }
        }

        // Assert that the balls are correctly sorted
        assertTrue(sorted, "The balls are not correctly sorted in the order of the Dutch national flag.");
    }

    @Test
    public void testDutchNationalFlagSortingWithMixedBalls() {
        DutchNationalFlag.DutchColors[] balls = {
            DutchNationalFlag.DutchColors.WHITE,
            DutchNationalFlag.DutchColors.RED,
            DutchNationalFlag.DutchColors.BLUE,
            DutchNationalFlag.DutchColors.WHITE,
            DutchNationalFlag.DutchColors.RED,
            DutchNationalFlag.DutchColors.BLUE,
            DutchNationalFlag.DutchColors.WHITE,
            DutchNationalFlag.DutchColors.RED,
            DutchNationalFlag.DutchColors.BLUE,
            DutchNationalFlag.DutchColors.WHITE,
            DutchNationalFlag.DutchColors.RED,
            DutchNationalFlag.DutchColors.BLUE
        };

        // Sort the balls
        Arrays.sort(balls);

        // Check if the sorted balls are in the order of the Dutch national flag
        boolean sorted = true;
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                sorted = false;
                break;
            }
        }

        // Assert that the balls are correctly sorted
        assertTrue(sorted, "The balls are not correctly sorted in the order of the Dutch national flag.");
    }
}
