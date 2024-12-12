import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ShufflerTest {

    @Test
    void dealGame1() {
        String expectedOutput = "JD 2D 9H JC 5D 7H 7C 5H\n" +
                "KD KC 9S 5S AD QC KH 3H\n" +
                "2S KS 9D QD JS AS AH 3C\n" +
                "4C 5C TS QH 4H AC 4D 7S\n" +
                "3S TD 4S TH 8H 2C JH 7D\n" +
                "6D 8S 8D QS 6C 3D 8C TC\n" +
                "6S 9C 2H 6H\n";

        testDealGame(1, expectedOutput);
    }

    @Test
    void dealGame617() {
        String expectedOutput = "7D AD 5C 3S 5S 8C 2D AH\n" +
                "TD 7S QD AC 6D 8H AS KH\n" +
                "TH QC 3H 9D 6S 8D 3D TC\n" +
                "KD 5H 9S 3C 8S 7H 4D JS\n" +
                "4C QS 9C 9H 7C 6H 2C 2S\n" +
                "4S TS 2H 5D JC 6C JH QH\n" +
                "JD KS KC 4H\n";
        testDealGame(617, expectedOutput);
    }

    @Test
    void getShuffledDeck_seed1() {
        Shuffler shuffler = new Shuffler();
        shuffler.seed = 1;
        String[] expected = {
                "6H", "2H", "9C", "6S", "8C", "3D", "6C", "QS",
                "8D", "8S", "6D", "7D", "JH", "2C", "8H", "TH",
                "4S", "TD", "3S", "7S", "4D", "AC", "4H", "QH",
                "TS", "5C", "4C", "3C", "AH", "AS", "JS", "QD",
                "9D", "KS", "2S", "3H", "KH", "QC", "AD", "5S",
                "9S", "KC", "KD", "5H", "7C", "7H", "5D", "JC",
                "9H", "2D", "JD"
        };
        assertArrayEquals(expected, shuffler.getShuffledDeck());

    }


    private void testDealGame(int seed, String expectedOutput) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Shuffler shuffler = new Shuffler();
        shuffler.dealGame(seed);

        assertEquals(expectedOutput, outputStream.toString());

        // Restore System.out
        System.setOut(System.out); 
    }
}
