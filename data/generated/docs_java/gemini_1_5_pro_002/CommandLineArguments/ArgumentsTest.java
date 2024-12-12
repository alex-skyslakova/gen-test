import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArgumentsTest {

    @Test
    void testNoArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Arguments.main(new String[]{});

        String expectedOutput = "There are 0 arguments given.\r\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testSingleArgument() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Arguments.main(new String[]{"hello"});

        String expectedOutput = "There are 1 arguments given.\r\n" +
                "The argument #1 is hello and is at index 0\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testMultipleArguments() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Arguments.main(new String[]{"-c", "alpha beta", "-h", "gamma"});


        String expectedOutput = "There are 4 arguments given.\r\n" +
                "The argument #1 is -c and is at index 0\r\n" +
                "The argument #2 is alpha beta and is at index 1\r\n" +
                "The argument #3 is -h and is at index 2\r\n" +
                "The argument #4 is gamma and is at index 3\r\n";

        assertEquals(expectedOutput, outContent.toString());

    }


    @Test
    void testArgumentsWithSpaces() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Arguments.main(new String[]{"arg1 with spaces", "arg2"});

        String expectedOutput = "There are 2 arguments given.\r\n" +
                "The argument #1 is arg1 with spaces and is at index 0\r\n" +
                "The argument #2 is arg2 and is at index 1\r\n";


        assertEquals(expectedOutput, outContent.toString());
    }

}
