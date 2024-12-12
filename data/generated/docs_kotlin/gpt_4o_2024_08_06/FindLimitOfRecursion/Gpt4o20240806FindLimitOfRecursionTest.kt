import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertTrue
import org.junit.Test

class FindLimitOfRecursionTest {

    @Test
    fun testRecursionLimit() {
        // Redirect standard output to capture the print statements
        val outputStream = ByteArrayOutputStream()
        val originalOut = System.out
        System.setOut(PrintStream(outputStream))

        try {
            // Call the main function which starts the recursion
            main(arrayOf())

            // Capture the output
            val output = outputStream.toString().trim()

            // Check if the output contains the expected message
            assertTrue(output.startsWith("Limit of recursion is"), "Output did not start with expected message")
            
            // Optionally, you can check if the number is reasonable (e.g., greater than 0)
            val limit = output.split(" ").last().toIntOrNull()
            assertTrue(limit != null && limit > 0, "Recursion limit should be a positive integer")
        } finally {
            // Restore the original standard output
            System.setOut(originalOut)
        }
    }
}
