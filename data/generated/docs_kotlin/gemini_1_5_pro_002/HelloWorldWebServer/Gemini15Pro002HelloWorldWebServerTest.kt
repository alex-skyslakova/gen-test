import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.net.Socket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.ConnectException
import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.TimeUnit


class HelloWorldWebServerTest {

    private lateinit var serverJob: Job

    @BeforeEach
    fun setUp() {
        serverJob = GlobalScope.launch {
            main(arrayOf()) // Start the server in a coroutine
        }
        // Give the server a moment to start
        TimeUnit.MILLISECONDS.sleep(500) 
    }

    @AfterEach
    fun tearDown() {
        serverJob.cancel()
    }


    @Test
    fun testServerResponse() {
        try {
            val socket = Socket("localhost", 8080)
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
            val response = reader.readLine()
            assertEquals("Goodbye, World!", response)
            socket.close()
        } catch (e: ConnectException) {
            fail("Server not running: ${e.message}")
        }

    }


    @Test
    fun testMultipleConnections() = runBlocking {
            // Launch multiple coroutines to simulate concurrent requests
            val numClients = 5
            val responses = mutableListOf<String?>()
            val jobs = List(numClients) {
                GlobalScope.launch {
                    try {
                         val socket = Socket("localhost", 8080)
                         val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
                         responses.add(reader.readLine())
                         socket.close()
                    } catch (e: ConnectException) {
                        responses.add(null)
                    }
                 }

            }

            jobs.joinAll() // Wait for all coroutines to complete

            // Assert that all clients received the expected response
            assertEquals(numClients, responses.size)
            responses.forEach { response -> assertEquals("Goodbye, World!", response) }

    }
}
