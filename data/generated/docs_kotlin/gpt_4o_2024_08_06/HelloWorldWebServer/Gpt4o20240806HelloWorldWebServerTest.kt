import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.Socket
import kotlin.concurrent.thread

class HelloWorldWebServerTest {

    companion object {
        private lateinit var serverThread: Thread

        @BeforeAll
        @JvmStatic
        fun startServer() {
            serverThread = thread {
                main(emptyArray())
            }
            // Give the server some time to start
            Thread.sleep(1000)
        }

        @AfterAll
        @JvmStatic
        fun stopServer() {
            serverThread.interrupt()
        }
    }

    @Test
    fun `test server response`() {
        Socket("localhost", 8080).use { socket ->
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val response = reader.readLine()
            assertEquals("Goodbye, World!", response)
        }
    }
}
