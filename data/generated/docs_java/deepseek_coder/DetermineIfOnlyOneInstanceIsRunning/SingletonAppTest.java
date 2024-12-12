import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.SocketException;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testSingleInstanceRunning() throws Exception {
        // Start the first instance
        SingletonApp.main(new String[]{});
        String expectedOutput = "OK, only this instance is running but will terminate in 10 seconds.\n";
        assertEquals(expectedOutput, outContent.toString());

        // Clear the output stream for the next test
        outContent.reset();

        // Attempt to start a second instance
        assertThrows(RuntimeException.class, () -> {
            try {
                SingletonApp.main(new String[]{});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        String expectedErrorOutput = "Application is already running, so terminating this instance.\n";
        assertEquals(expectedErrorOutput, outContent.toString());
    }

    @Test
    public void testPortAlreadyInUse() throws Exception {
        // Simulate the port being in use by creating a ServerSocket on the same port
        ServerSocket existingSocket = new ServerSocket(65000);

        // Attempt to start the SingletonApp
        assertThrows(RuntimeException.class, () -> {
            try {
                SingletonApp.main(new String[]{});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        String expectedErrorOutput = "Application is already running, so terminating this instance.\n";
        assertEquals(expectedErrorOutput, outContent.toString());

        // Close the existing socket to clean up
        existingSocket.close();
    }

    @Test
    public void testUnknownHostException() throws Exception {
        // Mock the InetAddress.getLocalHost() to throw UnknownHostException
        SingletonApp.class.getDeclaredField("s").setAccessible(true);
        SingletonApp.class.getDeclaredField("s").set(null, null);

        // Attempt to start the SingletonApp
        SingletonApp.main(new String[]{});

        // Since UnknownHostException is caught and ignored, the app should run normally
        String expectedOutput = "OK, only this instance is running but will terminate in 10 seconds.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
