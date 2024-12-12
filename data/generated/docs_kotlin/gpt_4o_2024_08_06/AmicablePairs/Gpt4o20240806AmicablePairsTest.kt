import kotlin.test.Test
import kotlin.test.assertEquals

class AmicablePairsTest {

    @Test
    fun testSumProperDivisors() {
        assertEquals(0, sumProperDivisors(1))
        assertEquals(1, sumProperDivisors(2))
        assertEquals(3, sumProperDivisors(4))
        assertEquals(7, sumProperDivisors(8))
        assertEquals(15, sumProperDivisors(16))
        assertEquals(284, sumProperDivisors(220)) // Known amicable number
        assertEquals(220, sumProperDivisors(284)) // Known amicable number
    }

    @Test
    fun testAmicablePairsBelow20000() {
        val expectedPairs = listOf(
            Pair(220, 284),
            Pair(1184, 1210),
            Pair(2620, 2924),
            Pair(5020, 5564),
            Pair(6232, 6368),
            Pair(10744, 10856),
            Pair(12285, 14595),
            Pair(17296, 18416)
        )

        val sum = IntArray(20000) { sumProperDivisors(it) }
        val actualPairs = mutableListOf<Pair<Int, Int>>()

        for (n in 2..19998) {
            val m = sum[n]
            if (m > n && m < 20000 && n == sum[m]) {
                actualPairs.add(Pair(n, m))
            }
        }

        assertEquals(expectedPairs, actualPairs)
    }
}
