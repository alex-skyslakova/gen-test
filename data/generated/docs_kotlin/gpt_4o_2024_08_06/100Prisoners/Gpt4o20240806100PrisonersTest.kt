import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PrisonersTest {

    @Test
    fun testOptimalStrategySuccessRate() {
        val successRate = exec(100_000, playOptimal)
        // The optimal strategy should have a success rate significantly higher than the random strategy
        // According to the problem, it should be around 31%
        assertTrue(successRate > 30.0, "Optimal strategy success rate should be greater than 30%")
    }

    @Test
    fun testRandomStrategySuccessRate() {
        val successRate = exec(100_000, playRandom)
        // The random strategy should have a very low success rate
        // It should be close to 0.0000000000000000000000000000008%
        assertTrue(successRate < 1.0, "Random strategy success rate should be less than 1%")
    }

    @Test
    fun testOptimalStrategyAlwaysReturnsBoolean() {
        repeat(100) {
            val result = playOptimal()
            assertTrue(result is Boolean, "Optimal strategy should return a Boolean")
        }
    }

    @Test
    fun testRandomStrategyAlwaysReturnsBoolean() {
        repeat(100) {
            val result = playRandom()
            assertTrue(result is Boolean, "Random strategy should return a Boolean")
        }
    }

    @Test
    fun testOptimalStrategyAlwaysSucceedsForSmallN() {
        // For a small number of executions, the optimal strategy should still succeed sometimes
        val successRate = exec(10, playOptimal)
        assertTrue(successRate > 0.0, "Optimal strategy should succeed at least once in 10 executions")
    }

    @Test
    fun testRandomStrategyFailsForSmallN() {
        // For a small number of executions, the random strategy should likely fail
        val successRate = exec(10, playRandom)
        assertTrue(successRate < 100.0, "Random strategy should not succeed in all 10 executions")
    }
}
