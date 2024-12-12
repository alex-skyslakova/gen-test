import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BestShuffleTest {

    @Test
    public void testBestShuffle_abracadabra() {
        String result = BestShuffle.bestShuffle("abracadabra");
        String[] parts = result.split(" ");
        assertEquals("abracadabra", parts[0]);
        assertNotEquals("abracadabra", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_seesaw() {
        String result = BestShuffle.bestShuffle("seesaw");
        String[] parts = result.split(" ");
        assertEquals("seesaw", parts[0]);
        assertNotEquals("seesaw", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_elk() {
        String result = BestShuffle.bestShuffle("elk");
        String[] parts = result.split(" ");
        assertEquals("elk", parts[0]);
        assertNotEquals("elk", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_grrrrrr() {
        String result = BestShuffle.bestShuffle("grrrrrr");
        String[] parts = result.split(" ");
        assertEquals("grrrrrr", parts[0]);
        assertNotEquals("grrrrrr", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_up() {
        String result = BestShuffle.bestShuffle("up");
        String[] parts = result.split(" ");
        assertEquals("up", parts[0]);
        assertNotEquals("up", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_a() {
        String result = BestShuffle.bestShuffle("a");
        String[] parts = result.split(" ");
        assertEquals("a", parts[0]);
        assertNotEquals("a", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_emptyString() {
        String result = BestShuffle.bestShuffle("");
        String[] parts = result.split(" ");
        assertEquals("", parts[0]);
        assertEquals("", parts[1]);
        assertEquals("(0)", parts[2]);
    }

    @Test
    public void testBestShuffle_allSameCharacters() {
        String result = BestShuffle.bestShuffle("aaaa");
        String[] parts = result.split(" ");
        assertEquals("aaaa", parts[0]);
        assertNotEquals("aaaa", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_mixedCharacters() {
        String result = BestShuffle.bestShuffle("abcabc");
        String[] parts = result.split(" ");
        assertEquals("abcabc", parts[0]);
        assertNotEquals("abcabc", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }

    @Test
    public void testBestShuffle_longString() {
        String result = BestShuffle.bestShuffle("abcdefghijklmnopqrstuvwxyz");
        String[] parts = result.split(" ");
        assertEquals("abcdefghijklmnopqrstuvwxyz", parts[0]);
        assertNotEquals("abcdefghijklmnopqrstuvwxyz", parts[1]);
        assertTrue(Integer.parseInt(parts[2].replace("(", "").replace(")", "")) >= 0);
    }
}
