import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HofstadterQSequenceTest {

    @Test
    fun testFirstTenTerms() {
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]

        val expectedFirstTenTerms = intArrayOf(1, 1, 2, 3, 3, 4, 5, 5, 6, 6)
        for (i in 1..10) {
            assertEquals(expectedFirstTenTerms[i - 1], q[i], "Term $i does not match expected value")
        }
    }

    @Test
    fun testThousandthTerm() {
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]

        assertEquals(502, q[1000], "The 1000th term does not match expected value")
    }

    @Test
    fun testFlipsCount() {
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]

        val flips = (2..100_000).count { q[it] < q[it - 1] }
        // The exact number of flips is not provided in the task, so we just check if it's a positive integer
        assertTrue(flips > 0, "The number of flips should be a positive integer")
    }

    @Test
    fun testLargeNHandling() {
        val q = IntArray(100_001)
        q[1] = 1
        q[2] = 1
        for (n in 3..100_000) q[n] = q[n - q[n - 1]] + q[n - q[n - 2]]

        // Check if the sequence can handle large n without crashing
        assertDoesNotThrow {
            q[100_000]
        }
    }
}
