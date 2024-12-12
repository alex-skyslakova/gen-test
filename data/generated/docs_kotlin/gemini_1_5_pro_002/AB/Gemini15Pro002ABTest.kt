import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import org.junit.jupiter.api.Assertions.*

class ABTest {

    private val standardIn = System.`in`
    private val standardOut = System.out
    private val streamOut = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(streamOut))
    }

    @AfterEach
    fun tearDown() {
        System.setIn(standardIn)
        System.setOut(standardOut)
    }

    @Test
    fun testValidInput() {
        val input = "2 2\nq"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        main(arrayOf())
        assertEquals("Enter two integers separated by space(s) or q to quit: Their sum is 4\n\nEnter two integers separated by space(s) or q to quit: ", streamOut.toString())
    }

    @Test
    fun testValidNegativeInput() {
        val input = "-2 -3\nq"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        main(arrayOf())
        assertEquals("Enter two integers separated by space(s) or q to quit: Their sum is -5\n\nEnter two integers separated by space(s) or q to quit: ", streamOut.toString())
    }


    @Test
    fun testInvalidInput() {
        val input = "abc\nq"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        main(arrayOf())
        assertEquals("Enter two integers separated by space(s) or q to quit: Invalid input, try again\nEnter two integers separated by space(s) or q to quit: ", streamOut.toString())
    }

    @Test
    fun testOutOfRangeInput() {
        val input = "1001 2\nq"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        main(arrayOf())
        assertEquals("Enter two integers separated by space(s) or q to quit: Both numbers must be in the interval [-1000, 1000] - try again\nEnter two integers separated by space(s) or q to quit: ", streamOut.toString())

    }

    @Test
    fun testQuit() {
        val input = "q"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        main(arrayOf())
        assertEquals("Enter two integers separated by space(s) or q to quit: ", streamOut.toString())
    }

     @Test
    fun testMultipleSpaces() {
        val input = "2   3\nq"
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        main(arrayOf())
        assertEquals("Enter two integers separated by space(s) or q to quit: Their sum is 5\n\nEnter two integers separated by space(s) or q to quit: ", streamOut.toString())
    }


}
