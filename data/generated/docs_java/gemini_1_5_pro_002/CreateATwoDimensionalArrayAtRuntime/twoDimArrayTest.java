import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class twoDimArrayTest {

    @Test
    void testMain_validInput() {
        String input = "2\n3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        twoDimArray.main(new String[]{});

        String expectedOutput = "The number at place [0 0] is 42.0\r\n"; // Adjust line ending if needed
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    void testMain_zeroInput() {
        String input = "0\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        twoDimArray.main(new String[]{});

       String expectedOutput = "The number at place [0 0] is 42.0\r\n"; //  Adjust line ending if needed. Note: This will throw ArrayIndexOutOfBoundsException if we try to access array[0][0] when dimensions are 0. The original code doesn't handle this. This test demonstrates that flaw.  If the program were modified to handle this, we'd adjust this test to expect different output.
       assertThrows(ArrayIndexOutOfBoundsException.class, () ->  twoDimArray.main(new String[]{})); // Expecting exception due to accessing [0][0] in a zero-sized array
    }



    @Test
    void testMain_negativeInput() {
        String input = "-2\n3\n"; // Negative input
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThrows(NegativeArraySizeException.class, () -> twoDimArray.main(new String[]{}));

         input = "2\n-3\n"; // Negative input
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertThrows(NegativeArraySizeException.class, () -> twoDimArray.main(new String[]{}));

    }

}
