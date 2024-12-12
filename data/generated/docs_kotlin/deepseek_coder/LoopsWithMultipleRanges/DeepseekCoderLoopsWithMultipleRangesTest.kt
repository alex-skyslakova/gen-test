import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.math.abs

class LoopsWithMultipleRangesTest {

    private var prod: Int = 0
    private var sum: Int = 0
    private val x = 5
    private val y = -5
    private val z = -2
    private val one = 1
    private val three = 3
    private val seven = 7
    private val p = 11 pow x

    @BeforeEach
    fun setUp() {
        prod = 1
        sum = 0
    }

    private fun process(j: Int) {
        sum += abs(j)
        if (abs(prod) < (1 shl 27) && j != 0) prod *= j
    }

    @Test
    fun testFirstLoopRange() {
        for (j in -three..(3 pow 3) step three) process(j)
        assertEquals(124, sum)
        assertEquals(-189, prod)
    }

    @Test
    fun testSecondLoopRange() {
        for (j in -seven..seven step x) process(j)
        assertEquals(17, sum)
        assertEquals(-105, prod)
    }

    @Test
    fun testThirdLoopRange() {
        for (j in 555..550 - y) process(j)
        assertEquals(555, sum)
        assertEquals(555, prod)
    }

    @Test
    fun testFourthLoopRange() {
        for (j in 22 downTo -28 step three) process(j)
        assertEquals(348, sum)
        assertEquals(-1452, prod)
    }

    @Test
    fun testFifthLoopRange() {
        for (j in 1927..1939) process(j)
        assertEquals(23136, sum)
        assertEquals(1927 * 1928 * 1929 * 1930 * 1931 * 1932 * 1933 * 1934 * 1935 * 1936 * 1937 * 1938 * 1939, prod)
    }

    @Test
    fun testSixthLoopRange() {
        for (j in x downTo y step -z) process(j)
        assertEquals(15, sum)
        assertEquals(-15, prod)
    }

    @Test
    fun testSeventhLoopRange() {
        for (j in p..p + one) process(j)
        assertEquals(265173, sum)
        assertEquals(265173, prod)
    }

    @Test
    fun testAllLoopRanges() {
        for (j in -three..(3 pow 3) step three) process(j)
        for (j in -seven..seven step x) process(j)
        for (j in 555..550 - y) process(j)
        for (j in 22 downTo -28 step three) process(j)
        for (j in 1927..1939) process(j)
        for (j in x downTo y step -z) process(j)
        for (j in p..p + one) process(j)
        assertEquals(265173 + 124 + 17 + 555 + 348 + 23136 + 15, sum)
        assertEquals(-189 * -105 * 555 * -1452 * 1927 * 1928 * 1929 * 1930 * 1931 * 1932 * 1933 * 1934 * 1935 * 1936 * 1937 * 1938 * 1939 * -15 * 265173, prod)
    }
}

infix fun Int.pow(e: Int): Int {
    if (e == 0) return 1
    var prod = this
    for (i in 2..e) {
        prod *= this
    }
    return prod
}
