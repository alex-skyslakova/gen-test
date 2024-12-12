import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CheckpointSynchronizationTest {

    @Test
    fun testSingleWorkerSingleTask() {
        val input = "1\n1\n"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim().split("\n")
        assertEquals(3, output.size) // 3 lines: prompts + task start + worker ready
        assert(output[2].contains("Worker 1 is ready"))
    }

    @Test
    fun testMultipleWorkersSingleTask() {
        val input = "3\n1\n"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim().split("\n")
        assertEquals(5, output.size) // 5 lines: prompts + task start + 3 workers ready
        assert(output[2].contains("Starting task number 1."))
        assert(output[3].contains("Worker 1 is ready"))
        assert(output[4].contains("Worker 2 is ready") || output[4].contains("Worker 3 is ready"))
    }

    @Test
    fun testMultipleWorkersMultipleTasks() {
        val input = "2\n2\n"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim().split("\n")
        assertEquals(8, output.size) // 8 lines: prompts + 2 tasks start + 4 workers ready
        assert(output[2].contains("Starting task number 1."))
        assert(output[3].contains("Worker 1 is ready"))
        assert(output[4].contains("Worker 2 is ready"))
        assert(output[5].contains("Starting task number 2."))
        assert(output[6].contains("Worker 1 is ready"))
        assert(output[7].contains("Worker 2 is ready"))
    }

    @Test
    fun testNoWorkers() {
        val input = "0\n1\n"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim().split("\n")
        assertEquals(3, output.size) // 3 lines: prompts + task start
        assert(output[2].contains("Starting task number 1."))
    }

    @Test
    fun testNoTasks() {
        val input = "1\n0\n"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim().split("\n")
        assertEquals(2, output.size) // 2 lines: prompts
    }
}
