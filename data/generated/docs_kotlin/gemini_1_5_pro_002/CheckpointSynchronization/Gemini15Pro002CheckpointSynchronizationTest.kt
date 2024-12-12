import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals
import kotlin.test.assertTimeout


class WorkerTest {

    private val originalOut = System.out
    private val outputStreamCaptor = java.io.ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(java.io.PrintStream(outputStreamCaptor))
        nWorkers = 0
        nTasks = 0
        Worker.nFinished = 0 // Reset static variable before each test
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
    }

    @Test
    fun testSingleWorkerSingleTask() {
        nWorkers = 1
        nTasks = 1
        runTasks()
        val output = outputStreamCaptor.toString()
        assert(output.contains("Worker 1 is ready"))
    }

    @Test
    fun testMultipleWorkersSingleTask() {
        nWorkers = 3
        nTasks = 1
        runTasks()
        val output = outputStreamCaptor.toString()
        assert(output.contains("Worker 1 is ready"))
        assert(output.contains("Worker 2 is ready"))
        assert(output.contains("Worker 3 is ready"))
    }

    @Test
    fun testMultipleWorkersMultipleTasks() {
        nWorkers = 2
        nTasks = 3
        runTasks()
        val output = outputStreamCaptor.toString()
        val countReady1 = output.split("Worker 1 is ready").size - 1
        val countReady2 = output.split("Worker 2 is ready").size - 1
        assertEquals(3, countReady1)
        assertEquals(3, countReady2)
    }

    @Test
    fun testNoPrematureCompletion() {
        nWorkers = 3
        nTasks = 1

        val latch = CountDownLatch(nWorkers)
        val workerThreads = mutableListOf<Thread>()

         for (j in 1..nWorkers) {
            val worker = object : Worker(j) {
                override fun run() {
                    super.run()
                    latch.countDown()
                }
            }
            val thread = Thread(worker)
            workerThreads.add(thread)
            thread.start()
        }

        latch.await(5, TimeUnit.SECONDS) // Wait for all workers to finish

        assertEquals(0, Worker.nFinished) // Check nFinished is reset after checkpoint

    }

    @Test
    fun testNoDeadlock() {
        nWorkers = 2
        nTasks = 1

        assertTimeout(org.junit.jupiter.api.Assertions.assertTimeoutPreemptively<Unit>(5000) {
            runTasks()
        }) {  }

    }


}


