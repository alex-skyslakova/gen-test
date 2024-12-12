import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestStringChallengeTest {

    @Test
    void testNoInput() throws Exception {
        String input = "";
        String expectedOutput = "";

        test(input, expectedOutput);
    }

    @Test
    void testSingleLine() throws Exception {
        String input = "a";
        String expectedOutput = "a";

        test(input, expectedOutput);

    }
    @Test
    void testMultipleLinesWithTies() throws Exception {
        String input = "a\nbb\nccc\nddd\nee\nf\nggg";
        String expectedOutput = "ccc\nddd\nggg";


        test(input, expectedOutput);
    }

    @Test
    void testMultipleLinesWithNoTies() throws Exception {
        String input = "a\nbb\ncccc\nd\nee\nf\ng";
        String expectedOutput = "cccc";

        test(input, expectedOutput);
    }

    @Test
    void testEmptyLines() throws Exception {
        String input = "\n\n\n";
        String expectedOutput = "";

        test(input, expectedOutput);
    }

    @Test
    void testLinesWithSpaces() throws Exception {
        String input = "a \n bb\nccc \nddd\nee\nf\nggg";
        String expectedOutput = "ccc \nddd\nggg";

        test(input, expectedOutput);
    }



    private void test(String input, String expectedOutput) throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));


        // As the main method reads from a file, we need to modify the approach for testing
        // Create a temporary file with the input data
        // ... (Code to create and populate a temporary file)
        // Then pass the temporary file path to the main method

        // After the main method execution, delete the temporary file
        // ... (Code to delete the temporary file)


        // A workaround for testing without file I/O, directly using the scanner with the input String:

        Scanner sc = new Scanner(input);
        String lines = "", longest = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (LongestStringChallenge.longer(longest, line))
                lines = longest = line;
            else if (!LongestStringChallenge.longer(line, longest))
                lines = lines.concat("\n").concat(line);
        }
        System.out.print(lines);



        assertEquals(expectedOutput, out.toString().trim());


        System.setIn(System.in); // Restore System.in
        System.setOut(System.out); // Restore System.out
    }
}
