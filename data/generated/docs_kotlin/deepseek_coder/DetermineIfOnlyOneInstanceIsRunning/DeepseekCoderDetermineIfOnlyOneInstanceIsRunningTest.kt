import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import java.io.IOException
import java.net.ServerSocket
import java.net.InetAddress

class SingleInstanceTest {

    private var mockServerSocket: ServerSocket? = null

    @BeforeEach
    fun setUp() {
        // Create a mock ServerSocket to simulate an already running instance
        try {
            mockServerSocket = ServerSocket(65000, 10, InetAddress.getLocalHost())
        } catch (e: IOException) {
            fail("Failed to set up mock ServerSocket for testing")
        }
    }

    @AfterEach
    fun tearDown() {
        // Close the mock ServerSocket after each test
        mockServerSocket?.close()
    }

    @Test
    fun testAlreadyRunning() {
        // Given an already running instance (mocked by ServerSocket)
        // When checking if already running
        val result = SingleInstance.alreadyRunning()
        // Then it should return true
        assertTrue(result)
    }

    @Test
    fun testNotAlreadyRunning() {
        // Given no other instance running (close the mock ServerSocket)
        mockServerSocket?.close()
        // When checking if already running
        val result = SingleInstance.alreadyRunning()
        // Then it should return false
        assertFalse(result)
    }

    @Test
    fun testCloseServerSocket() {
        // Given a ServerSocket is created
        SingleInstance.alreadyRunning()
        // When closing the ServerSocket
        SingleInstance.close()
        // Then the ServerSocket should be closed
        assertTrue(SingleInstance.ss?.isClosed() ?: false)
    }

    @Test
    fun testCloseAlreadyClosedServerSocket() {
        // Given a ServerSocket is created and then closed
        SingleInstance.alreadyRunning()
        SingleInstance.close()
        // When trying to close it again
        SingleInstance.close()
        // Then it should not throw an exception or fail
        // This test is more about ensuring no exception is thrown
        assertDoesNotThrow { SingleInstance.close() }
    }

    @Test
    fun testCloseNullServerSocket() {
        // Given no ServerSocket is created
        // When trying to close it
        SingleInstance.close()
        // Then it should not throw an exception or fail
        // This test is more about ensuring no exception is thrown
        assertDoesNotThrow { SingleInstance.close() }
    }
}
