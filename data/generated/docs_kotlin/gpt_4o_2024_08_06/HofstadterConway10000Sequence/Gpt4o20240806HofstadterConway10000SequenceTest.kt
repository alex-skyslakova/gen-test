import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HofstadterConway10000SequenceTest {

    private fun generateSequence(limit: Int): IntArray {
        val a = IntArray(limit)
        a[1] = 1
        a[2] = 1
        for (n in 3 until limit) {
            val p = a[n - 1]
            a[n] = a[p] + a[n - p]
        }
        return a
    }

    @Test
    fun testInitialValues() {
        val sequence = generateSequence(10)
        assertEquals(1, sequence[1], "a(1) should be 1")
        assertEquals(1, sequence[2], "a(2) should be 1")
    }

    @Test
    fun testSequenceValues() {
        val sequence = generateSequence(10)
        assertEquals(2, sequence[3], "a(3) should be 2")
        assertEquals(2, sequence[4], "a(4) should be 2")
        assertEquals(3, sequence[5], "a(5) should be 3")
        assertEquals(4, sequence[6], "a(6) should be 4")
        assertEquals(4, sequence[7], "a(7) should be 4")
        assertEquals(4, sequence[8], "a(8) should be 4")
        assertEquals(5, sequence[9], "a(9) should be 5")
    }

    @Test
    fun testMaximaBetweenPowersOfTwo() {
        val limit = (1 shl 20) + 1
        val sequence = generateSequence(limit)
        var pow2 = 1
        var p = 1
        var max = sequence[1].toDouble()
        for (n in 2 until limit) {
            val r = sequence[n].toDouble() / n
            if (r > max) max = r
            if (n == pow2 * 2) {
                assertTrue(max <= 0.55, "Max a(n)/n between powers of 2 should be <= 0.55")
                pow2 *= 2
                p++
                max = r
            }
        }
    }

    @Test
    fun testMallowsNumber() {
        val limit = (1 shl 20) + 1
        val sequence = generateSequence(limit)
        var prize = 0
        for (n in limit - 1 downTo 1) {
            if (sequence[n].toDouble() / n >= 0.55) {
                prize = n
                break
            }
        }
        assertEquals(220, prize, "Mallows' number should be 220")
    }
}
