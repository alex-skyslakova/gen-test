import org.junit.Assert.assertEquals
import org.junit.Test

class HofstadterConway10000SequenceTest {

    @Test
    fun testInitialSequenceValues() {
        val sequence = generateSequence(10)
        assertEquals(1, sequence[1])
        assertEquals(1, sequence[2])
        assertEquals(2, sequence[3])
        assertEquals(2, sequence[4])
        assertEquals(3, sequence[5])
        assertEquals(4, sequence[6])
        assertEquals(4, sequence[7])
        assertEquals(4, sequence[8])
        assertEquals(5, sequence[9])
        assertEquals(5, sequence[10])
    }

    @Test
    fun testSequenceGrowth() {
        val sequence = generateSequence(20)
        assertEquals(1, sequence[11])
        assertEquals(6, sequence[12])
        assertEquals(6, sequence[13])
        assertEquals(6, sequence[14])
        assertEquals(6, sequence[15])
        assertEquals(8, sequence[16])
        assertEquals(8, sequence[17])
        assertEquals(8, sequence[18])
        assertEquals(8, sequence[19])
        assertEquals(8, sequence[20])
    }

    @Test
    fun testMaximaBetweenPowersOfTwo() {
        val sequence = generateSequence(1 shl 20 + 1)
        var pow2 = 1
        var p = 1
        var max = sequence[1].toDouble()
        for (n in 2 until sequence.size) {
            val r = sequence[n].toDouble() / n
            if (r > max) max = r
            if (n == pow2 * 2) {
                when (p) {
                    1 -> assertEquals(1.0, max, 0.000001)
                    2 -> assertEquals(0.666667, max, 0.000001)
                    3 -> assertEquals(0.666667, max, 0.000001)
                    4 -> assertEquals(0.6, max, 0.000001)
                    5 -> assertEquals(0.571429, max, 0.000001)
                    6 -> assertEquals(0.555556, max, 0.000001)
                    7 -> assertEquals(0.555556, max, 0.000001)
                    8 -> assertEquals(0.55, max, 0.000001)
                    9 -> assertEquals(0.55, max, 0.000001)
                    10 -> assertEquals(0.55, max, 0.000001)
                    11 -> assertEquals(0.55, max, 0.000001)
                    12 -> assertEquals(0.55, max, 0.000001)
                    13 -> assertEquals(0.55, max, 0.000001)
                    14 -> assertEquals(0.55, max, 0.000001)
                    15 -> assertEquals(0.55, max, 0.000001)
                    16 -> assertEquals(0.55, max, 0.000001)
                    17 -> assertEquals(0.55, max, 0.000001)
                    18 -> assertEquals(0.55, max, 0.000001)
                    19 -> assertEquals(0.55, max, 0.000001)
                    20 -> assertEquals(0.55, max, 0.000001)
                }
                pow2 *= 2
                p++
                max = r
            }
        }
    }

    @Test
    fun testMallowsNumber() {
        val sequence = generateSequence(1 shl 20 + 1)
        var prize = 0
        for (n in sequence.size - 1 downTo 1) {
            if (sequence[n].toDouble() / n >= 0.55) {
                prize = n
                break
            }
        }
        assertEquals(1489, prize)
    }

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
}
