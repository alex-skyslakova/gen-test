import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.TimeUnit


class ConcurrentComputingTest {

    @Test
    fun testConcurrentOutput() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val msgs = listOf("Enjoy", "Rosetta", "Code")
        val barrier = CyclicBarrier(msgs.size)
        val threads = msgs.map { Thread(DelayedMessagePrinter(barrier, it)) }
        threads.forEach { it.start() }

        // Wait for threads to finish (adjust timeout if needed)
        threads.forEach { it.join(1000) }


        val output = outputStream.toString().trim().split("\n")
        assertEquals(msgs.size, output.size)
        assertTrue(output.containsAll(msgs))

        // Restore System.out
        System.setOut(System.out)
    }


    @Test
    fun testDelayedMessagePrinter() {
        val barrier = CyclicBarrier(1)
        val msg = "Test Message"
        val printer = DelayedMessagePrinter(barrier, msg)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))


        val thread = Thread(printer)
        thread.start()
        thread.join(1000)

        barrier.await(1, TimeUnit.SECONDS)

        val output = outputStream.toString().trim()
        assertEquals(msg, output)

        System.setOut(System.out)


    }

    @Test
    fun testBarrierTimeout(){

        val barrier = CyclicBarrier(2)
        val msg = "Test Message"
        val printer = DelayedMessagePrinter(barrier, msg)
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val thread = Thread(printer)
        thread.start()

        assertThrows(java.util.concurrent.TimeoutException::class.java) {
           barrier.await(1,TimeUnit.SECONDS)
        }
        System.setOut(System.out)

    }
}
