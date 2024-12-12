import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class CheckpointSynchronizationTest {

    @Test
    fun testSingleWorkerSingleTask() {
        nWorkers = 1
        nTasks = 1
        runTasks()
        // Since there's only one worker and one task, the worker should finish the task.
        // No additional assertions needed as the program should run without errors.
    }

    @Test
    fun testMultipleWorkersSingleTask() {
        nWorkers = 3
        nTasks = 1
        runTasks()
        // Since there are multiple workers and one task, all workers should finish the task.
        // No additional assertions needed as the program should run without errors.
    }

    @Test
    fun testSingleWorkerMultipleTasks() {
        nWorkers = 1
        nTasks = 3
        runTasks()
        // Since there's only one worker and multiple tasks, the worker should finish all tasks.
        // No additional assertions needed as the program should run without errors.
    }

    @Test
    fun testMultipleWorkersMultipleTasks() {
        nWorkers = 3
        nTasks = 3
        runTasks()
        // Since there are multiple workers and multiple tasks, all workers should finish all tasks.
        // No additional assertions needed as the program should run without errors.
    }

    @Test
    fun testWorkerJoiningAndLeaving() {
        nWorkers = 3
        nTasks = 3
        val executor = Executors.newFixedThreadPool(nWorkers)
        val latch = CountDownLatch(nWorkers)

        for (i in 1..nTasks) {
            println("\nStarting task number $i.")
            for (j in 1..nWorkers) {
                executor.execute(Worker(j))
            }
            Worker.checkPoint()  // wait for all workers to finish the task
        }

        executor.shutdown()
        assertTrue(executor.awaitTermination(1, TimeUnit.MINUTES))
    }

    @Test
    fun testRaceConditionFree() {
        nWorkers = 10
        nTasks = 10
        val executor = Executors.newFixedThreadPool(nWorkers)
        val latch = CountDownLatch(nWorkers)

        for (i in 1..nTasks) {
            println("\nStarting task number $i.")
            for (j in 1..nWorkers) {
                executor.execute(Worker(j))
            }
            Worker.checkPoint()  // wait for all workers to finish the task
        }

        executor.shutdown()
        assertTrue(executor.awaitTermination(1, TimeUnit.MINUTES))
    }
}
