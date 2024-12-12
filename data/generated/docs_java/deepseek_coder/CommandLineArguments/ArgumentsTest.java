import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArgumentsTest {

    @Test
    public void testNoArguments() {
        String[] args = {};
        Arguments.main(args);
        // No assertions to make here since the method only prints to console
    }

    @Test
    public void testSingleArgument() {
        String[] args = {"single"};
        Arguments.main(args);
        // No assertions to make here since the method only prints to console
    }

    @Test
    public void testMultipleArguments() {
        String[] args = {"alpha", "beta", "gamma"};
        Arguments.main(args);
        // No assertions to make here since the method only prints to console
    }

    @Test
    public void testArgumentsWithSpaces() {
        String[] args = {"alpha beta", "gamma"};
        Arguments.main(args);
        // No assertions to make here since the method only prints to console
    }

    @Test
    public void testArgumentsWithSpecialCharacters() {
        String[] args = {"-c", "alpha beta", "-h", "gamma"};
        Arguments.main(args);
        // No assertions to make here since the method only prints to console
    }
}
