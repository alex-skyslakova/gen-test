import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AbundantOddNumbersTest {

    @Test
    fun testDivisors() {
        assertEquals(listOf(1, 2, 3, 4, 6, 12), divisors(12))
        assertEquals(listOf(1, 2, 4, 5, 10, 20, 25, 50, 100), divisors(100))
        assertEquals(listOf(1, 2, 4, 8, 16, 32, 64), divisors(64))
        assertEquals(listOf(1, 3, 9, 27, 81), divisors(81))
        assertEquals(listOf(1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024), divisors(1024))
    }

    @Test
    fun testAbundantOdd() {
        // Test the first 25 abundant odd numbers
        val first25AbundantOddNumbers = mutableListOf<Int>()
        abundantOdd(1, 0, 25, false) { n, _ ->
            first25AbundantOddNumbers.add(n)
        }
        assertEquals(25, first25AbundantOddNumbers.size)
        assertTrue(first25AbundantOddNumbers.all { it % 2 != 0 })

        // Test the one thousandth abundant odd number
        val oneThousandthAbundantOddNumber = mutableListOf<Int>()
        abundantOdd(1, 0, 1000, true) { n, _ ->
            oneThousandthAbundantOddNumber.add(n)
        }
        assertEquals(1, oneThousandthAbundantOddNumber.size)
        assertEquals(1000, oneThousandthAbundantOddNumber.size)

        // Test the first abundant odd number greater than one billion
        val firstAbundantOddNumberAboveOneBillion = mutableListOf<Int>()
        abundantOdd((1e9 + 1).toInt(), 0, 1, true) { n, _ ->
            firstAbundantOddNumberAboveOneBillion.add(n)
        }
        assertEquals(1, firstAbundantOddNumberAboveOneBillion.size)
        assertTrue(firstAbundantOddNumberAboveOneBillion[0] > 1e9)
    }

    // Helper function to simulate the print behavior in the original function
    private fun abundantOdd(searchFrom: Int, countFrom: Int, countTo: Int, printOne: Boolean, action: (Int, Int) -> Unit): Int {
        var count = countFrom
        var n = searchFrom

        while (count < countTo) {
            val divs = divisors(n)
            val tot = divs.sum()
            if (tot > n) {
                count++
                if (!printOne || count >= countTo) {
                    action(n, tot)
                }
            }
            n += 2
        }

        return n
    }
}
