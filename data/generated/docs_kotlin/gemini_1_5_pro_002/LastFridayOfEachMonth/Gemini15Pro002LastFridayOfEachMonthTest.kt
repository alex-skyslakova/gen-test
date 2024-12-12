import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class LastFridayTest {

    @Test
    fun testLastFridays2012() {
        val expectedOutput = """
            2012-01-27
            2012-02-24
            2012-03-30
            2012-04-27
            2012-05-25
            2012-06-29
            2012-07-27
            2012-08-31
            2012-09-28
            2012-10-26
            2012-11-30
            2012-12-28
        """.trimIndent()
        assertOutput(2012, expectedOutput)
    }

    @Test
    fun testLastFridays2023() {
         val expectedOutput = """
            2023-01-27
            2023-02-24
            2023-03-31
            2023-04-28
            2023-05-26
            2023-06-30
            2023-07-28
            2023-08-25
            2023-09-29
            2023-10-27
            2023-11-24
            2023-12-29
        """.trimIndent()
        assertOutput(2023, expectedOutput)
    }

    @Test
    fun testLastFridays1900() { // Leap year
        val expectedOutput = """
            1900-01-26
            1900-02-23
            1900-03-30
            1900-04-27
            1900-05-25
            1900-06-29
            1900-07-27
            1900-08-31
            1900-09-28
            1900-10-26
            1900-11-30
            1900-12-28
        """.trimIndent()

        assertOutput(1900, expectedOutput)

    }

     @Test
    fun testLastFridays2000() { // Leap year
        val expectedOutput = """
            2000-01-28
            2000-02-25
            2000-03-31
            2000-04-28
            2000-05-26
            2000-06-30
            2000-07-28
            2000-08-25
            2000-09-29
            2000-10-27
            2000-11-24
            2000-12-29
        """.trimIndent()

        assertOutput(2000, expectedOutput)

    }



    private fun assertOutput(year: Int, expectedOutput: String) {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Simulate user input. Replace with your actual input method if different
        val simulatedInput = "$year\n"
        System.setIn(simulatedInput.byteInputStream())

        main(arrayOf()) // Call the main function

        val actualOutput = outputStream.toString().trim()


        assertEquals(expectedOutput, actualOutput.substringAfter("The last Fridays of each month in $year are as follows:").trim())

        // Reset System.out to default
        System.setOut(System.out)
        System.setIn(System.`in`)

    }
}
