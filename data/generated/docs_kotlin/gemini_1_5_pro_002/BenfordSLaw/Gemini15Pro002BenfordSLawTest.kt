import java.math.BigInteger
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BenfordTest {

    @Test
    fun testBenfordFibonacci() {
        val expectedDistribution = (1..9).map { Math.log10(1 + 1.0 / it) }

        val benford = Benford(FibonacciGenerator)
        val actualDistribution = benford.toString().lines().map { it.split("\t")[1].toDouble() }

        for (i in expectedDistribution.indices) {
            assertEquals(expectedDistribution[i], actualDistribution[i], 0.05) 
        }
    }


    @Test
    fun testBenfordEmptyInput() {
        val emptyGenerator = object : NumberGenerator {
            override val numbers: Array<BigInteger> = emptyArray()
        }

        val benford = Benford(emptyGenerator)
        val actualDistribution = benford.toString().lines().map { it.split("\t")[1].toDoubleOrNull() }

        assertTrue(actualDistribution.all { it == null || it.isNaN() })
        
    }


    @Test
    fun testBenfordSingleDigitInput(){
        val singleDigitGenerator = object : NumberGenerator {
            override val numbers: Array<BigInteger> = arrayOf(BigInteger.valueOf(7))
        }
        val benford = Benford(singleDigitGenerator)
        val actualDistributionString = benford.toString()
        val lines = actualDistributionString.lines()
        assertEquals(9, lines.size)
        val actualDistribution = lines.map { it.split("\t")[1].toDoubleOrNull() }

        assertEquals(1.0, actualDistribution[6], 0.001) // 7 should be the first digit
        for (i in actualDistribution.indices) {
            if (i != 6) {
                assertEquals(0.0, actualDistribution[i], 0.001) // All other digits should have 0 frequency.

            }
        }
    }



    @Test
    fun testBenfordNumbersWithLeadingZeros(){
        val leadingZerosGenerator = object : NumberGenerator {
            override val numbers: Array<BigInteger> = arrayOf(BigInteger.valueOf(0), BigInteger.valueOf(10),BigInteger.valueOf(200) )
        }

        val benford = Benford(leadingZerosGenerator)

        val actualDistributionString = benford.toString()
        val lines = actualDistributionString.lines()
        assertEquals(9, lines.size)
        val actualDistribution = lines.map { it.split("\t")[1].toDoubleOrNull() }

        assertEquals(1.0/3.0, actualDistribution[0], 0.001) // 1 should be the first digit 1/3 times
        assertEquals(1.0/3.0, actualDistribution[1], 0.001) // 2 should be the first digit 1/3 times

        for (i in actualDistribution.indices) {
            if (i != 0 && i!= 1) {
                assertEquals(0.0, actualDistribution[i], 0.001) 
            }
        }
    }


}




interface NumberGenerator {
    val numbers: Array<BigInteger>
}

class Benford(ng: NumberGenerator) {
    override fun toString() = str

    private val firstDigits = IntArray(9)
    private val count = ng.numbers.size.toDouble()
    private val str: String

    init {
        for (n in ng.numbers) {
            val firstDigitStr = n.toString().replaceFirst("^0+(?!$)".toRegex(), "").firstOrNull()?.toString() // Added regex for leading zeros
            if (firstDigitStr != null && firstDigitStr.toIntOrNull() != null) { // Handle potential nulls from empty strings
                val firstDigit = firstDigitStr.toInt()
                firstDigits[firstDigit - 1]++
            }
        }
        str = with(StringBuilder()) {
            for (i in firstDigits.indices) {
                val actualFreq = if (count > 0) firstDigits[i] / count else Double.NaN
                append(i + 1).append('\t').append(actualFreq).append('\t').append(Math.log10(1 + 1.0 / (i + 1))).append('\n')
            }
            toString()
        }
    }

}



object FibonacciGenerator : NumberGenerator {
    override val numbers: Array<BigInteger> by lazy {
        val fib = Array<BigInteger>(1000, { BigInteger.ONE })
        for (i in 2 until fib.size)
            fib[i] = fib[i - 2].add(fib[i - 1])
        fib
    }
}


