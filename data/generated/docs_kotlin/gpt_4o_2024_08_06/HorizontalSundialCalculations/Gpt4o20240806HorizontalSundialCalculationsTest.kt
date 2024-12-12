import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class HorizontalSundialCalculationsTest {

    @Test
    fun testSundialCalculations() {
        val input = "4.95\n150.5\n150.0\n"
        val expectedOutput = """
            Please enter the following in degrees:
              Latitude       :   Longitude      :   Legal Meridian : 
            Sine of latitude     = 0.086318
            Longitude - Meridian = 0.500

            Hour   Sun Hour Angle  Dial Hour Line Angle
            -----  --------------  --------------------
                          °               °
             6 AM      -90.500         -89.995
             7 AM      -75.500         -85.638
             8 AM      -60.500         -77.825
             9 AM      -45.500         -65.860
            10 AM      -30.500         -49.034
            11 AM      -15.500         -26.619
            12 PM       +0.500         +0.000
             1 PM      +15.500         +26.619
             2 PM      +30.500         +49.034
             3 PM      +45.500         +65.860
             4 PM      +60.500         +77.825
             5 PM      +75.500         +85.638
             6 PM      +90.500         +89.995

        """.trimIndent()

        val inputStream = ByteArrayInputStream(input.toByteArray())
        val outputStream = ByteArrayOutputStream()
        System.setIn(inputStream)
        System.setOut(PrintStream(outputStream))

        main(arrayOf())

        assertEquals(expectedOutput, outputStream.toString().trim())
    }
}
