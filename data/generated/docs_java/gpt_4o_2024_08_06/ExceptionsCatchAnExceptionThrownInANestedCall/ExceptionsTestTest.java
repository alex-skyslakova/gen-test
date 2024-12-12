import org.junit.Test;
import static org.junit.Assert.*;

public class ExceptionsTestTest {

    @Test
    public void testFooCatchesU0() {
        try {
            ExceptionsTest.foo();
            fail("Expected U1 to be thrown");
        } catch (U1 e) {
            // Expected exception, test passes
        }
    }

    @Test
    public void testBarThrowsU0() {
        try {
            ExceptionsTest.bar(0);
            fail("Expected U0 to be thrown");
        } catch (U0 e) {
            // Expected exception, test passes
        } catch (U1 e) {
            fail("Did not expect U1 to be thrown");
        }
    }

    @Test
    public void testBarThrowsU1() {
        try {
            ExceptionsTest.bar(1);
            fail("Expected U1 to be thrown");
        } catch (U0 e) {
            fail("Did not expect U0 to be thrown");
        } catch (U1 e) {
            // Expected exception, test passes
        }
    }

    @Test
    public void testBazThrowsU0() {
        try {
            ExceptionsTest.baz(0);
            fail("Expected U0 to be thrown");
        } catch (U0 e) {
            // Expected exception, test passes
        } catch (U1 e) {
            fail("Did not expect U1 to be thrown");
        }
    }

    @Test
    public void testBazThrowsU1() {
        try {
            ExceptionsTest.baz(1);
            fail("Expected U1 to be thrown");
        } catch (U0 e) {
            fail("Did not expect U0 to be thrown");
        } catch (U1 e) {
            // Expected exception, test passes
        }
    }
}
