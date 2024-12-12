import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class HelloWorldNewlineOmissionTest {

    @Test
    fun testOutputWithoutNewline() {
        // Redirect standard output to capture the print statement
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Call the main function
        main(arrayOf())

        // Check the output
        assertEquals("Goodbye, World!", outputStream.toString())
    }
}
