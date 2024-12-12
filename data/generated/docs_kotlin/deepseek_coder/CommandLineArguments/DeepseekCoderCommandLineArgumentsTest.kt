import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CommandLineArgumentsTest {

    @Test
    fun testNoArguments() {
        val args = arrayOf<String>()
        val output = captureOutput { main(args) }
        assertEquals("There are 0 arguments given.\n", output)
    }

    @Test
    fun testSingleArgument() {
        val args = arrayOf("single")
        val output = captureOutput { main(args) }
        assertEquals("There are 1 arguments given.\nThe argument #1 is single and is at index 0\n", output)
    }

    @Test
    fun testMultipleArguments() {
        val args = arrayOf("alpha", "beta", "gamma")
        val output = captureOutput { main(args) }
        assertEquals("There are 3 arguments given.\nThe argument #1 is alpha and is at index 0\nThe argument #2 is beta and is at index 1\nThe argument #3 is gamma and is at index 2\n", output)
    }

    @Test
    fun testArgumentsWithSpaces() {
        val args = arrayOf("alpha beta", "gamma")
        val output = captureOutput { main(args) }
        assertEquals("There are 2 arguments given.\nThe argument #1 is alpha beta and is at index 0\nThe argument #2 is gamma and is at index 1\n", output)
    }

    @Test
    fun testEmptyArgument() {
        val args = arrayOf("")
        val output = captureOutput { main(args) }
        assertEquals("There are 1 arguments given.\nThe argument #1 is  and is at index 0\n", output)
    }

    @Test
    fun testMixedArguments() {
        val args = arrayOf("alpha", "", "gamma delta")
        val output = captureOutput { main(args) }
        assertEquals("There are 3 arguments given.\nThe argument #1 is alpha and is at index 0\nThe argument #2 is  and is at index 1\nThe argument #3 is gamma delta and is at index 2\n", output)
    }

    private fun captureOutput(block: () -> Unit): String {
        val output = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(output))
        block()
        System.setOut(java.io.PrintStream(java.io.FileOutputStream(java.io.FileDescriptor.out)))
        return output.toString()
    }
}
