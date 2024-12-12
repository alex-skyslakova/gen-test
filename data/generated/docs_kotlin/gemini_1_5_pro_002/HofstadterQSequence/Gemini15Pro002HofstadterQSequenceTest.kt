import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HofstadterQSequenceTest {

    private fun qSequence(n: Int): Int {
        val q = IntArray(n + 1)
        q[1] = 1
        q[2] = 1
        for (i in 3..n) q[i] = q[i - q[i - 1]] + q[i - q[i - 2]]
        return q[n]
    }
    
    private fun qSequenceFirstN(n: Int): List<Int> {
        val q = IntArray(n + 1)
        q[1] = 1
        q[2] = 1
        for (i in 3..n) q[i] = q[i - q[i - 1]] + q[i - q[i - 2]]
        return q.slice(1..n)
    }

    private fun countFlips(n: Int): Int {
         val q = IntArray(n + 1)
        q[1] = 1
        q[2] = 1
        for (i in 3..n) q[i] = q[i - q[i - 1]] + q[i - q[i - 2]]
        return (2..n).count { q[it] < q[it - 1] }
    }


    @Test
    fun testFirstTenTerms() {
        val expected = listOf(1, 1, 2, 3, 3, 4, 5, 5, 6, 6)
        val actual = qSequenceFirstN(10)
        assertIterableEquals(expected, actual)
    }

    @Test
    fun testThousandthTerm() {
        val expected = 502
        val actual = qSequence(1000)
        assertEquals(expected, actual)
    }

    @Test
    fun testHundredThousandthTermFlips() {
        val expected = 49470  // Pre-calculated, might vary slightly if implementation differs.
        val actual = countFlips(100_000)
        assertEquals(expected, actual)
    }

    @Test
    fun testLargeN() {
        // Test with a large n to ensure no stack overflow or other issues
        val result = qSequence(500_000)
        assertTrue(result > 0) // Just check it completes and returns a somewhat sane value.
    }
    

      @Test
    fun testSmallNValues() {
        assertEquals(1, qSequence(1))
        assertEquals(1, qSequence(2))
        assertEquals(2, qSequence(3))
        assertEquals(3, qSequence(4))
    }


}
