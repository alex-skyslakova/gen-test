import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.net.InetAddress
import java.net.UnknownHostException
import io.mockk.*

class HostnameTest {

    @Test
    fun testGetHostnameSuccess() {
        val mockInetAddress = mockk<InetAddress>()
        every { mockInetAddress.hostName } returns "test-hostname"
        mockkStatic(InetAddress::class)
        every { InetAddress.getLocalHost() } returns mockInetAddress

        val expectedHostname = "test-hostname"

        val byteArrayOutputStream = ByteArrayOutputStream()
        val printStream = PrintStream(byteArrayOutputStream)
        System.setOut(printStream)


        main(arrayOf())

        val actualOutput = byteArrayOutputStream.toString().trim()
        assertEquals(expectedHostname, actualOutput)

        System.setOut(System.out) // Restore original System.out
        unmockkStatic(InetAddress::class)
    }


    @Test
    fun testGetHostnameUnknownHostException() {
        mockkStatic(InetAddress::class)
        every { InetAddress.getLocalHost() } throws UnknownHostException("Test Exception")


        val byteArrayOutputStream = ByteArrayOutputStream()
        val printStream = PrintStream(byteArrayOutputStream)
        System.setErr(printStream) // Redirect stderr for exception capture

        main(arrayOf())

        val actualOutput = byteArrayOutputStream.toString().trim()
        // We can't reliably assert the entire stack trace, but ensure at least the exception message is logged.
        assertTrue(actualOutput.contains("Test Exception"))

        System.setErr(System.err) // Restore original System.err
        unmockkStatic(InetAddress::class)

    }
}


import java.io.ByteArrayOutputStream
import java.io.PrintStream
