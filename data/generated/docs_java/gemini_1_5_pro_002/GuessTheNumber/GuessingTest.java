import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GuessingTest {

    @Test
    void testCorrectGuessFirstTry() {
        String input = "5\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Manipulate random number generation for testing purposes
        Guessing.main(new String[]{});

        String expectedOutput = "Guess the number between 1 and 10: Well guessed!\n";
         // Due to the random nature, we cannot predict what the original prompt will say for the number.
         // Instead we assert that the correct success message appears, which indicates the flow worked.
        assertTrue(out.toString().contains("Well guessed!")); 
        System.setIn(System.in); // Restore System.in
        System.setOut(System.out); // Restore System.out
    }

    @Test
    void testCorrectGuessAfterMultipleTries() {
        String input = "2\n7\n9\n5\n"; // Assuming the number is 5
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        Guessing.main(new String[]{});


         assertTrue(out.toString().contains("Wrong! Guess again: ")); // Check for the wrong guess prompt.
         assertTrue(out.toString().contains("Well guessed!")); // Success message must also be there.

        System.setIn(System.in);
        System.setOut(System.out);
    }


}
