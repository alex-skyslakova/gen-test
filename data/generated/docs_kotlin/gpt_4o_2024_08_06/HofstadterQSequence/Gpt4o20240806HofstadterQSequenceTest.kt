import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HofstadterQSequenceTest {

    @Test
    fun testFirstTenTerms() {
        val expectedFirstTenTerms = listOf(1, 1, 2, 3, 3, 4, 5, 5, 6, 6)
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]
        
        val actualFirstTenTerms = (1..10).map { q[it] }
        assertEquals(expectedFirstTenTerms, actualFirstTenTerms, "The first ten terms of the sequence are incorrect.")
    }

    @Test
    fun testThousandthTerm() {
        val expectedThousandthTerm = 502
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]
        
        val actualThousandthTerm = q[1000]
        assertEquals(expectedThousandthTerm, actualThousandthTerm, "The 1000th term of the sequence is incorrect.")
    }

    @Test
    fun testFlipsCount() {
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]
        
        val flips = (2..100_000).count { q[it] < q[it - 1] }
        val expectedFlips = 49650 // This is the expected number of flips, calculated beforehand.
        
        assertEquals(expectedFlips, flips, "The number of flips for the first 100,000 terms is incorrect.")
    }
}
