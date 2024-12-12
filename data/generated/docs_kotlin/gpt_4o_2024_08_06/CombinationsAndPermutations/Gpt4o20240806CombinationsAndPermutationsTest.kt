import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigInteger

class CombinationsAndPermutationsTest {

    @Test
    fun testPermutationsExact() {
        val expectedResults = listOf(
            BigInteger.ONE, // 1 P 0
            BigInteger.ONE, // 2 P 0
            BigInteger.valueOf(6), // 3 P 1
            BigInteger.valueOf(12), // 4 P 1
            BigInteger.valueOf(60), // 5 P 1
            BigInteger.valueOf(120), // 6 P 2
            BigInteger.valueOf(210), // 7 P 2
            BigInteger.valueOf(336), // 8 P 2
            BigInteger.valueOf(504), // 9 P 3
            BigInteger.valueOf(720), // 10 P 3
            BigInteger.valueOf(990), // 11 P 3
            BigInteger.valueOf(1320) // 12 P 4
        )
        for (n in 1..12) {
            val k = n / 3
            assertEquals(expectedResults[n - 1], perm(n, k))
        }
    }

    @Test
    fun testCombinationsExact() {
        val expectedResults = listOf(
            BigInteger.valueOf(1), // 10 C 3
            BigInteger.valueOf(1140), // 20 C 6
            BigInteger.valueOf(5311735), // 30 C 10
            BigInteger.valueOf(15890700), // 40 C 13
            BigInteger.valueOf(50063860), // 50 C 16
            BigInteger.valueOf(1182645815640) // 60 C 20
        )
        for ((index, n) in (10..60 step 10).withIndex()) {
            val k = n / 3
            assertEquals(expectedResults[index], comb(n, k))
        }
    }

    @Test
    fun testPermutationsApproximate() {
        val expectedLengths = listOf(
            1, // 5 P 1
            2, // 50 P 16
            3, // 500 P 166
            4, // 1000 P 333
            5, // 5000 P 1666
            6  // 15000 P 5000
        )
        val na = intArrayOf(5, 50, 500, 1000, 5000, 15000)
        for ((index, n) in na.withIndex()) {
            val k = n / 3
            val result = perm(n, k).toString()
            assert(result.length >= expectedLengths[index])
        }
    }

    @Test
    fun testCombinationsApproximate() {
        val expectedLengths = listOf(
            3, // 100 C 33
            4, // 200 C 66
            5, // 300 C 100
            6, // 400 C 133
            7, // 500 C 166
            8, // 600 C 200
            9, // 700 C 233
            10, // 800 C 266
            11, // 900 C 300
            12  // 1000 C 333
        )
        for ((index, n) in (100..1000 step 100).withIndex()) {
            val k = n / 3
            val result = comb(n, k).toString()
            assert(result.length >= expectedLengths[index])
        }
    }
}
