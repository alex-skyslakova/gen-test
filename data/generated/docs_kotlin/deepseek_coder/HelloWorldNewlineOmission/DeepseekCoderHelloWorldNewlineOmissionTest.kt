import org.junit.Test
import kotlin.test.assertEquals

class HelloWorldNewlineOmissionTest {

    @Test
    fun testOutputWithoutNewline() {
        // Capture the standard output
        val outputStream = System.out
        val byteArrayOutputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(byteArrayOutputStream))

        // Run the main function
        HelloWorldNewlineOmission.main(arrayOf())

        // Reset the standard output
        System.setOut(outputStream)

        // Verify the output
        val output = byteArrayOutputStream.toString().trim()
        assertEquals("Goodbye, World!", output)
    }
}
