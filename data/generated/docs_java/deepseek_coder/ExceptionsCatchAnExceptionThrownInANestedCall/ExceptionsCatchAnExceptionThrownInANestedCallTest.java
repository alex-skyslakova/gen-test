import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ExceptionsTest {

    @Test
    void testFooCatchesU0() {
        // Arrange
        final String expectedOutput = "Function foo caught exception U0\n";
        final StringBuffer actualOutput = new StringBuffer();

        // Act
        try {
            System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
                public void write(int b) {
                    actualOutput.append((char) b);
                }
            }));
            ExceptionsTest.foo();
        } finally {
            System.setOut(System.out);
        }

        // Assert
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test
    void testFooDoesNotCatchU1() {
        // Arrange
        boolean exceptionCaught = false;

        // Act
        try {
            ExceptionsTest.foo();
        } catch (U1 e) {
            exceptionCaught = true;
        }

        // Assert
        assertTrue(exceptionCaught, "Expected U1 to be thrown and not caught by foo");
    }

    @Test
    void testBarThrowsU0() {
        // Arrange
        boolean exceptionCaught = false;

        // Act
        try {
            ExceptionsTest.bar(0);
        } catch (U0 e) {
            exceptionCaught = true;
        }

        // Assert
        assertTrue(exceptionCaught, "Expected U0 to be thrown by bar");
    }

    @Test
    void testBarThrowsU1() {
        // Arrange
        boolean exceptionCaught = false;

        // Act
        try {
            ExceptionsTest.bar(1);
        } catch (U1 e) {
            exceptionCaught = true;
        }

        // Assert
        assertTrue(exceptionCaught, "Expected U1 to be thrown by bar");
    }

    @Test
    void testBazThrowsU0() {
        // Arrange
        boolean exceptionCaught = false;

        // Act
        try {
            ExceptionsTest.baz(0);
        } catch (U0 e) {
            exceptionCaught = true;
        }

        // Assert
        assertTrue(exceptionCaught, "Expected U0 to be thrown by baz");
    }

    @Test
    void testBazThrowsU1() {
        // Arrange
        boolean exceptionCaught = false;

        // Act
        try {
            ExceptionsTest.baz(1);
        } catch (U1 e) {
            exceptionCaught = true;
        }

        // Assert
        assertTrue(exceptionCaught, "Expected U1 to be thrown by baz");
    }
}
