import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class JacobiSymbolTest {

    @Test
    fun testJacobiSymbol_BasicCases() {
        // (a | n) = 1
        assertEquals(1, jacobi(1, 1), "(1 | 1) should be 1")
        assertEquals(1, jacobi(1, 3), "(1 | 3) should be 1")
        assertEquals(1, jacobi(1, 5), "(1 | 5) should be 1")
        assertEquals(1, jacobi(1, 7), "(1 | 7) should be 1")
        assertEquals(1, jacobi(1, 11), "(1 | 11) should be 1")

        // (a | n) = -1
        assertEquals(-1, jacobi(2, 3), "(2 | 3) should be -1")
        assertEquals(-1, jacobi(3, 5), "(3 | 5) should be -1")
        assertEquals(-1, jacobi(5, 7), "(5 | 7) should be -1")
        assertEquals(-1, jacobi(7, 11), "(7 | 11) should be -1")

        // (a | n) = 0
        assertEquals(0, jacobi(0, 3), "(0 | 3) should be 0")
        assertEquals(0, jacobi(0, 5), "(0 | 5) should be 0")
        assertEquals(0, jacobi(0, 7), "(0 | 7) should be 0")
        assertEquals(0, jacobi(0, 11), "(0 | 11) should be 0")
    }

    @Test
    fun testJacobiSymbol_CompositeN() {
        // (a | n) = 1 for composite n
        assertEquals(1, jacobi(1, 9), "(1 | 9) should be 1")
        assertEquals(1, jacobi(1, 25), "(1 | 25) should be 1")
        assertEquals(1, jacobi(1, 49), "(1 | 49) should be 1")

        // (a | n) = -1 for composite n
        assertEquals(-1, jacobi(2, 9), "(2 | 9) should be -1")
        assertEquals(-1, jacobi(3, 25), "(3 | 25) should be -1")
        assertEquals(-1, jacobi(5, 49), "(5 | 49) should be -1")

        // (a | n) = 0 for composite n
        assertEquals(0, jacobi(9, 9), "(9 | 9) should be 0")
        assertEquals(0, jacobi(25, 25), "(25 | 25) should be 0")
        assertEquals(0, jacobi(49, 49), "(49 | 49) should be 0")
    }

    @Test
    fun testJacobiSymbol_LargeValues() {
        // (a | n) = 1 for large values
        assertEquals(1, jacobi(123456789, 987654321), "(123456789 | 987654321) should be 1")

        // (a | n) = -1 for large values
        assertEquals(-1, jacobi(987654321, 123456789), "(987654321 | 123456789) should be -1")

        // (a | n) = 0 for large values
        assertEquals(0, jacobi(987654321, 987654321), "(987654321 | 987654321) should be 0")
    }

    @Test
    fun testJacobiSymbol_EdgeCases() {
        // (a | n) = 1 for edge cases
        assertEquals(1, jacobi(1, Int.MAX_VALUE), "(1 | Int.MAX_VALUE) should be 1")

        // (a | n) = -1 for edge cases
        assertEquals(-1, jacobi(2, Int.MAX_VALUE), "(2 | Int.MAX_VALUE) should be -1")

        // (a | n) = 0 for edge cases
        assertEquals(0, jacobi(0, Int.MAX_VALUE), "(0 | Int.MAX_VALUE) should be 0")
    }

    @Test
    fun testJacobiSymbol_InvalidInput() {
        // n <= 0
        assertThrows(AssertionError::class.java) { jacobi(1, 0) }
        assertThrows(AssertionError::class.java) { jacobi(1, -1) }

        // n is even
        assertThrows(AssertionError::class.java) { jacobi(1, 2) }
        assertThrows(AssertionError::class.java) { jacobi(1, 4) }
    }
}
