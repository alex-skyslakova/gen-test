import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class HelloWorldWebServerTest {

    private lateinit var serverThread: Thread

    @BeforeEach
    fun setUp() {
        // Start the server in a separate thread
        serverThread = Thread {
            HelloWorldWebServer.main(arrayOf())
        }
        serverThread.start()
    }

    @AfterEach
    fun tearDown() {
        // Interrupt the server thread to stop it
        serverThread.interrupt()
    }

    @Test
    fun testServerRespondsCorrectly() {
        val client = Socket("localhost", 8080)
        val input = BufferedReader(InputStreamReader(client.getInputStream()))
        val response = input.readLine()
        client.close()

        assertEquals("Goodbye, World!", response)
    }

    @Test
    fun testServerHandlesMultipleConnections() {
        val client1 = Socket("localhost", 8080)
        val client2 = Socket("localhost", 8080)

        val input1 = BufferedReader(InputStreamReader(client1.getInputStream()))
        val input2 = BufferedReader(InputStreamReader(client2.getInputStream()))

        val response1 = input1.readLine()
        val response2 = input2.readLine()

        client1.close()
        client2.close()

        assertEquals("Goodbye, World!", response1)
        assertEquals("Goodbye, World!", response2)
    }

    @Test
    fun testServerClosesConnectionAfterResponse() {
        val client = Socket("localhost", 8080)
        val input = BufferedReader(InputStreamReader(client.getInputStream()))
        val response = input.readLine()

        // Attempt to read again after the first response
        assertFailsWith<Exception> {
            input.readLine()
        }

        client.close()

        assertEquals("Goodbye, World!", response)
    }
}
