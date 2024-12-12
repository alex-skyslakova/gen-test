import kotlin.test.Test
import kotlin.test.assertEquals

class JacobiSymbolTest {

    @Test
    fun testJacobiSymbolBasicCases() {
        // Basic cases
        assertEquals(1, jacobi(1, 3), "Jacobi(1 | 3) should be 1")
        assertEquals(0, jacobi(0, 5), "Jacobi(0 | 5) should be 0")
        assertEquals(1, jacobi(2, 3), "Jacobi(2 | 3) should be 1")
        assertEquals(-1, jacobi(3, 5), "Jacobi(3 | 5) should be -1")
    }

    @Test
    fun testJacobiSymbolWithEvenN() {
        // Even n should not be allowed, so we expect an assertion error
        try {
            jacobi(3, 4)
            assert(false) { "Expected an assertion error for even n" }
        } catch (e: AssertionError) {
            // Expected
        }
    }

    @Test
    fun testJacobiSymbolWithNegativeA() {
        // Test with negative a
        assertEquals(-1, jacobi(-1, 3), "Jacobi(-1 | 3) should be -1")
        assertEquals(1, jacobi(-1, 5), "Jacobi(-1 | 5) should be 1")
    }

    @Test
    fun testJacobiSymbolWithLargeNumbers() {
        // Test with larger numbers
        assertEquals(1, jacobi(1001, 9907), "Jacobi(1001 | 9907) should be 1")
        assertEquals(-1, jacobi(123456, 789101), "Jacobi(123456 | 789101) should be -1")
    }

    @Test
    fun testJacobiSymbolWithPrimeN() {
        // When n is prime, Jacobi symbol equals Legendre symbol
        assertEquals(1, jacobi(4, 7), "Jacobi(4 | 7) should be 1")
        assertEquals(-1, jacobi(2, 11), "Jacobi(2 | 11) should be -1")
    }

    @Test
    fun testJacobiSymbolWithEdgeCases() {
        // Edge cases
        assertEquals(0, jacobi(0, 1), "Jacobi(0 | 1) should be 0")
        assertEquals(1, jacobi(1, 1), "Jacobi(1 | 1) should be 1")
    }
}
