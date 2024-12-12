import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class CyclotomicPolynomialTest {

    @BeforeEach
    fun setUp() {
        // Clear the computed cache before each test
        COMPUTED.clear()
    }

    @Test
    fun testCyclotomicPolynomialForSmallN() {
        // Test the first few cyclotomic polynomials
        assertEquals(Polynomial(1, 1, -1, 0), cyclotomicPolynomial(1))
        assertEquals(Polynomial(1, 1, 1, 0), cyclotomicPolynomial(2))
        assertEquals(Polynomial(1, 2, 1, 1, 1, 0), cyclotomicPolynomial(3))
        assertEquals(Polynomial(1, 1, 1, 0), cyclotomicPolynomial(4))
        assertEquals(Polynomial(1, 2, 1, 1, 1, 0), cyclotomicPolynomial(5))
        assertEquals(Polynomial(1, 1, 1, 0), cyclotomicPolynomial(6))
        assertEquals(Polynomial(1, 2, 1, 1, 1, 0), cyclotomicPolynomial(7))
        assertEquals(Polynomial(1, 1, 1, 0), cyclotomicPolynomial(8))
        assertEquals(Polynomial(1, 2, 1, 1, 1, 0), cyclotomicPolynomial(9))
        assertEquals(Polynomial(1, 1, 1, 0), cyclotomicPolynomial(10))
    }

    @Test
    fun testHasCoefficientAbs() {
        val polynomial = Polynomial(1, 2, -1, 1, 1, 0)
        assertTrue(polynomial.hasCoefficientAbs(1))
        assertFalse(polynomial.hasCoefficientAbs(2))
    }

    @Test
    fun testMainFunctionality() {
        // Capture the output of the main function
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        main()

        val output = outputStream.toString()
        val lines = output.split("\n")

        // Check the first 30 cyclotomic polynomials
        for (i in 1..30) {
            val expected = cyclotomicPolynomial(i).toString()
            assertTrue(lines.contains("CP[$i] = $expected"))
        }

        // Check the first 10 cyclotomic polynomials with n or -n as a coefficient
        for (i in 1..10) {
            assertTrue(lines.contains("CP[$i] has coefficient with magnitude = $i"))
        }
    }

    @Test
    fun testGetDivisors() {
        assertEquals(listOf(1, 2, 3, 6), getDivisors(6))
        assertEquals(listOf(1, 2, 4, 8), getDivisors(8))
        assertEquals(listOf(1, 3, 9), getDivisors(9))
    }

    @Test
    fun testGetFactors() {
        assertEquals(mapOf(2 to 1), getFactors(2))
        assertEquals(mapOf(2 to 3), getFactors(8))
        assertEquals(mapOf(3 to 2), getFactors(9))
        assertEquals(mapOf(2 to 1, 3 to 1), getFactors(6))
    }
}
