import org.junit.Test;

public class AssertionsTest {

    @Test(expected = AssertionError.class)
    public void testAssertionWithoutMessage() {
        int a = 13;
        assert a == 42;
    }

    @Test(expected = AssertionError.class)
    public void testAssertionWithMessage() {
        int a = 13;
        assert a == 42 : "Error message";
    }

    @Test
    public void testAssertionWithCorrectValue() {
        int a = 42;
        assert a == 42;
    }

    @Test
    public void testAssertionWithCorrectValueAndMessage() {
        int a = 42;
        assert a == 42 : "Error message";
    }
}
