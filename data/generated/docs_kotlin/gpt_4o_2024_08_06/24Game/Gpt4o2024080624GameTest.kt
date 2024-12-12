import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.Stack

internal class Game24Test {

    @Test
    fun testCorrectExpression() {
        val input = "3 3 8 8\n3 3 8 8 * +\n"
        val inputStream: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val output = captureOutput {
            Game24.run()
        }

        assertTrue(output.contains("Correct!"))
    }

    @Test
    fun testIncorrectExpression() {
        val input = "3 3 8 8\n3 3 8 8 + +\n"
        val inputStream: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val output = captureOutput {
            Game24.run()
        }

        assertTrue(output.contains("Not correct."))
    }

    @Test
    fun testInvalidDigits() {
        val input = "3 3 8 8\n3 3 8 9 * +\n"
        val inputStream: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val output = captureOutput {
            Game24.run()
        }

        assertTrue(output.contains("Not the same digits."))
    }

    @Test
    fun testDivisionByZero() {
        val input = "3 3 8 8\n3 0 / 8 8 + +\n"
        val inputStream: InputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)

        val output = captureOutput {
            Game24.run()
        }

        assertTrue(output.contains("Not correct."))
    }

    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        block()

        System.setOut(originalOut)
        return outputStream.toString()
    }
}
