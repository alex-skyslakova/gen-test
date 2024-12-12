import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HofstadterFigureFigureSequencesTest {

    @Test
    fun testFfr() {
        val expectedR = listOf(1, 3, 7, 12, 18, 26, 35, 45, 56, 69)
        for (i in expectedR.indices) {
            assertEquals(expectedR[i], ffr(i + 1), "ffr(${i + 1}) should be ${expectedR[i]}")
        }
    }

    @Test
    fun testFfs() {
        val expectedS = listOf(2, 4, 5, 6, 8, 9, 10, 11, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24, 25, 27, 28, 29, 30, 31, 32, 33, 34, 36, 37, 38, 39, 40, 41, 42, 43, 44, 46, 47, 48)
        for (i in expectedS.indices) {
            assertEquals(expectedS[i], ffs(i + 1), "ffs(${i + 1}) should be ${expectedS[i]}")
        }
    }

    @Test
    fun testFirst40RAndFirst960SIncludeAllIntegersFrom1To1000() {
        val first40R = (1..40).map { ffr(it) }
        val first960S = (1..960).map { ffs(it) }
        val allIntegers = (1..1000).toSet()
        val combined = (first40R + first960S).toSet()
        assertEquals(allIntegers, combined, "The first 40 R values and first 960 S values should include all integers from 1 to 1000 exactly once")
    }
}
