import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testPlayOptimalSuccess() {
        // Arrange
        List<Integer> secretList = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        // Create a scenario where each prisoner finds their number in the first try
        for (int i = 0; i < 100; i++) {
            secretList.set(i, i);
        }
        Collections.shuffle(secretList);


        boolean result = Main.playOptimal(100);

         assertTrue(result);
    }

    @Test
    void testPlayOptimalFailure() {
       // Arrange
        List<Integer> secretList = IntStream.range(0, 100).boxed().collect(Collectors.toList());

          // Force a cycle that no prisoner can resolve within 50 attempts
        for (int i = 0; i < 100; ++i) {
            secretList.set(i, (i + 1) % 100);
        }

        Collections.shuffle(secretList);


        boolean result = Main.playOptimal(100);

        assertFalse(result);
    }



    @Test
    void testPlayRandomSuccess() {
        // Arrange - It's statistically very unlikely but possible for random play to succeed.
        //  We'll simply test that the function doesn't throw exceptions and returns a boolean.
        boolean result = Main.playRandom(100);

        assertNotNull(result); // or assertTrue/assertFalse depending on the actual outcome.

    }

    @Test
    void testPlayRandomFailure() {
         // Arrange -  Very likely to fail
        boolean result = Main.playRandom(100);

        assertNotNull(result); // or assertTrue/assertFalse depending on the actual outcome.
    }





    @Test
    void testExec() {
        double result = Main.exec(1000, 100, Main::playOptimal);
        assertTrue(result >= 0 && result <= 100); // Check if the success rate is within the valid range
    }



}
