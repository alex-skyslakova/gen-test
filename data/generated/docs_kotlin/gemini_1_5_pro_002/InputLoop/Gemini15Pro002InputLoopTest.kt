import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class InputLoopTest {

    private val standardIn = System.`in`
    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setIn(standardIn)
        System.setOut(standardOut)
    }

    @Test
    fun testQuitImmediately() {
        val input = "quit\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        assertEquals("Keep entering text or the word 'quit' to end the program:\n", outputStreamCaptor.toString())
    }


    @Test
    fun testSingleWordInput() {
        val input = "hello\nquit\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        assertEquals("Keep entering text or the word 'quit' to end the program:\n\nYou entered the following words:\nhello\n", outputStreamCaptor.toString())

    }

    @Test
    fun testMultipleWordsInput() {
        val input = "hello\nworld\ntest\nquit\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        assertEquals("Keep entering text or the word 'quit' to end the program:\n\nYou entered the following words:\nhello\nworld\ntest\n", outputStreamCaptor.toString())
    }

    @Test
    fun testCaseInsensitiveQuit() {
        val input = "Quit\n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        assertEquals("Keep entering text or the word 'quit' to end the program:\n", outputStreamCaptor.toString())
    }

    @Test
    fun testQuitWithTrailingSpaces() {
        val input = "quit   \n"
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        main(arrayOf())

        assertEquals("Keep entering text or the word 'quit' to end the program:\n", outputStreamCaptor.toString())
    }



}
