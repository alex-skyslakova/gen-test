import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class SingletonAppTest {

    @Test
    public void testSingleInstanceRunning() {
        try {
            // Attempt to create a ServerSocket on the same port to simulate another instance running
            ServerSocket testSocket = new ServerSocket(65000, 10, InetAddress.getLocalHost());
            ProcessBuilder processBuilder = new ProcessBuilder("java", "SingletonApp");
            Process process = processBuilder.start();

            // Wait for the process to terminate
            int exitCode = process.waitFor();

            // Since the port is already taken, the application should exit with code 0
            assertEquals(0, exitCode);

            // Clean up by closing the test socket
            testSocket.close();
        } catch (IOException | InterruptedException e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }

    @Test
    public void testNoOtherInstanceRunning() {
        try {
            // Ensure no other instance is running by not creating a ServerSocket beforehand
            ProcessBuilder processBuilder = new ProcessBuilder("java", "SingletonApp");
            Process process = processBuilder.start();

            // Wait for the process to terminate
            int exitCode = process.waitFor();

            // The application should run successfully and exit with code 0
            assertEquals(0, exitCode);
        } catch (IOException | InterruptedException e) {
            fail("Exception occurred during test: " + e.getMessage());
        }
    }
}
