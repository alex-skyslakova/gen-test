import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException
import java.net.ServerSocket

class SingleInstanceTest {

    @After
    fun tearDown() {
        // Ensure the server socket is closed after each test
        SingleInstance.close()
    }

    @Test
    fun testAlreadyRunningWhenPortIsFree() {
        // Ensure the port is free before testing
        SingleInstance.close()

        // Test when no other instance is running
        val isAlreadyRunning = SingleInstance.alreadyRunning()
        assertFalse("Expected no other instance to be running", isAlreadyRunning)
    }

    @Test
    fun testAlreadyRunningWhenPortIsOccupied() {
        // Occupy the port to simulate another instance running
        val serverSocket = ServerSocket(65000)

        try {
            // Test when another instance is running
            val isAlreadyRunning = SingleInstance.alreadyRunning()
            assertTrue("Expected another instance to be running", isAlreadyRunning)
        } finally {
            // Close the server socket to free the port
            serverSocket.close()
        }
    }

    @Test
    fun testCloseWhenSocketIsOpen() {
        // Ensure the port is free before testing
        SingleInstance.close()

        // Open the socket
        SingleInstance.alreadyRunning()

        // Close the socket
        SingleInstance.close()

        // Verify that the socket is closed
        assertTrue("Expected the server socket to be closed", SingleInstance.ss?.isClosed ?: true)
    }

    @Test
    fun testCloseWhenSocketIsAlreadyClosed() {
        // Ensure the port is free before testing
        SingleInstance.close()

        // Attempt to close the socket when it's already closed
        SingleInstance.close()

        // Verify that the socket is still closed
        assertTrue("Expected the server socket to be closed", SingleInstance.ss?.isClosed ?: true)
    }
}
