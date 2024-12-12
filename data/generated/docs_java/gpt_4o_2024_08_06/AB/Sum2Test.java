import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Sum2Test {

    @Test
    public void testSumPositiveNumbers() {
        String input = "2 2";
        String expectedOutput = "4";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumDifferentPositiveNumbers() {
        String input = "3 2";
        String expectedOutput = "5";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumNegativeNumbers() {
        String input = "-3 -2";
        String expectedOutput = "-5";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumPositiveAndNegativeNumbers() {
        String input = "-3 2";
        String expectedOutput = "-1";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumZeroAndPositiveNumber() {
        String input = "0 5";
        String expectedOutput = "5";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumZeroAndNegativeNumber() {
        String input = "0 -5";
        String expectedOutput = "-5";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumZeroAndZero() {
        String input = "0 0";
        String expectedOutput = "0";

        assertEquals(expectedOutput, runSum2(input));
    }

    @Test
    public void testSumWithSpaces() {
        String input = "   7    3   ";
        String expectedOutput = "10";

        assertEquals(expectedOutput, runSum2(input));
    }

    private String runSum2(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        Sum2.main(new String[]{});

        return out.toString().trim();
    }
}
