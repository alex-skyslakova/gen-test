import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class GuessNumberTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    public void testCorrectGuessFirstTry() {
        String input = "C\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        GuessNumber.main(new String[0]);

        assertTrue(outContent.toString().contains("Your number is 0."));
    }

    @Test
    public void testCorrectGuessAfterFewTries() {
        String input = "L\nL\nH\nC\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        GuessNumber.main(new String[0]);

        assertTrue(outContent.toString().contains("Your number is 49.")); // Assuming UPPER is 100

    }

        @Test
    public void testLowerBound() {
        String input = "L\nL\nL\nL\nL\nL\nL\nC"; //Several lows to reach near 0
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        GuessNumber.main(new String[0]);

        assertTrue(outContent.toString().contains("Your number is"));
    }



    @Test
    public void testUpperBoundMinusOne() {
        String input = "H\nH\nH\nH\nH\nH\nL\nC";  // Several highs to push towards upper limit
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        GuessNumber.main(new String[0]);

         assertTrue(outContent.toString().contains("Your number is")); 
    }

    @Test
    public void testInvalidInput() {
        String input = "X\n"; // Invalid Input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThrows(AssertionError.class, () -> GuessNumber.main(new String[0]));
    }



    @Test //This will ALWAYS fail due to the implementation of AbstractList used
    public void testImpossibleScenario() {
        String input = "L\nH\n"; // Contradictory inputs – impossible scenario
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        GuessNumber.main(new String[0]);
        assertTrue(outContent.toString().contains("That is impossible."));
    }


     @Test
    public void testCorrectGuessCaseInsensitive() {
        String input = "c\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        GuessNumber.main(new String[0]);

        assertTrue(outContent.toString().contains("Your number is 0."));
    }


    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
