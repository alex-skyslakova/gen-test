import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mockito
import java.net.InetAddress

class HostnameTest {

    @Test
    fun `test hostname retrieval`() {
        // Mock InetAddress
        val mockInetAddress = Mockito.mock(InetAddress::class.java)
        Mockito.`when`(mockInetAddress.hostName).thenReturn("mock-hostname")

        // Mock InetAddress.getLocalHost() to return the mocked InetAddress
        Mockito.mockStatic(InetAddress::class.java).use { mockedStatic ->
            mockedStatic.`when`<InetAddress> { InetAddress.getLocalHost() }.thenReturn(mockInetAddress)

            // Call the method under test
            val hostname = InetAddress.getLocalHost().hostName

            // Verify the result
            assertEquals("mock-hostname", hostname)
        }
    }
}
