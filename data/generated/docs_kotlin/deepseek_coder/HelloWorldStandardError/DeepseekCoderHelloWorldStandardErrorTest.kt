import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.assertEquals

class HelloWorldStandardErrorTest {

    @Test
    fun testStandardErrorOutput() {
        // Redirect standard error to a ByteArrayOutputStream
        val originalErr = System.err
        val errContent = ByteArrayOutputStream()
        System.setErr(PrintStream(errContent))

        // Call the main function
        HelloWorldStandardError.main(arrayOf())

        // Restore original standard error
        System.setErr(originalErr)

        // Verify the output
        assertEquals("Goodbye, World!\n", errContent.toString())
    }
}
