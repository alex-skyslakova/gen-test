import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputLoopTest {

    @Test
    public void testWordByWordReading() {
        String inputData = "Hello world this is a test";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        Scanner in = new Scanner(inputStream);

        StringBuilder result = new StringBuilder();
        while (in.hasNext()) {
            String input = in.next();
            result.append(input).append(" ");
        }

        assertEquals("Hello world this is a test ", result.toString());
    }

    @Test
    public void testLineByLineReading() {
        String inputData = "Hello world\nthis is a test";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        Scanner in = new Scanner(inputStream);

        StringBuilder result = new StringBuilder();
        while (in.hasNextLine()) {
            String input = in.nextLine();
            result.append(input).append("\n");
        }

        assertEquals("Hello world\nthis is a test\n", result.toString());
    }

    @Test
    public void testEmptyStream() {
        String inputData = "";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        Scanner in = new Scanner(inputStream);

        StringBuilder result = new StringBuilder();
        while (in.hasNext()) {
            String input = in.next();
            result.append(input).append(" ");
        }

        assertEquals("", result.toString());
    }

    @Test
    public void testSingleWord() {
        String inputData = "Hello";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        Scanner in = new Scanner(inputStream);

        StringBuilder result = new StringBuilder();
        while (in.hasNext()) {
            String input = in.next();
            result.append(input).append(" ");
        }

        assertEquals("Hello ", result.toString());
    }

    @Test
    public void testSingleLine() {
        String inputData = "Hello world";
        InputStream inputStream = new ByteArrayInputStream(inputData.getBytes());
        Scanner in = new Scanner(inputStream);

        StringBuilder result = new StringBuilder();
        while (in.hasNextLine()) {
            String input = in.nextLine();
            result.append(input).append("\n");
        }

        assertEquals("Hello world\n", result.toString());
    }
}
