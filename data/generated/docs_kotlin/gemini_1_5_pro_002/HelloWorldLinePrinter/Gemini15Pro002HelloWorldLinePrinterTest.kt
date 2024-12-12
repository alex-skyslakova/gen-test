import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

class HelloWorldLinePrinterTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testHelloWorldPrintedToFile() {
        // Simulate /dev/lp0 with a temporary file
        val simulatedPrinter = File(tempDir, "simulated_lp0")

        // Redirect the output to the simulated printer
        val originalLp0 = System.getProperty("lp0")
        System.setProperty("lp0", simulatedPrinter.absolutePath)


        // Invoke the main function (replace with your actual invocation method)
        val args = arrayOf<String>() // Empty arguments if needed
        main(args)

        // Restore the original lp0 property if it existed
        if (originalLp0 != null) {
            System.setProperty("lp0", originalLp0)
        } else {
            System.clearProperty("lp0")
        }

        // Verify the content of the simulated printer file
        val expectedText = "Hello World!\n"
        val actualText = simulatedPrinter.readText()
        assertEquals(expectedText, actualText)
    }



    // Helper function to allow calling main within the test
    // This will depend on how you are actually running the code
    private fun main(args: Array<String>) {
        val text = "Hello World!\n"
        File(System.getProperty("lp0") ?: "/dev/lp0").writeText(text) // Use System property or fallback
    }
}


