import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LoopsIncrementLoopIndexWithinLoopBodyTest {

    @Test
    fun testIsPrime() {
        // Test known primes
        assertEquals(true, isPrime(2))
        assertEquals(true, isPrime(3))
        assertEquals(true, isPrime(5))
        assertEquals(true, isPrime(7))
        assertEquals(true, isPrime(11))
        
        // Test known non-primes
        assertEquals(false, isPrime(1))
        assertEquals(false, isPrime(4))
        assertEquals(false, isPrime(6))
        assertEquals(false, isPrime(8))
        assertEquals(false, isPrime(9))
        assertEquals(false, isPrime(10))
    }

    @Test
    fun testMainLoop() {
        // Redirect output to capture it for testing
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        // Call the main function
        main(arrayOf())

        // Get the output
        val output = outputStream.toString()

        // Check that 42 primes are printed
        val primeCount = output.lines().filter { it.contains("n =") }.count()
        assertEquals(42, primeCount)

        // Check the first few primes for correctness
        val expectedPrimes = listOf(
            "n = 1   43",
            "n = 2   47",
            "n = 3   53",
            "n = 4   61",
            "n = 5   73"
        )
        expectedPrimes.forEach { expected ->
            assert(output.contains(expected)) { "Expected output to contain: $expected" }
        }
    }
}
