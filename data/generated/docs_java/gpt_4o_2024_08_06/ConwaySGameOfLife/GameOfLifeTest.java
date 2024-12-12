import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class GameOfLifeTest {

    @Test
    public void testBlinkerPattern() {
        String[] initialGeneration = {
            "_#_",
            "_#_",
            "_#_"
        };

        String[] expectedGeneration1 = {
            "___",
            "###",
            "___"
        };

        String[] expectedGeneration2 = {
            "_#_",
            "_#_",
            "_#_"
        };

        // Generation 0 to Generation 1
        String[] generation1 = GameOfLife.life(initialGeneration);
        assertArrayEquals(expectedGeneration1, generation1);

        // Generation 1 to Generation 2
        String[] generation2 = GameOfLife.life(generation1);
        assertArrayEquals(expectedGeneration2, generation2);

        // Generation 2 to Generation 3
        String[] generation3 = GameOfLife.life(generation2);
        assertArrayEquals(expectedGeneration1, generation3);
    }
}
