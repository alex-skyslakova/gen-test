import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class LongPrimesTest {

    @Test
    fun testSieve() {
        val primes = sieve(500)
        val expectedPrimes = listOf(3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499)
        assertEquals(expectedPrimes, primes)
    }

    @Test
    fun testFindPeriod() {
        assertEquals(6, findPeriod(7))
        assertEquals(1, findPeriod(3))
        assertEquals(16, findPeriod(17))
        assertEquals(18, findPeriod(19))
    }

    @Test
    fun testMainOutput() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(arrayOf())

        val expectedOutput = """
            The long primes up to 500 are:
            [7, 17, 19, 23, 29, 47, 59, 61, 97, 109, 113, 131, 149, 167, 179, 181, 193, 223, 229, 233, 257, 263, 269, 313, 337, 367, 379, 383, 389, 419, 433, 461, 487, 491, 499]

            The number of long primes up to:
              500 is 35
             1000 is 60
             2000 is 116
             4000 is 218
             8000 is 390
            16000 is 716
            32000 is 1300
            64000 is 2430
        """.trimIndent()

        assertEquals(expectedOutput, outContent.toString().trim())
    }
}
