import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class FaulhaberSFormulaTest {

    @Test
    fun testFracInitialization() {
        val frac1 = Frac(3, 4)
        assertEquals(3, frac1.num)
        assertEquals(4, frac1.denom)

        val frac2 = Frac(-3, -4)
        assertEquals(3, frac2.num)
        assertEquals(4, frac2.denom)

        val frac3 = Frac(0, 5)
        assertEquals(0, frac3.num)
        assertEquals(1, frac3.denom)
    }

    @Test
    fun testFracOperations() {
        val frac1 = Frac(1, 2)
        val frac2 = Frac(1, 3)

        val sum = frac1 + frac2
        assertEquals(Frac(5, 6), sum)

        val difference = frac1 - frac2
        assertEquals(Frac(1, 6), difference)

        val product = frac1 * frac2
        assertEquals(Frac(1, 6), product)
    }

    @Test
    fun testFracComparison() {
        val frac1 = Frac(1, 2)
        val frac2 = Frac(2, 4)
        val frac3 = Frac(3, 4)

        assertTrue(frac1 == frac2)
        assertFalse(frac1 == frac3)
        assertTrue(frac1 < frac3)
        assertTrue(frac3 > frac1)
    }

    @Test
    fun testBernoulliNumbers() {
        assertEquals(Frac(1, 1), bernoulli(0))
        assertEquals(Frac(-1, 2), bernoulli(1))
        assertEquals(Frac(1, 6), bernoulli(2))
        assertEquals(Frac(0, 1), bernoulli(3))
        assertEquals(Frac(-1, 30), bernoulli(4))
    }

    @Test
    fun testBinomialCoefficients() {
        assertEquals(1, binomial(5, 0))
        assertEquals(5, binomial(5, 1))
        assertEquals(10, binomial(5, 2))
        assertEquals(10, binomial(5, 3))
        assertEquals(5, binomial(5, 4))
        assertEquals(1, binomial(5, 5))
    }

    @Test
    fun testFaulhaber() {
        // This test will check the output of the faulhaber function for p = 0 to 9
        // Since faulhaber prints the result, we will capture the output and verify it
        val expectedOutputs = listOf(
            "0 : n",
            "1 : 1/2n^2 + 1/2n",
            "2 : 1/3n^3 + 1/2n^2 + 1/6n",
            "3 : 1/4n^4 + 1/2n^3 + 1/4n^2",
            "4 : 1/5n^5 + 1/2n^4 + 1/3n^3 - 1/30n",
            "5 : 1/6n^6 + 1/2n^5 + 5/12n^4 - 1/12n^2",
            "6 : 1/7n^7 + 1/2n^6 + 1/2n^5 - 1/6n^3 + 1/42n",
            "7 : 1/8n^8 + 1/2n^7 + 7/12n^6 - 7/24n^4 + 1/12n^2",
            "8 : 1/9n^9 + 1/2n^8 + 2/3n^7 - 7/15n^5 + 2/9n^3 - 1/30n",
            "9 : 1/10n^10 + 1/2n^9 + 3/4n^8 - 7/10n^6 + 1/2n^4 - 3/20n^2"
        )

        for (i in 0..9) {
            val output = captureOutput { faulhaber(i) }
            assertEquals(expectedOutputs[i], output.trim())
        }
    }

    private fun captureOutput(block: () -> Unit): String {
        val outputStream = java.io.ByteArrayOutputStream()
        val printStream = java.io.PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }
        return outputStream.toString()
    }
}
