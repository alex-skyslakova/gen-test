import org.junit.jupiter.api.Test
import java.util.ArrayDeque
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HailstoneSequenceTest {

    @Test
    fun `test hailstone sequence for 1`() {
        val result = hailstone(1)
        val expected = ArrayDeque<Int>().apply { add(1) }
        assertEquals(expected, result)
    }

    @Test
    fun `test hailstone sequence for 2`() {
        val result = hailstone(2)
        val expected = ArrayDeque<Int>().apply { add(2); add(1) }
        assertEquals(expected, result)
    }

    @Test
    fun `test hailstone sequence for 3`() {
        val result = hailstone(3)
        val expected = ArrayDeque<Int>().apply { add(3); add(10); add(5); add(16); add(8); add(4); add(2); add(1) }
        assertEquals(expected, result)
    }

    @Test
    fun `test hailstone sequence for 4`() {
        val result = hailstone(4)
        val expected = ArrayDeque<Int>().apply { add(4); add(2); add(1) }
        assertEquals(expected, result)
    }

    @Test
    fun `test hailstone sequence for 27`() {
        val result = hailstone(27)
        val expectedStart = listOf(27, 82, 41, 124)
        val expectedEnd = listOf(8, 4, 2, 1)
        assertEquals(expectedStart, result.take(4))
        assertEquals(expectedEnd, result.takeLast(4))
        assertEquals(112, result.size)
    }

    @Test
    fun `test longest hailstone sequence under 100000`() {
        var longestHail: ArrayDeque<Int>? = null
        var maxLength = 0
        for (x in 1..99999) {
            val hail = hailstone(x)
            if (hail.size > maxLength) {
                maxLength = hail.size
                longestHail = hail
            }
        }
        assertNotNull(longestHail)
        assertEquals(77031, longestHail!!.first)
        assertEquals(351, longestHail.size)
    }
}
