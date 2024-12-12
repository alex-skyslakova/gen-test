import org.junit.Test
import java.util.concurrent.CyclicBarrier
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class DelayedMessagePrinterTest {

    @Test
    fun testDelayedMessagePrinter() {
        val msgs = listOf("Enjoy", "Rosetta", "Code")
        val barrier = CyclicBarrier(msgs.size)
        val latch = CountDownLatch(msgs.size)
        val printedMessages = mutableListOf<String>()

        for (msg in msgs) {
            Thread {
                try {
                    barrier.await()
                    printedMessages.add(msg)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    latch.countDown()
                }
            }.start()
        }

        assertTrue(latch.await(5, TimeUnit.SECONDS))
        assertEquals(msgs.size, printedMessages.size)
        assertTrue(printedMessages.containsAll(msgs))
    }
}
