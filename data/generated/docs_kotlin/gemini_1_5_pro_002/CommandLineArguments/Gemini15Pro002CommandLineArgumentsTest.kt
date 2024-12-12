import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CommandLineArgumentsTest {

    @Test
    fun testNoArguments() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        val expectedOutput = "There are 0 arguments given.\n"
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testSingleArgument() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("hello"))

        val expectedOutput = "There are 1 arguments given.\n" +
                "The argument #1 is hello and is at index 0\n"
        assertEquals(expectedOutput, outputStream.toString())

    }

    @Test
    fun testMultipleArguments() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("-c", "alpha beta", "-h", "gamma"))

        val expectedOutput = "There are 4 arguments given.\n" +
                "The argument #1 is -c and is at index 0\n" +
                "The argument #2 is alpha beta and is at index 1\n" +
                "The argument #3 is -h and is at index 2\n" +
                "The argument #4 is gamma and is at index 3\n"
        assertEquals(expectedOutput, outputStream.toString())
    }


    @Test
    fun testArgumentsWithSpaces() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main(arrayOf("arg with spaces", "another arg"))

        val expectedOutput = "There are 2 arguments given.\n" +
                "The argument #1 is arg with spaces and is at index 0\n" +
                "The argument #2 is another arg and is at index 1\n"

        assertEquals(expectedOutput, outputStream.toString())

    }

}
