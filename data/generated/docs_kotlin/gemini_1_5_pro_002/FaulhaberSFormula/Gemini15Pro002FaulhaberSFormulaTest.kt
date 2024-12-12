import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FaulhaberSFormulaTest {

    @Test
    fun testGcd() {
        assertEquals(5, gcd(10, 5))
        assertEquals(5, gcd(5, 10))
        assertEquals(1, gcd(10, 3))
        assertEquals(2, gcd(6, 4))
        assertEquals(10, gcd(10, 0))
        assertEquals(10, gcd(0, 10))
        assertEquals(0, gcd(0, 0))
    }

    @Test
    fun testFrac() {
        assertEquals(Frac(1, 2), Frac(2, 4))
        assertEquals(Frac(-1, 2), Frac(2, -4))
        assertEquals(Frac(0, 1), Frac(0, 4))
        assertEquals("1/2", Frac(1, 2).toString())
        assertEquals("1", Frac(1, 1).toString())
        assertEquals("0", Frac(0, 1).toString())
        assertEquals("-1/2", Frac(-1, 2).toString())

        assertEquals(Frac(3, 2) , Frac(1,2) + Frac(1,1))
        assertEquals(Frac(1,2), Frac(1,1) - Frac(1,2))
        assertEquals(Frac(-1,2), -Frac(1,2))
        assertEquals(Frac(1,4), Frac(1,2) * Frac(1,2))
        assertEquals(Frac(1, 2), Frac(1, 2).abs())
        assertEquals(Frac(1, 2), Frac(-1, -2).abs())


        assertTrue(Frac(1, 2) < Frac(1,1))
        assertTrue(Frac(1, 2) > Frac(0,1))
        assertTrue(Frac(1, 2) == Frac(1,2))

        assertEquals(0.5, Frac(1,2).toDouble(), 0.0001)
    }

    @Test
    fun testBernoulli() {
        assertEquals(Frac(1,1), bernoulli(0))
        assertEquals(Frac(-1,2), bernoulli(1))
        assertEquals(Frac(1,6), bernoulli(2))
        assertEquals(Frac(0,1), bernoulli(3))
        assertEquals(Frac(-1,30), bernoulli(4))
    }


    @Test
    fun testBinomial() {
        assertEquals(1, binomial(0,0))
        assertEquals(1, binomial(1,0))
        assertEquals(1, binomial(1,1))
        assertEquals(10, binomial(5,2))
        assertEquals(1, binomial(5,0))
        assertEquals(5, binomial(5,1))
        assertEquals(10, binomial(5,3))
        assertEquals(5, binomial(5,4))
        assertEquals(1, binomial(5,5))
    }

    @Test
    fun testFaulhaberOutput() {
        // Redirect output to capture the printed string
        val outputStream = System.out
        val byteArrayOutputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(byteArrayOutputStream))

        main(arrayOf())
        
        val outputString = byteArrayOutputStream.toString("UTF8")
        
        // Reset the system output
        System.setOut(outputStream)
        val expectedOutput = """
0 : n
1 : 1/2n - 1/2n^2 + n
2 : 1/6n - 1/2n^2 + 1/3n^3
3 : 1/4n^2 - 1/2n^3 + 1/4n^4
4 : -1/30n + 1/3n^3 - 1/2n^4 + 1/5n^5
5 : -1/12n^2 + 5/12n^4 - 1/2n^5 + 1/6n^6
6 : 1/42n - 1/6n^3 + 1/2n^5 - 1/2n^6 + 1/7n^7
7 : 1/12n^2 - 7/24n^4 + 7/12n^6 - 1/2n^7 + 1/8n^8
8 : -1/30n + 2/9n^3 - 7/15n^5 + 2/3n^7 - 1/2n^8 + 1/9n^9
9 : -1/20n^2 + 3/10n^4 - 7/10n^6 + 3/2n^8 - 1/2n^9 + 1/10n^10
""".trimIndent()

        assertEquals(expectedOutput, outputString.trim())

    }
}



