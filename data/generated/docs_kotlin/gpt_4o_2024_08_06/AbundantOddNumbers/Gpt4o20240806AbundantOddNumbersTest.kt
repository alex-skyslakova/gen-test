import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AbundantOddNumbersTest {

    @Test
    fun testDivisors() {
        assertEquals(listOf(1), divisors(1))
        assertEquals(listOf(1, 3), divisors(3))
        assertEquals(listOf(1, 2, 4), divisors(4))
        assertEquals(listOf(1, 3, 9), divisors(9))
        assertEquals(listOf(1, 2, 5, 10), divisors(10))
    }

    @Test
    fun testAbundantOddFirst25() {
        val expectedFirst25 = listOf(
            945, 1575, 2205, 2835, 3465, 4095, 4725, 5355, 5775, 5985,
            6435, 6615, 6825, 7245, 7425, 7875, 8085, 8415, 8505, 8925,
            9555, 9765, 10395, 10515, 10605
        )
        val result = mutableListOf<Int>()
        var n = 1
        var count = 0
        while (count < 25) {
            val divs = divisors(n)
            val tot = divs.sum()
            if (tot > n) {
                result.add(n)
                count++
            }
            n += 2
        }
        assertEquals(expectedFirst25, result)
    }

    @Test
    fun testAbundantOddThousandth() {
        val expectedThousandth = 2480625
        val result = abundantOdd(1, 0, 1000, false)
        assertEquals(expectedThousandth, result)
    }

    @Test
    fun testAbundantOddAboveOneBillion() {
        val expectedAboveOneBillion = 1000000001
        val result = abundantOdd((1e9 + 1).toInt(), 0, 1, false)
        assertEquals(expectedAboveOneBillion, result)
    }
}
