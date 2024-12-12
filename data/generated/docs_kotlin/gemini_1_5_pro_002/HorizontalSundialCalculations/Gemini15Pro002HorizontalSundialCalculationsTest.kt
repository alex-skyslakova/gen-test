import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.lang.Math.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream


class HorizontalSundialCalculationsTest {

    @Test
    fun testCalculationsLincolnIsland() {
        val input = "4.95\n-150.5\n-150\n"
        val expectedOutput = """
            Please enter the following in degrees:
              Latitude       :   Longitude      :   Legal Meridian : 
            Sine of latitude     = 0.086429
            Longitude - Meridian = -0.500

            Hour   Sun Hour Angle  Dial Hour Line Angle
            -----  --------------  --------------------
                          °               °
             6 AM      -90.500         -5.023
             7 AM      -75.500         -3.781
             8 AM      -60.500         -2.522
             9 AM      -45.500         -1.245
            10 AM      -30.500         -0.000
            11 AM      -15.500         +1.200
            12 PM       -0.500         +2.354
             1 PM      +14.500         +3.453
             2 PM      +29.500         +4.489
             3 PM      +44.500         +5.456
             4 PM      +59.500         +6.349
             5 PM      +74.500         +7.164
             6 PM      +89.500         +7.898
        """.trimIndent()

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        System.setIn(ByteArrayInputStream(input.toByteArray()))


       main(arrayOf())


        assertEquals(expectedOutput, outputStream.toString().trimIndent())
    }

    @Test
    fun testCalculationsEquatorZeroMeridian() {
         val input = "0\n0\n0\n"
        val expectedOutput = """
            Please enter the following in degrees:
              Latitude       :   Longitude      :   Legal Meridian : 
            Sine of latitude     = 0.000000
            Longitude - Meridian = 0.000

            Hour   Sun Hour Angle  Dial Hour Line Angle
            -----  --------------  --------------------
                          °               °
             6 AM      -90.000         +0.000
             7 AM      -75.000         +0.000
             8 AM      -60.000         +0.000
             9 AM      -45.000         +0.000
            10 AM      -30.000         +0.000
            11 AM      -15.000         +0.000
            12 PM        0.000         +0.000
             1 PM      +15.000         +0.000
             2 PM      +30.000         +0.000
             3 PM      +45.000         +0.000
             4 PM      +60.000         +0.000
             5 PM      +75.000         +0.000
             6 PM      +90.000         +0.000
        """.trimIndent()

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        System.setIn(ByteArrayInputStream(input.toByteArray()))


        main(arrayOf())


        assertEquals(expectedOutput, outputStream.toString().trimIndent())
    }


     @Test
    fun testCalculationsNorthPole() {
        val input = "90\n0\n0\n"
        val expectedOutput = """
            Please enter the following in degrees:
              Latitude       :   Longitude      :   Legal Meridian : 
            Sine of latitude     = 1.000000
            Longitude - Meridian = 0.000

            Hour   Sun Hour Angle  Dial Hour Line Angle
            -----  --------------  --------------------
                          °               °
             6 AM      -90.000         -90.000
             7 AM      -75.000         -75.000
             8 AM      -60.000         -60.000
             9 AM      -45.000         -45.000
            10 AM      -30.000         -30.000
            11 AM      -15.000         -15.000
            12 PM        0.000         +0.000
             1 PM      +15.000         +15.000
             2 PM      +30.000         +30.000
             3 PM      +45.000         +45.000
             4 PM      +60.000         +60.000
             5 PM      +75.000         +75.000
             6 PM      +90.000         +90.000
        """.trimIndent()

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        System.setIn(ByteArrayInputStream(input.toByteArray()))


        main(arrayOf())


        assertEquals(expectedOutput, outputStream.toString().trimIndent())
    }
}
