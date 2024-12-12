import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class BenfordTest {

    @Test
    fun `test FibonacciGenerator generates 1000 numbers`() {
        val numbers = FibonacciGenerator.numbers
        assertEquals(1000, numbers.size, "FibonacciGenerator should generate 1000 numbers")
    }

    @Test
    fun `test first Fibonacci numbers`() {
        val numbers = FibonacciGenerator.numbers
        assertEquals(BigInteger.ONE, numbers[0], "First Fibonacci number should be 1")
        assertEquals(BigInteger.ONE, numbers[1], "Second Fibonacci number should be 1")
        assertEquals(BigInteger.valueOf(2), numbers[2], "Third Fibonacci number should be 2")
        assertEquals(BigInteger.valueOf(3), numbers[3], "Fourth Fibonacci number should be 3")
        assertEquals(BigInteger.valueOf(5), numbers[4], "Fifth Fibonacci number should be 5")
    }

    @Test
    fun `test Benford distribution with Fibonacci numbers`() {
        val benford = Benford(FibonacciGenerator)
        val expectedDistribution = arrayOf(
            0.301, 0.176, 0.125, 0.097, 0.079, 0.067, 0.058, 0.051, 0.046
        )

        val actualDistribution = benford.toString().lines().filter { it.isNotBlank() }.map {
            it.split('\t')[1].toDouble()
        }

        for (i in expectedDistribution.indices) {
            assertEquals(expectedDistribution[i], actualDistribution[i], 0.01, "Distribution for digit ${i + 1} should match")
        }
    }

    @Test
    fun `test Benford expected probabilities`() {
        val benford = Benford(FibonacciGenerator)
        val expectedProbabilities = arrayOf(
            Math.log10(2.0), Math.log10(1.5), Math.log10(1.333), Math.log10(1.25),
            Math.log10(1.2), Math.log10(1.166), Math.log10(1.143), Math.log10(1.125), Math.log10(1.111)
        )

        val actualProbabilities = benford.toString().lines().filter { it.isNotBlank() }.map {
            it.split('\t')[2].toDouble()
        }

        for (i in expectedProbabilities.indices) {
            assertEquals(expectedProbabilities[i], actualProbabilities[i], 0.001, "Expected probability for digit ${i + 1} should match")
        }
    }
}
