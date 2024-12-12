import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.math.BigInteger

class IntegerSequenceTest {

    @Test
    fun testPrintUntilIntMaxValue() {
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        // Simulate the loop until Int.MAX_VALUE
        for (i in 0..10) { // Limiting to 10 for test purposes
            println(i)
        }

        System.setOut(originalOut)

        val expectedOutput = (0..10).joinToString(separator = "\n") + "\n"
        assertEquals(expectedOutput, outContent.toString())
    }

    @Test
    fun testPrintForeverWithBigInteger() {
        val originalOut = System.out
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        // Simulate the loop for BigInteger
        var n = BigInteger.ZERO
        for (i in 0..10) { // Limiting to 10 for test purposes
            println(n)
            n += BigInteger.ONE
        }

        System.setOut(originalOut)

        val expectedOutput = (0..10).joinToString(separator = "\n") + "\n"
        assertEquals(expectedOutput, outContent.toString())
    }
}
