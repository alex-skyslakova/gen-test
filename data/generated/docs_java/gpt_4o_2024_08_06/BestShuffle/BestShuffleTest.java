import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BestShuffleTest {

    @Test
    public void testBestShuffle_abracadabra() {
        String result = BestShuffle.bestShuffle("abracadabra");
        assertTrue(result.startsWith("abracadabra "));
        assertTrue(result.endsWith("(0)"));
    }

    @Test
    public void testBestShuffle_seesaw() {
        String result = BestShuffle.bestShuffle("seesaw");
        assertTrue(result.startsWith("seesaw "));
        assertTrue(result.endsWith("(0)"));
    }

    @Test
    public void testBestShuffle_grrrrrr() {
        String result = BestShuffle.bestShuffle("grrrrrr");
        assertTrue(result.startsWith("grrrrrr "));
        assertTrue(result.endsWith("(6)"));
    }

    @Test
    public void testBestShuffle_pop() {
        String result = BestShuffle.bestShuffle("pop");
        assertTrue(result.startsWith("pop "));
        assertTrue(result.endsWith("(0)"));
    }

    @Test
    public void testBestShuffle_up() {
        String result = BestShuffle.bestShuffle("up");
        assertTrue(result.startsWith("up "));
        assertTrue(result.endsWith("(0)"));
    }

    @Test
    public void testBestShuffle_a() {
        String result = BestShuffle.bestShuffle("a");
        assertTrue(result.startsWith("a "));
        assertTrue(result.endsWith("(1)"));
    }

    @Test
    public void testShuffleDifferentFromOriginal() {
        String original = "elk";
        String result = BestShuffle.bestShuffle(original);
        String shuffled = result.split(" ")[1];
        assertNotEquals(original, shuffled);
    }

    @Test
    public void testShuffleScore() {
        String original = "elk";
        String result = BestShuffle.bestShuffle(original);
        String shuffled = result.split(" ")[1];
        int score = Integer.parseInt(result.substring(result.indexOf('(') + 1, result.indexOf(')')));
        assertEquals(BestShuffle.count(original, shuffled.toCharArray()), score);
    }
}
