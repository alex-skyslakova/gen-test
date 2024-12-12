import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.test.assertEquals

class DateFormatTest {

    @Test
    fun testDateFormat() {
        // Redirect standard output to capture the printed output
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Call the main function
        main(arrayOf())

        // Get the current date
        val now = GregorianCalendar()

        // Define the expected output formats
        val dateFormat1 = SimpleDateFormat("yyyy-MM-dd")
        val dateFormat2 = SimpleDateFormat("EEEE, MMMM d, yyyy")

        // Create expected output strings
        val expectedOutput1 = dateFormat1.format(now.time)
        val expectedOutput2 = dateFormat2.format(now.time)

        // Get the actual output
        val actualOutput = outputStream.toString().trim().split("\n")

        // Verify the outputs
        assertEquals(expectedOutput1, actualOutput[0].trim())
        assertEquals(expectedOutput2, actualOutput[1].trim())
    }
}
