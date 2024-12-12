import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HofstadterFigureFigureSequencesTest {

    @Test
    fun testFirstTenValuesOfR() {
        val expectedR = listOf(1, 3, 7, 12, 18, 26, 35, 45, 56, 69)
        val actualR = (1..10).map { ffr(it) }
        assertEquals(expectedR, actualR)
    }

    @Test
    fun testFirstTenValuesOfS() {
        val expectedS = listOf(2, 4, 5, 6, 8, 9, 10, 11, 13, 14)
        val actualS = (1..10).map { ffs(it) }
        assertEquals(expectedS, actualS)
    }

    @Test
    fun testFirst40RPlusFirst960S() {
        val first40R = (1..40).map { ffr(it) }
        val first960S = (1..960).map { ffs(it) }
        val combinedSet = first40R.toSet() + first960S.toSet()

        // Check if the combined set contains exactly the integers from 1 to 1000
        val expectedSet = (1..1000).toSet()
        assertEquals(expectedSet, combinedSet)
    }

    @Test
    fun testRAndSUniqueness() {
        val first40R = (1..40).map { ffr(it) }
        val first960S = (1..960).map { ffs(it) }

        // Check if there are any duplicates in R and S
        val rSet = first40R.toSet()
        val sSet = first960S.toSet()

        assertEquals(first40R.size, rSet.size)
        assertEquals(first960S.size, sSet.size)
    }

    @Test
    fun testRAndSNoOverlap() {
        val first40R = (1..40).map { ffr(it) }
        val first960S = (1..960).map { ffs(it) }

        // Check if there is any overlap between R and S
        val overlap = first40R.intersect(first960S.toSet())
        assertTrue(overlap.isEmpty())
    }

    @Test
    fun testRAndSCoverage() {
        val first40R = (1..40).map { ffr(it) }
        val first960S = (1..960).map { ffs(it) }

        // Check if the combined set covers all integers from 1 to 1000
        val combinedSet = first40R.toSet() + first960S.toSet()
        val expectedSet = (1..1000).toSet()

        assertEquals(expectedSet, combinedSet)
    }
}
