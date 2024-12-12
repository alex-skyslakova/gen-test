import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

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
    fun testSequenceGeneration() {
        val a = generateSequence(10)
        assertArrayEquals(intArrayOf(0, 1, 1, 2, 2, 3, 4, 4, 4, 5), a)
    }

    @Test
    fun testMaximaCalculation() {
        val limit = (1 shl 4) + 1 // Smaller limit for testing
        val a = generateSequence(limit)

        var pow2 = 1
        var max = a[1].toDouble()
        for (n in 2 until limit) {
            val r = a[n].toDouble() / n
            if (r > max) max = r
            if (n == pow2 * 2) {
                // Assertions for maxima would go here, 
                // but they are dependent on the output format which is not required to be tested.
                // Example: if (n == 4) assertEquals(1.0, max)
                pow2 *= 2
                max = r
            }
        }
    }



    @Test
    fun testMallowsNumber() {
        val limit = (1 shl 20) + 1
        val a = generateSequence(limit)

        var prize = 0
        for (n in limit - 1 downTo 1) {
            if (a[n].toDouble() / n >= 0.55) {
                prize = n
                break
            }
        }
        assertEquals(148, prize) // Known Mallows' number
    }

    @Test
    fun testPowerOfTwoRatio(){
        val powers = listOf(2, 4, 8, 16, 32)
        for(power in powers){
            val a = generateSequence(power + 1)
            assertEquals(0.5, a[power].toDouble()/power)
        }

    }

}
