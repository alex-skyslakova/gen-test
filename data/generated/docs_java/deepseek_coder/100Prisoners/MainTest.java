import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testPlayOptimal_Success() {
        // Arrange
        int n = 100;
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.shuffle(secretList);

        // Act
        boolean result = Main.playOptimal(n);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testPlayOptimal_Failure() {
        // Arrange
        int n = 100;
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        // Force a failure by ensuring no prisoner finds their number
        for (int i = 0; i < n; i++) {
            secretList.set(i, (i + 1) % n);
        }

        // Act
        boolean result = Main.playOptimal(n);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testPlayRandom_Success() {
        // Arrange
        int n = 100;
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        Collections.shuffle(secretList);

        // Act
        boolean result = Main.playRandom(n);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testPlayRandom_Failure() {
        // Arrange
        int n = 100;
        List<Integer> secretList = IntStream.range(0, n).boxed().collect(Collectors.toList());
        // Force a failure by ensuring no prisoner finds their number
        for (int i = 0; i < n; i++) {
            secretList.set(i, (i + 1) % n);
        }

        // Act
        boolean result = Main.playRandom(n);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testExec_OptimalPlay() {
        // Arrange
        int n = 1000;
        int p = 100;

        // Act
        double result = Main.exec(n, p, Main::playOptimal);

        // Assert
        assertTrue(result >= 0.0 && result <= 100.0);
    }

    @Test
    public void testExec_RandomPlay() {
        // Arrange
        int n = 1000;
        int p = 100;

        // Act
        double result = Main.exec(n, p, Main::playRandom);

        // Assert
        assertTrue(result >= 0.0 && result <= 100.0);
    }
}
