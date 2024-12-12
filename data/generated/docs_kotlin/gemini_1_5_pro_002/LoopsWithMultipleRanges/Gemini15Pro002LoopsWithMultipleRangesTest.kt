import kotlin.math.abs
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

infix fun Int.pow(e: Int): Int {
    if (e == 0) return 1
    var prod = this
    for (i in 2..e) {
        prod *= this
    }
    return prod
}

class LoopsWithMultipleRangesTest {

    @Test
    fun testLoopsWithMultipleRanges() {
        var prod = 1
        var sum = 0
        val x = 5
        val y = -5
        val z = -2
        val one = 1
        val three = 3
        val seven = 7
        val p = 11 pow x
        fun process(j: Int) {
            sum += abs(j)
            if (abs(prod) < (1 shl 27) && j != 0) prod *= j
        }

        for (j in -three..(3 pow 3) step three) process(j)
        for (j in -seven..seven step x) process(j)
        for (j in 555..550-y) process(j)
        for (j in 22 downTo -28 step three) process(j)
        for (j in 1927..1939) process(j)
        for (j in x downTo y step -z) process(j)  // corrected step to be positive as per PL/I logic
        for (j in p..p + one) process(j)


        assertEquals(4136, sum)
        assertEquals(-2073578240, prod)
    }


    @Test
    fun testPow() {
        assertEquals(1, 2 pow 0)
        assertEquals(8, 2 pow 3)
        assertEquals(27, 3 pow 3)
        assertEquals(1, 1 pow 10)

    }

    @Test
    fun testProcessZero(){
        var prod = 1
        var sum = 0
        fun process(j: Int) {
            sum += abs(j)
            if (abs(prod) < (1 shl 27) && j != 0) prod *= j
        }
        process(0)
        assertEquals(0, sum)
        assertEquals(1, prod)

    }

        @Test
    fun testProcessLargeProd(){
        var prod = Int.MAX_VALUE
        var sum = 0
        fun process(j: Int) {
            sum += abs(j)
            if (abs(prod) < (1 shl 27) && j != 0) prod *= j
        }
        process(2)
        assertEquals(2, sum)
        assertEquals(Int.MAX_VALUE, prod)

    }



}

