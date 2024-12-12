import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ArgumentsTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testNoArguments() {
        String[] args = {};
        Arguments.main(args);
        assertEquals("There are 0 arguments given.\n", outContent.toString());
    }

    @Test
    public void testSingleArgument() {
        String[] args = {"-c"};
        Arguments.main(args);
        String expectedOutput = "There are 1 arguments given.\n" +
                                "The argument #1 is -c and is at index 0\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMultipleArguments() {
        String[] args = {"-c", "alpha beta", "-h", "gamma"};
        Arguments.main(args);
        String expectedOutput = "There are 4 arguments given.\n" +
                                "The argument #1 is -c and is at index 0\n" +
                                "The argument #2 is alpha beta and is at index 1\n" +
                                "The argument #3 is -h and is at index 2\n" +
                                "The argument #4 is gamma and is at index 3\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testArgumentsWithSpaces() {
        String[] args = {"\"alpha beta\"", "\"gamma\""};
        Arguments.main(args);
        String expectedOutput = "There are 2 arguments given.\n" +
                                "The argument #1 is \"alpha beta\" and is at index 0\n" +
                                "The argument #2 is \"gamma\" and is at index 1\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
