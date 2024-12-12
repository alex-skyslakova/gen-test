import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DepartmentNumbersTest {

    @Test
    fun testValidCombinations() {
        val expectedCombinations = listOf(
            Triple(2, 3, 7),
            Triple(2, 5, 5),
            Triple(2, 7, 3),
            Triple(4, 1, 7),
            Triple(4, 3, 5),
            Triple(4, 5, 3),
            Triple(4, 7, 1),
            Triple(6, 1, 5),
            Triple(6, 3, 3),
            Triple(6, 5, 1)
        )

        val actualCombinations = mutableListOf<Triple<Int, Int, Int>>()
        for (i in 2..6 step 2) {
            for (j in 1..7) {
                if (j == i) continue
                for (k in 1..7) {
                    if (k == i || k == j) continue
                    if (i + j + k != 12) continue
                    actualCombinations.add(Triple(i, j, k))
                }
            }
        }

        assertEquals(expectedCombinations.size, actualCombinations.size, "Number of valid combinations should match")
        assertTrue(expectedCombinations.containsAll(actualCombinations), "All expected combinations should be present")
        assertTrue(actualCombinations.containsAll(expectedCombinations), "All actual combinations should be expected")
    }
}
