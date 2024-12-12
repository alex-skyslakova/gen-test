import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.IntStream;

public class LinearCongruentialGeneratorTest {

    @Test
    public void testRandBSD() {
        int seed = 0;
        int[] expected = {12345, 1406932606, 654583775, 1449466924, 229283573, 1109335178, 1051550459, 1293799192, 794471793, 551188310};
        IntStream generatedStream = LinearCongruentialGenerator.randBSD(seed).limit(10);
        int[] generated = generatedStream.toArray();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], generated[i], "Mismatch at index " + i);
        }
    }

    @Test
    public void testRandMS() {
        int seed = 0;
        int[] expected = {38, 8477, 7325, 8697, 6925, 2687, 15316, 264, 11942, 5525};
        IntStream generatedStream = LinearCongruentialGenerator.randMS(seed).limit(10);
        int[] generated = generatedStream.toArray();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], generated[i], "Mismatch at index " + i);
        }
    }
}
