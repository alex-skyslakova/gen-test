import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShufflerTest {

    @Test
    void testDealGame1() {
        Shuffler shuffler = new Shuffler();
        String expectedOutput = "JD 2D 9H JC 5D 7H 7C 5H \n" +
                                "KD KC 9S 5S AD QC KH 3H \n" +
                                "2S KS 9D QD JS AS AH 3C \n" +
                                "4C 5C TS QH 4H AC 4D 7S \n" +
                                "3S TD 4S TH 8H 2C JH 7D \n" +
                                "6D 8S 8D QS 6C 3D 8C TC \n" +
                                "6S 9C 2H 6H \n";
        assertEquals(expectedOutput, captureOutput(() -> shuffler.dealGame(1)));
    }

    @Test
    void testDealGame617() {
        Shuffler shuffler = new Shuffler();
        String expectedOutput = "7D AD 5C 3S 5S 8C 2D AH \n" +
                                "TD 7S QD AC 6D 8H AS KH \n" +
                                "TH QC 3H 9D 6S 8D 3D TC \n" +
                                "KD 5H 9S 3C 8S 7H 4D JS \n" +
                                "4C QS 9C 9H 7C 6H 2C 2S \n" +
                                "4S TS 2H 5D JC 6C JH QH \n" +
                                "JD KS KC 4H \n";
        assertEquals(expectedOutput, captureOutput(() -> shuffler.dealGame(617)));
    }

    @Test
    void testRandomNumberGeneration() {
        Shuffler shuffler = new Shuffler();
        shuffler.dealGame(1);
        int randomNumber = shuffler.random();
        assertTrue(randomNumber >= 0 && randomNumber <= 32767, "Random number out of range");
    }

    private String captureOutput(Runnable task) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        task.run();
        System.setOut(System.out);
        return out.toString();
    }
}
