import org.junit.Test
import kotlin.test.assertEquals

class DepartmentNumbersTest {

    @Test
    fun testValidCombinations() {
        val expectedCombinations = listOf(
            listOf(2, 3, 7),
            listOf(2, 4, 6),
            listOf(2, 6, 4),
            listOf(2, 7, 3),
            listOf(4, 1, 7),
            listOf(4, 2, 6),
            listOf(4, 3, 5),
            listOf(4, 5, 3),
            listOf(4, 6, 2),
            listOf(4, 7, 1),
            listOf(6, 1, 5),
            listOf(6, 2, 4),
            listOf(6, 4, 2),
            listOf(6, 5, 1)
        )

        val actualCombinations = mutableListOf<List<Int>>()
        var count = 0

        for (i in 2..6 step 2) {
            for (j in 1..7) {
                if (j == i) continue
                for (k in 1..7) {
                    if (k == i || k == j) continue
                    if (i + j + k != 12) continue
                    actualCombinations.add(listOf(i, j, k))
                    count++
                }
            }
        }

        assertEquals(expectedCombinations.size, count)
        assertEquals(expectedCombinations, actualCombinations)
    }

    @Test
    fun testCountOfValidCombinations() {
        var count = 0

        for (i in 2..6 step 2) {
            for (j in 1..7) {
                if (j == i) continue
                for (k in 1..7) {
                    if (k == i || k == j) continue
                    if (i + j + k != 12) continue
                    count++
                }
            }
        }

        assertEquals(14, count)
    }

    @Test
    fun testPoliceNumberIsEven() {
        for (i in 2..6 step 2) {
            for (j in 1..7) {
                if (j == i) continue
                for (k in 1..7) {
                    if (k == i || k == j) continue
                    if (i + j + k != 12) continue
                    assertEquals(0, i % 2)
                }
            }
        }
    }

    @Test
    fun testNumbersAreUnique() {
        for (i in 2..6 step 2) {
            for (j in 1..7) {
                if (j == i) continue
                for (k in 1..7) {
                    if (k == i || k == j) continue
                    if (i + j + k != 12) continue
                    assertNotEquals(i, j)
                    assertNotEquals(i, k)
                    assertNotEquals(j, k)
                }
            }
        }
    }

    @Test
    fun testSumIsTwelve() {
        for (i in 2..6 step 2) {
            for (j in 1..7) {
                if (j == i) continue
                for (k in 1..7) {
                    if (k == i || k == j) continue
                    if (i + j + k != 12) continue
                    assertEquals(12, i + j + k)
                }
            }
        }
    }

    private fun assertNotEquals(a: Int, b: Int) {
        if (a == b) throw AssertionError("$a should not be equal to $b")
    }
}
