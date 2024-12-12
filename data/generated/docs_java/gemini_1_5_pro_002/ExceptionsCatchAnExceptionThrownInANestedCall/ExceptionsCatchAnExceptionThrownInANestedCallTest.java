import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionsTestTest {

    @Test
    void testFooCatchesU0() throws U1 {
        // No assertion needed, checking for successful execution without U1 being thrown from foo
        ExceptionsTest.foo(); 
    }

    @Test
    void testBarThrowsU0() {
        assertThrows(U0.class, () -> ExceptionsTest.bar(0));
    }

    @Test
    void testBarThrowsU1() {
        assertThrows(U1.class, () -> ExceptionsTest.bar(1));
    }

    @Test
    void testBazThrowsU0() {
        assertThrows(U0.class, () -> ExceptionsTest.baz(0));
    }

    @Test
    void testBazThrowsU1() {
        assertThrows(U1.class, () -> ExceptionsTest.baz(1));
    }
}
