import org.junit.Test;
import static org.junit.Assert.*;

public class ExceptionsTestTest {

    @Test
    public void testFooCatchesU0() {
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
        } catch (U1 e) {
            // Expected to catch U1 here
        } finally {
            System.setOut(System.out);
        }

        // Assert
        assertEquals(expectedOutput, actualOutput.toString());
    }

    @Test(expected = U1.class)
    public void testFooDoesNotCatchU1() throws U1 {
        // Act
        ExceptionsTest.foo();
    }
}
