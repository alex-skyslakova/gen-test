import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AverageLoopLengthTest {

    companion object {
        const val DELTA = 0.05 // Allowable error margin for floating-point comparisons
    }

    @Test
    fun testAnalyticalFunction() {
        val expectedResults = listOf(
            1.0, 1.5, 1.8889, 2.2188, 2.5104, 2.7747, 3.0181, 3.2450, 3.4583, 3.6602,
            3.8524, 4.0361, 4.2123, 4.3820, 4.5458, 4.7043, 4.8579, 5.0071, 5.1522, 5.2936
        )

        for (n in 1..expectedResults.size) {
            val analyticalResult = ana(n)
            assertEquals(expectedResults[n - 1], analyticalResult, DELTA, "Analytical result for N=$n is incorrect")
        }
    }

    @Test
    fun testAverageFunction() {
        // These tests are more about checking that the function runs without error
        // and returns a plausible result, rather than exact values due to randomness.
        for (n in 1..20) {
            val averageResult = avg(n)
            val analyticalResult = ana(n)
            // Check that the average result is within a reasonable range of the analytical result
            assertEquals(analyticalResult, averageResult, 0.5, "Average result for N=$n is not within expected range")
        }
    }
}
