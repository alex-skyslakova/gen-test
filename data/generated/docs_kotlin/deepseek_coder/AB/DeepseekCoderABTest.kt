import org.junit.Test
import org.junit.Assert.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ABTest {

    @Test
    fun testValidInput() {
        val input = "2 2\nq\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: Their sum is 4\n\nEnter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    @Test
    fun testInvalidInput() {
        val input = "2 a\nq\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: Invalid input, try again\nEnter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    @Test
    fun testOutOfRangeInput() {
        val input = "1001 1\nq\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: Both numbers must be in the interval [-1000, 1000] - try again\nEnter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    @Test
    fun testNegativeNumbers() {
        val input = "-5 -3\nq\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: Their sum is -8\n\nEnter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    @Test
    fun testMixedSignNumbers() {
        val input = "-5 3\nq\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: Their sum is -2\n\nEnter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    @Test
    fun testQuitCommand() {
        val input = "q\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    @Test
    fun testQuitCommandCaseInsensitive() {
        val input = "Q\n"
        val expectedOutput = "Enter two integers separated by space(s) or q to quit: "
        assertEquals(expectedOutput, getProgramOutput(input))
    }

    private fun getProgramOutput(input: String): String {
        val inStream = ByteArrayInputStream(input.toByteArray())
        val outStream = ByteArrayOutputStream()
        System.setIn(inStream)
        System.setOut(PrintStream(outStream))

        main(arrayOf())

        return outStream.toString()
    }
}
