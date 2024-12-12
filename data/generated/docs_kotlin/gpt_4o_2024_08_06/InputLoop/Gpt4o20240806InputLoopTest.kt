import org.junit.Test
import org.junit.Assert.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InputLoopTest {

    @Test
    fun testSingleWordInput() {
        val input = "hello\nquit\n"
        val expectedOutput = "Keep entering text or the word 'quit' to end the program:\n\nYou entered the following words:\nhello\n"

        assertEquals(expectedOutput, runInputLoop(input))
    }

    @Test
    fun testMultipleWordsInput() {
        val input = "hello\nworld\nquit\n"
        val expectedOutput = "Keep entering text or the word 'quit' to end the program:\n\nYou entered the following words:\nhello\nworld\n"

        assertEquals(expectedOutput, runInputLoop(input))
    }

    @Test
    fun testNoWordsInput() {
        val input = "quit\n"
        val expectedOutput = "Keep entering text or the word 'quit' to end the program:\n"

        assertEquals(expectedOutput, runInputLoop(input))
    }

    @Test
    fun testMixedCaseQuit() {
        val input = "hello\nQUIT\n"
        val expectedOutput = "Keep entering text or the word 'quit' to end the program:\n\nYou entered the following words:\nhello\n"

        assertEquals(expectedOutput, runInputLoop(input))
    }

    @Test
    fun testLeadingAndTrailingSpaces() {
        val input = "  hello  \nquit\n"
        val expectedOutput = "Keep entering text or the word 'quit' to end the program:\n\nYou entered the following words:\nhello\n"

        assertEquals(expectedOutput, runInputLoop(input))
    }

    private fun runInputLoop(input: String): String {
        val inputStream = ByteArrayInputStream(input.toByteArray())
        val outputStream = ByteArrayOutputStream()
        val originalIn = System.`in`
        val originalOut = System.out

        try {
            System.setIn(inputStream)
            System.setOut(PrintStream(outputStream))
            main(arrayOf())
        } finally {
            System.setIn(originalIn)
            System.setOut(originalOut)
        }

        return outputStream.toString().replace("\r\n", "\n") // Normalize line endings
    }
}
