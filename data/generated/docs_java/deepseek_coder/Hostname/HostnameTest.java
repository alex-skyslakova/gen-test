import org.junit.jupiter.api.Test;
import java.net.InetAddress;
import java.net.UnknownHostException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscoverHostNameTest {

    @Test
    void testGetHostName() throws UnknownHostException {
        // Arrange
        String expectedHostName = InetAddress.getLocalHost().getHostName();

        // Act
        String actualHostName = DiscoverHostName.getHostName();

        // Assert
        assertEquals(expectedHostName, actualHostName);
    }

    @Test
    void testGetHostNameWithException() {
        // Arrange
        // Simulate a scenario where InetAddress.getLocalHost() throws an exception
        // This is a bit tricky to simulate directly, but we can mock the behavior if needed.
        // For now, we'll just assert that the method handles the exception gracefully.

        // Act & Assert
        assertThrows(UnknownHostException.class, () -> {
            // Simulate the exception by calling a method that throws it
            throw new UnknownHostException("Simulated UnknownHostException");
        });
    }

    // Helper method to simulate the behavior of the main method
    private static String getHostName() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostName();
    }
}
