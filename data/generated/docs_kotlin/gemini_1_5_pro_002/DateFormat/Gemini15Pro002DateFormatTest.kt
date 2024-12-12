import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.Locale
import kotlin.test.assertEquals

class DateFormatTest {

    @Test
    fun testDateFormats() {
        val now = GregorianCalendar(2007, Calendar.NOVEMBER, 23) // Fixed date for testing

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Call the main function with an empty array to simulate command line execution
        main(arrayOf())

        val expectedOutput = "2007-11-23\nFriday, November 23, 2007\n"
       assertEquals(expectedOutput, outputStream.toString())

         // Resetting the standard out
        System.setOut(System.out)
    }


    @Test
    fun testDateFormatsLocale() {
        val now = GregorianCalendar(2007, Calendar.NOVEMBER, 23) // Fixed date for testing
        val locale = Locale("fr", "FR") // Example: French locale


        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

         // Temporarily change the default locale
        val originalLocale = Locale.getDefault()
        Locale.setDefault(locale)


        // Call the main function with an empty array to simulate command line execution
        main(arrayOf())

        // Construct expected output based on the locale
        val expectedOutputBuilder = StringBuilder()
        expectedOutputBuilder.append("%tF".format(locale, now)).append("\n")
        expectedOutputBuilder.append("%tA, %1\$tB %1\$te, %1\$tY".format(locale,now)).append("\n")

        assertEquals(expectedOutputBuilder.toString(), outputStream.toString())


        // Restore original locale after the test
        Locale.setDefault(originalLocale)

        System.setOut(System.out)


    }
}
