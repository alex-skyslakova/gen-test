import org.junit.Assert.assertEquals
import org.junit.Test

class AmicablePairsTest {

    @Test
    fun testSumProperDivisors() {
        // Test cases for sumProperDivisors function
        assertEquals(0, sumProperDivisors(0))
        assertEquals(0, sumProperDivisors(1))
        assertEquals(1, sumProperDivisors(2))
        assertEquals(1, sumProperDivisors(3))
        assertEquals(3, sumProperDivisors(4))
        assertEquals(1, sumProperDivisors(5))
        assertEquals(6, sumProperDivisors(6))
        assertEquals(1, sumProperDivisors(7))
        assertEquals(7, sumProperDivisors(8))
        assertEquals(4, sumProperDivisors(9))
        assertEquals(8, sumProperDivisors(10))
        assertEquals(1, sumProperDivisors(11))
        assertEquals(15, sumProperDivisors(12))
        assertEquals(1, sumProperDivisors(13))
        assertEquals(9, sumProperDivisors(14))
        assertEquals(10, sumProperDivisors(15))
        assertEquals(21, sumProperDivisors(16))
        assertEquals(1, sumProperDivisors(17))
        assertEquals(17, sumProperDivisors(18))
        assertEquals(1, sumProperDivisors(19))
        assertEquals(22, sumProperDivisors(20))
    }

    @Test
    fun testAmicablePairs() {
        // Test cases for finding amicable pairs
        val sum = IntArray(20000) { sumProperDivisors(it) }
        val amicablePairs = mutableListOf<Pair<Int, Int>>()

        for (n in 2..19998) {
            val m = sum[n]
            if (m > n && m < 20000 && n == sum[m]) {
                amicablePairs.add(Pair(n, m))
            }
        }

        // Expected amicable pairs below 20,000
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

        assertEquals(expectedPairs.size, amicablePairs.size)
        for (pair in expectedPairs) {
            assert(amicablePairs.contains(pair))
        }
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
}
