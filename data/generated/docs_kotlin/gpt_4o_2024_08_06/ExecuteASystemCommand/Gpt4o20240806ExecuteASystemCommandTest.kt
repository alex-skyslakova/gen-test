import org.junit.Test
import org.junit.Assert.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Locale

class ExecuteASystemCommandTest {

    @Test
    fun testDirCommandOutput() {
        // Redirect System.out to capture the output
        val originalOut = System.out
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        try {
            // Execute the main function to test the command output
            main(arrayOf())

            // Capture the output
            val output = outputStream.toString().trim()

            // Check if the output contains expected directory listing information
            // This is a basic check to see if the output is not empty
            // You might want to adjust this based on your actual directory content
            assertTrue("Output should not be empty", output.isNotEmpty())
            assertTrue("Output should contain directory listing", output.contains("Directory of"))

        } finally {
            // Restore the original System.out
            System.setOut(originalOut)
        }
    }
}
