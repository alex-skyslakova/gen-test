import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class LargestNumberDivisibleByItsDigitsTest {

    @Test
    fun testDivByAll() {
        assertTrue(135.divByAll(listOf('1', '3', '5')))
        assertTrue(36.divByAll(listOf('3', '6')))
        assertTrue(72.divByAll(listOf('7', '2')))
    }

    @Test
    fun testMainFunction() {
        // Capture the output of the main function
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        main(arrayOf())

        // Check if the output contains the expected largest number
        val output = outputStream.toString().trim()
        assertTrue(output.contains("Largest decimal number is 9867312"))
    }
}
