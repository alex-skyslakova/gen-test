import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CommandLineArgumentsTest {

    @Test
    fun `test no arguments`() {
        val args = arrayOf<String>()
        val output = captureOutput { main(args) }
        assertTrue(output.contains("There are 0 arguments given."))
    }

    @Test
    fun `test single argument`() {
        val args = arrayOf("-c")
        val output = captureOutput { main(args) }
        assertTrue(output.contains("There are 1 arguments given."))
        assertTrue(output.contains("The argument #1 is -c and is at index 0"))
    }

    @Test
    fun `test multiple arguments`() {
        val args = arrayOf("-c", "alpha beta", "-h", "gamma")
        val output = captureOutput { main(args) }
        assertTrue(output.contains("There are 4 arguments given."))
        assertTrue(output.contains("The argument #1 is -c and is at index 0"))
        assertTrue(output.contains("The argument #2 is alpha beta and is at index 1"))
        assertTrue(output.contains("The argument #3 is -h and is at index 2"))
        assertTrue(output.contains("The argument #4 is gamma and is at index 3"))
    }

    // Helper function to capture the output of the main function
    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }
        return outputStream.toString().trim()
    }
}
