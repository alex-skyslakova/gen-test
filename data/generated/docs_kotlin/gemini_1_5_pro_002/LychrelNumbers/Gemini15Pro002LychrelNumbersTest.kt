import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class LychrelNumbersTest {

    @Test
    fun testIsPalindrome() {
        assertTrue(isPalindrome(BigInteger.valueOf(121)))
        assertTrue(isPalindrome(BigInteger.valueOf(55)))
        assertTrue(isPalindrome(BigInteger.valueOf(1)))
        assertFalse(isPalindrome(BigInteger.valueOf(12)))
        assertFalse(isPalindrome(BigInteger.valueOf(1234)))
    }

    @Test
    fun testLychrelTest_palindrome() {
        val seq = mutableListOf<BigInteger>()
        lychrelTest(33, seq)
        assertEquals(0, lychrelSieve[33]) // Not Lychrel
        assertTrue(seq.isEmpty())


        lychrelTest(44,seq)
        assertEquals(0, lychrelSieve[44])
        assertTrue(seq.isEmpty())
    }

    @Test
    fun testLychrelTest_nonPalindrome() {
        val seq = mutableListOf<BigInteger>()
        lychrelTest(12, seq)
        assertEquals(0, lychrelSieve[12]) // Not Lychrel
        assertEquals(1, seq.size)
        assertEquals(BigInteger.valueOf(33), seq[0])

        seq.clear()

        lychrelTest(55, seq)
        assertEquals(0, lychrelSieve[55]) // Not Lychrel
        assertEquals(2, seq.size)
        assertEquals(BigInteger.valueOf(110), seq[0])
        assertEquals(BigInteger.valueOf(121), seq[1])
    }


    @Test
    fun testLychrelTest_potentialLychrel() {
        val seq = mutableListOf<BigInteger>()
        lychrelTest(196, seq)
        assertEquals(1, lychrelSieve[196]) // Seed Lychrel

        seq.clear()

        lychrelTest(689, seq)
        assertEquals(2, lychrelSieve[689]) // Related Lychrel

    }


    @Test
    fun testLychrelTest_smallLimit() {
         val originalITERATIONS = ITERATIONS
         val originalLIMIT = LIMIT
         val originalBigLimit = bigLimit


         try{
                val smallLimit = 100
                val smallIterations = 5
                // redefine constants for testing purposes
                ITERATIONS = smallIterations;
                LIMIT = smallLimit;
                bigLimit = BigInteger.valueOf(LIMIT.toLong());


                lychrelSieve.fill(0) // reset sieve
                seedLychrels.clear()
                relatedLychrels.clear()

                val seq = mutableListOf<BigInteger>()
                for (i in 1..LIMIT) {
                     if (lychrelSieve[i] == 0) {
                           seq.clear()
                           lychrelTest(i, seq)
                     }
                }
                var related = lychrelSieve.count { it == 2 }
         } finally {
                ITERATIONS = originalITERATIONS
                LIMIT = originalLIMIT
                bigLimit = originalBigLimit
         }
    }

}

