import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class NewClassTest {

    @Test
    void testSecondMethodIsCalled() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Create an instance of NewClass, which should trigger the call to second()
        new NewClass();

        // Restore the original System.out
        System.setOut(originalOut);

        // Check if the output contains "Second"
        assertEquals("Second\n", outputStream.toString());
    }

    @Test
    void testFirstMethodCallsCallback() {
        // Create a mock callback
        AnEventOrCallback mockCallback = new AnEventOrCallback() {
            boolean wasCalled = false;

            @Override
            public void call() {
                wasCalled = true;
            }
        };

        // Create an instance of NewClass
        NewClass newClass = new NewClass();

        // Call the first method with the mock callback
        newClass.first(mockCallback);

        // Check if the callback's call method was invoked
        assertTrue(((AnEventOrCallback) mockCallback).wasCalled);
    }
}
