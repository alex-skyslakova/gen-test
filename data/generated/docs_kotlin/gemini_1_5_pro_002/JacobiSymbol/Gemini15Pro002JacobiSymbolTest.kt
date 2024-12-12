import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class JacobiSymbolTest {

    @Test
    fun testJacobiSymbol_basicCases() {
        assertEquals(1, jacobi(1, 5))
        assertEquals(-1, jacobi(2, 5))
        assertEquals(0, jacobi(5, 5))
        assertEquals(1, jacobi(4, 5)) 
    }

    @Test
    fun testJacobiSymbol_primeN() {
        assertEquals(1, jacobi(1, 7))
        assertEquals(1, jacobi(2, 7))
        assertEquals(-1, jacobi(3, 7))
        assertEquals(-1, jacobi(5, 7))
        assertEquals(1, jacobi(6, 7))
        assertEquals(0, jacobi(7, 7))
        assertEquals(1, jacobi(4, 7))
    }


    @Test
    fun testJacobiSymbol_compositeN() {
        assertEquals(-1, jacobi(2, 15))
        assertEquals(0, jacobi(3, 15))
        assertEquals(1, jacobi(1, 15))
        assertEquals(1, jacobi(4, 15))
        assertEquals(-1, jacobi(7, 15))
        assertEquals(1, jacobi(2, 21))
        assertEquals(0, jacobi(3, 21))
        assertEquals(1, jacobi(21, 5))

    }
    
    @Test
    fun testJacobiSymbol_largeNumbers() {
        assertEquals(1, jacobi(12345, 67891)) // Example with larger numbers
        assertEquals(-1, jacobi(54321, 98765))
    }

    @Test
    fun testJacobiSymbol_negativeA() {
        assertEquals(-1, jacobi(-1, 5))
        assertEquals(1, jacobi(-2, 7)) // Example with negative 'a'
    }


    private fun jacobi(A: Int, N: Int): Int {
       // The provided jacobi function from the problem description would be pasted here
       // during actual testing. Omitted here to follow instructions.
        return 0 // Placeholder return, replace with the actual function in testing.
    }
}
