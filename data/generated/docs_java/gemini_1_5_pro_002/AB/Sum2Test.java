import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class Sum2Test {

    @Test
    void testPositiveNumbers() {
        String input = "2 2";
        String expectedOutput = "4\n";
        testSum2(input, expectedOutput);
    }

    @Test
    void testNegativeNumbers() {
        String input = "-5 -3";
        String expectedOutput = "-8\n";
        testSum2(input, expectedOutput);
    }

    @Test
    void testPositiveAndNegativeNumbers() {
        String input = "7 -2";
        String expectedOutput = "5\n";
        testSum2(input, expectedOutput);
    }

    @Test
    void testZeroes() {
        String input = "0 0";
        String expectedOutput = "0\n";
        testSum2(input, expectedOutput);

    }

    @Test
    void testBoundaryValues() {
        String input = "1000 -1000";
        String expectedOutput = "0\n";
        testSum2(input, expectedOutput);


        input = "-1000 1000";
        expectedOutput = "0\n";
        testSum2(input, expectedOutput);
    }



    private void testSum2(String input, String expectedOutput) {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        try {
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Sum2.main(new String[]{});

            assertEquals(expectedOutput, outContent.toString());

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
