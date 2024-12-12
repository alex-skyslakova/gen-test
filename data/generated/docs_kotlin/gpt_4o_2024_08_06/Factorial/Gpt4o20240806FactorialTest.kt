import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows

class FactorialTest {

    @Test
    fun testFactorialIterativeZero() {
        assertEquals(1L, facti(0))
    }

    @Test
    fun testFactorialIterativeOne() {
        assertEquals(1L, facti(1))
    }

    @Test
    fun testFactorialIterativePositiveNumber() {
        assertEquals(120L, facti(5))
        assertEquals(3628800L, facti(10))
        assertEquals(2432902008176640000L, facti(20))
    }

    @Test
    fun testFactorialIterativeNegativeNumber() {
        assertThrows(IllegalArgumentException::class.java) {
            facti(-1)
        }
    }

    @Test
    fun testFactorialRecursiveZero() {
        assertEquals(1L, factr(0))
    }

    @Test
    fun testFactorialRecursiveOne() {
        assertEquals(1L, factr(1))
    }

    @Test
    fun testFactorialRecursivePositiveNumber() {
        assertEquals(120L, factr(5))
        assertEquals(3628800L, factr(10))
        assertEquals(2432902008176640000L, factr(20))
    }

    @Test
    fun testFactorialRecursiveNegativeNumber() {
        assertThrows(IllegalArgumentException::class.java) {
            factr(-1)
        }
    }
}
