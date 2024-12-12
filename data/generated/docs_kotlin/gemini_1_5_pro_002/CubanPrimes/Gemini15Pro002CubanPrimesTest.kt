import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CubanPrimesTest {

    @Test
    fun testFirst200CubanPrimes() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main()

        val output = outputStream.toString()
        val lines = output.split("\n")

        // Check the first line
        assertTrue(lines[0].startsWith("The first 200 cuban primes:"))

        // Check that 200 primes are printed
        val primeLines = lines.filter { it.trim().isNotEmpty() && !it.startsWith("The") && !it.startsWith("Progress") && !it.startsWith("The ") }
        val printedPrimes = primeLines.joinToString(" ").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        assertEquals(200, printedPrimes.size)


        // Spot check a few known cuban primes (from OEIS A002407)
        assertTrue(printedPrimes.contains(7L))
        assertTrue(printedPrimes.contains(19L))
        assertTrue(printedPrimes.contains(37L))
        assertTrue(printedPrimes.contains(61L))
        assertTrue(printedPrimes.contains(127L))

    }


    @Test
    fun test100000thCubanPrime() {
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main()

        val output = outputStream.toString()
        val lines = output.split("\n")

       // Check the last line containing the 100,000th prime
        val lastLine = lines.last { it.trim().isNotEmpty() }
        assertTrue(lastLine.startsWith("The 100000th cuban prime is"))
        assertEquals(1738961541, lastLine.split(" ").last().toLong())  // Verified value from OEIS A002407

    }



    @Test
    fun testProgressDots() {
         val outputStream = ByteArrayOutputStream()
         System.setOut(PrintStream(outputStream))

         main()

         val output = outputStream.toString()

         //Check progress dots
         assertTrue(output.contains("Progress to the 100000th cuban prime: "))
         val progressLineIndex = output.lines().indexOfFirst { it.contains("Progress to the 100000th cuban prime: ") }
         val dotsLine = output.lines()[progressLineIndex + 1]
         assertEquals(2000, dotsLine.count { it == '.'}) // 100000 / 50 = 2000 dots

    }
}


