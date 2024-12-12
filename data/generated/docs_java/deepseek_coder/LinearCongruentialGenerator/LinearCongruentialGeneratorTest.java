import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearCongruentialGeneratorTest {

    @Test
    public void testRandBSD() {
        // Expected sequence for BSD formula with seed 0
        List<Integer> expectedBSD = List.of(
                12345, 1406932606, 654583775, 1449466924, 229283573,
                1109335178, 1051550459, 1293799192, 794471793, 551188310
        );

        // Generate the sequence using the randBSD method
        List<Integer> actualBSD = LinearCongruentialGenerator.randBSD(0)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());

        // Assert that the generated sequence matches the expected sequence
        assertEquals(expectedBSD, actualBSD);
    }

    @Test
    public void testRandMS() {
        // Expected sequence for Microsoft formula with seed 0
        List<Integer> expectedMS = List.of(
                38, 7719, 21238, 2437, 8855, 11797, 8365, 32285, 10450, 30612
        );

        // Generate the sequence using the randMS method
        List<Integer> actualMS = LinearCongruentialGenerator.randMS(0)
                .limit(10)
                .boxed()
                .collect(Collectors.toList());

        // Assert that the generated sequence matches the expected sequence
        assertEquals(expectedMS, actualMS);
    }
}
