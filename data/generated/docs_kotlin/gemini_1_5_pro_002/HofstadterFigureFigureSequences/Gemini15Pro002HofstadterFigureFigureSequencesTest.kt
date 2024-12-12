import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HofstadterFigureFigureSequencesTest {

    @Test
    fun testFfrFirstTen() {
        val expected = listOf(1, 3, 7, 12, 18, 26, 35, 45, 56, 69)
        for (i in 1..10) {
            assertEquals(expected[i - 1], ffr(i))
        }
    }

    @Test
    fun testFfsBasic() {
        assertEquals(2, ffs(1))
        assertEquals(4, ffs(2))
        assertEquals(5, ffs(3))
        assertEquals(6, ffs(4))
        assertEquals(8, ffs(5))
        assertEquals(9, ffs(6))
    }


    @Test
    fun testCombinedSequences() {
        val first40R = (1..40).map { ffr(it) }.toSet()
        val first960S = (1..960).map { ffs(it) }.toSet()
        val combined = first40R + first960S

        assertEquals(1000, combined.size)
        for(i in 1..1000) {
            assertTrue(combined.contains(i))
        }
    }

    @Test
    fun testLargeInput() {
        // Testing with larger inputs to ensure no maximum value issues
        ffr(100)
        ffs(1000)
    }


    @Test
    fun testCacheEfficiency() { // Testing for potential caching improvements
        val startTime = System.currentTimeMillis()
        ffr(500) // First call to populate cache
        val firstCallTime = System.currentTimeMillis() - startTime

        val startTime2 = System.currentTimeMillis()
        ffr(500) // Second call should be faster due to caching
        val secondCallTime = System.currentTimeMillis() - startTime2
        assertTrue(secondCallTime < firstCallTime) // Asserting second call is faster

        val startTime3 = System.currentTimeMillis()
        ffs(800) // First call to ffs with different value
        val firstCallTimeffs = System.currentTimeMillis() - startTime3

         val startTime4 = System.currentTimeMillis()
        ffs(800) // Second call should be faster due to caching
        val secondCallTimeffs = System.currentTimeMillis() - startTime4
        assertTrue(secondCallTimeffs < firstCallTimeffs) // Asserting second call is faster
    }
}
