import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.test.assertEquals

class BenfordTest {

    @Test
    fun `test Benford's law distribution for Fibonacci numbers`() {
        val benford = Benford(FibonacciGenerator)
        val expectedDistribution = calculateExpectedDistribution()
        val actualDistribution = benford.toString().split("\n").map { it.split("\t")[1].toDouble() }

        for (i in 1..9) {
            val actual = actualDistribution[i - 1]
            val expected = expectedDistribution[i - 1]
            assertEquals(expected, actual, 0.01, "Digit $i distribution mismatch")
        }
    }

    private fun calculateExpectedDistribution(): List<Double> {
        return (1..9).map { d ->
            Math.log10(1 + 1.0 / d)
        }
    }

    @Test
    fun `test FibonacciGenerator generates correct number of Fibonacci numbers`() {
        val fibonacciNumbers = FibonacciGenerator.numbers
        assertEquals(1000, fibonacciNumbers.size, "FibonacciGenerator should generate 1000 numbers")
    }

    @Test
    fun `test FibonacciGenerator generates correct Fibonacci sequence`() {
        val fibonacciNumbers = FibonacciGenerator.numbers
        assertEquals(BigInteger.ONE, fibonacciNumbers[0], "First Fibonacci number should be 1")
        assertEquals(BigInteger.ONE, fibonacciNumbers[1], "Second Fibonacci number should be 1")
        assertEquals(BigInteger("2"), fibonacciNumbers[2], "Third Fibonacci number should be 2")
        assertEquals(BigInteger("3"), fibonacciNumbers[3], "Fourth Fibonacci number should be 3")
        assertEquals(BigInteger("5"), fibonacciNumbers[4], "Fifth Fibonacci number should be 5")
        assertEquals(BigInteger("8"), fibonacciNumbers[5], "Sixth Fibonacci number should be 8")
    }

    @Test
    fun `test Benford's law distribution for a custom number generator`() {
        val customGenerator = object : NumberGenerator {
            override val numbers: Array<BigInteger>
                get() = arrayOf(
                    BigInteger.ONE, BigInteger.TEN, BigInteger("100"), BigInteger("200"), BigInteger("300"),
                    BigInteger("400"), BigInteger("500"), BigInteger("600"), BigInteger("700"), BigInteger("800"),
                    BigInteger("900"), BigInteger("1000")
                )
        }

        val benford = Benford(customGenerator)
        val expectedDistribution = calculateExpectedDistribution()
        val actualDistribution = benford.toString().split("\n").map { it.split("\t")[1].toDouble() }

        for (i in 1..9) {
            val actual = actualDistribution[i - 1]
            val expected = expectedDistribution[i - 1]
            assertEquals(expected, actual, 0.01, "Digit $i distribution mismatch")
        }
    }
}
