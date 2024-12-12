import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.*;

class SingletonAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testSingleInstanceRunning() throws Exception {
        // Start the first instance
        SingletonApp.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("OK, only this instance is running"));

        // Reset the output stream
        outContent.reset();

        // Attempt to start a second instance
        assertThrows(IOException.class, () -> {
            SingletonApp.main(new String[]{});
        });

        // Check the output for the second instance
        output = outContent.toString();
        assertTrue(output.contains("Application is already running"));
    }

    @Test
    void testNoInstanceRunning() throws Exception {
        // Ensure no instance is running by closing the ServerSocket
        if (SingletonApp.s != null && !SingletonApp.s.isClosed()) {
            SingletonApp.s.close();
        }

        // Start a new instance
        SingletonApp.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("OK, only this instance is running"));
    }

    @Test
    void testServerSocketInitialization() {
        assertNotNull(SingletonApp.s);
        assertFalse(SingletonApp.s.isClosed());
    }

    @Test
    void testServerSocketClose() throws Exception {
        // Ensure no instance is running by closing the ServerSocket
        if (SingletonApp.s != null && !SingletonApp.s.isClosed()) {
            SingletonApp.s.close();
        }

        // Attempt to start a new instance
        SingletonApp.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains("OK, only this instance is running"));

        // Check if the ServerSocket is closed after the instance terminates
        assertTrue(SingletonApp.s.isClosed());
    }
}
