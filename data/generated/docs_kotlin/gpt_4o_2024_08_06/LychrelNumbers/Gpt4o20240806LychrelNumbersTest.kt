import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class LychrelNumbersTest {

    @Test
    fun testIsPalindrome() {
        assertTrue(isPalindrome(BigInteger("121")))
        assertTrue(isPalindrome(BigInteger("1221")))
        assertFalse(isPalindrome(BigInteger("123")))
        assertFalse(isPalindrome(BigInteger("1234")))
    }

    @Test
    fun testLychrelTestForPalindrome() {
        val seq = mutableListOf<BigInteger>()
        lychrelTest(12, seq)
        assertEquals(listOf(BigInteger("33")), seq)
        assertEquals(0, lychrelSieve[12])
    }

    @Test
    fun testLychrelTestForNonPalindrome() {
        val seq = mutableListOf<BigInteger>()
        lychrelTest(196, seq)
        assertTrue(seq.isNotEmpty())
        assertEquals(1, lychrelSieve[196])
    }

    @Test
    fun testLychrelTestForRelatedNumber() {
        val seq = mutableListOf<BigInteger>()
        lychrelTest(689, seq)
        assertTrue(seq.isNotEmpty())
        assertEquals(2, lychrelSieve[689])
    }

    @Test
    fun testMainFunctionOutput() {
        main(arrayOf())
        assertEquals(1, seedLychrels.size)
        assertTrue(seedLychrels.contains(196))
        assertTrue(relatedLychrels.contains(BigInteger("689")))
    }

    @Test
    fun testPalindromicLychrelNumbers() {
        val palindromes = mutableListOf<Int>()
        for (i in 1..LIMIT) {
            if (lychrelSieve[i] > 0 && isPalindrome(BigInteger.valueOf(i.toLong()))) {
                palindromes.add(i)
            }
        }
        assertTrue(palindromes.isNotEmpty())
    }
}
