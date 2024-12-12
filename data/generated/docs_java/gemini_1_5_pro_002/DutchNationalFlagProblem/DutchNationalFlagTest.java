import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class DutchNationalFlagTest {

    private enum DutchColors {
        RED, WHITE, BLUE
    }

    private DutchColors[] generateRandomBalls(int numBalls) {
        DutchColors[] balls = new DutchColors[numBalls];
        DutchColors[] values = DutchColors.values();
        Random rand = new Random();
        for (int i = 0; i < balls.length; i++) {
            balls[i] = values[rand.nextInt(values.length)];
        }
        return balls;
    }


    @Test
    void testSortBalls_emptyArray() {
        DutchColors[] balls = new DutchColors[0];
        Arrays.sort(balls);
        assertTrue(isSorted(balls)); 
    }

    @Test
    void testSortBalls_singleElement() {
        DutchColors[] balls = new DutchColors[1];
        balls[0] = DutchColors.WHITE;
        Arrays.sort(balls);
        assertTrue(isSorted(balls));
    }

    @Test
    void testSortBalls_allSameColor() {
        DutchColors[] balls = new DutchColors[5];
        Arrays.fill(balls, DutchColors.BLUE);
        Arrays.sort(balls);
        assertTrue(isSorted(balls));
    }


    @Test
    void testSortBalls_randomColors() {
        DutchColors[] balls = generateRandomBalls(10);
        Arrays.sort(balls);
        assertTrue(isSorted(balls));
    }

    @Test
    void testSortBalls_preSorted() {
        DutchColors[] balls = {DutchColors.RED, DutchColors.WHITE, DutchColors.BLUE, DutchColors.BLUE};
        Arrays.sort(balls); // Sorting already sorted array.
        assertTrue(isSorted(balls));
    }

    @Test
    void testSortBalls_reverseSorted() {
        DutchColors[] balls = {DutchColors.BLUE, DutchColors.WHITE, DutchColors.RED};
        Arrays.sort(balls);
        assertTrue(isSorted(balls));
    }



    private boolean isSorted(DutchColors[] balls) {
        for (int i = 1; i < balls.length; i++) {
            if (balls[i - 1].compareTo(balls[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
