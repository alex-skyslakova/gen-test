import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestStringChallengeTest {

    @Test
    public void testBasicCase() throws Exception {
        String input = "a\nbb\nccc\nddd\nee\nf\nggg";
        String expectedOutput = "ccc\nddd\nggg";
        assertEquals(expectedOutput, getOutput(input));
    }

    @Test
    public void testSingleLongestLine() throws Exception {
        String input = "a\nbb\nccc";
        String expectedOutput = "ccc";
        assertEquals(expectedOutput, getOutput(input));
    }

    @Test
    public void testNoInput() throws Exception {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, getOutput(input));
    }

    @Test
    public void testTiesForLongestLine() throws Exception {
        String input = "a\nbb\nccc\nddd\nee\nf\nggg\nhhh";
        String expectedOutput = "ccc\nddd\nggg\nhhh";
        assertEquals(expectedOutput, getOutput(input));
    }

    @Test
    public void testWhitespaceHandling() throws Exception {
        String input = " a \n bb \n  ccc  \n  ddd  \n ee \n f \n ggg ";
        String expectedOutput = "  ccc  \n  ddd  \n ggg ";
        assertEquals(expectedOutput, getOutput(input));
    }

    private String getOutput(String input) throws Exception {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(inputStream);
        String lines = "", longest = "";
        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (LongestStringChallenge.longer(longest, line))
                lines = longest = line;
            else if (!LongestStringChallenge.longer(line, longest))
                lines = lines.concat("\n").concat(line);
        }
        return lines;
    }
}
