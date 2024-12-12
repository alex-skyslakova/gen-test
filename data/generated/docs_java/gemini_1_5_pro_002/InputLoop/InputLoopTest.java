import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.ByteArrayOutputStream;
import static org.junit.jupiter.api.Assertions.*;

public class InputLoopTest {

    @Test
    void testWordByWordInput() {
        String input = "hello world this is a test";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            System.out.println(word);
        }

        String expectedOutput = "hello\nworld\nthis\nis\na\ntest\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out); // Restore original System.out
    }


    @Test
    void testLineByLineInput() {
        String input = "hello world\nthis is a test\nanother line";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = new Scanner(in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
        }

        String expectedOutput = "hello world\nthis is a test\nanother line\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out); // Restore original System.out

    }

    @Test
    void testEmptyInput() {
        String input = "";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            System.out.println(word);
        }

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out); // Restore original System.out
    }

    @Test
    void testOnlyWhitespaceInput() {
        String input = "   \n \t  ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Scanner scanner = new Scanner(in);
        while (scanner.hasNext()) {
            String word = scanner.next();
            System.out.println(word);

        }

       String expectedOutput = ""; // next() should skip whitespace
       assertEquals(expectedOutput, outContent.toString());
       System.setOut(System.out); // Restore original System.out


       outContent = new ByteArrayOutputStream();
       System.setOut(new PrintStream(outContent));
       in = new ByteArrayInputStream(input.getBytes());
       scanner = new Scanner(in);


       while (scanner.hasNextLine()) { // nextLine() should preserve whitespace within a line
           String word = scanner.nextLine();
           System.out.println(word);
       }
        assertEquals("   \n \t  \n", outContent.toString()); //nextLine should preserve whitespace within the line.
        System.setOut(System.out);

    }


}
