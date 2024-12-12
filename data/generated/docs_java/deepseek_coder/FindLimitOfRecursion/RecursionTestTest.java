import org.junit.Test;
import static org.junit.Assert.*;

public class RecursionTestTest {

    @Test
    public void testRecurse() {
        // This test will trigger the recursion and catch the StackOverflowError
        try {
            RecursionTest.recurse(0);
            fail("Expected StackOverflowError was not thrown.");
        } catch (StackOverflowError e) {
            // The test passes if StackOverflowError is caught
            // We don't need to assert anything specific about the output
            // since the purpose is to find the recursion limit
        }
    }
}
