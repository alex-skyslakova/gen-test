import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.BitSet

class CellularAutomatonTest {

    private val size = 32
    private val lines = size / 2

    private fun ruleTest(rule: Int, x: Int) = (rule and (1 shl (7 and x))) != 0

    private infix fun Boolean.shl(bitCount: Int) = (if (this) 1 else 0) shl bitCount

    private fun Boolean.toInt() = if (this) 1 else 0

    private fun evolve(s: BitSet, rule: Int) {
        val t = BitSet(size)
        t[size - 1] = ruleTest(rule, (s[0] shl 2) or (s[size - 1] shl 1) or s[size - 2].toInt())
        t[0] = ruleTest(rule, (s[1] shl 2) or (s[0] shl 1) or s[size - 1].toInt())
        for (i in 1 until size - 1) {
            t[i] = ruleTest(rule, (s[i + 1] shl 2) or (s[i] shl 1) or s[i - 1].toInt())
        }
        for (i in 0 until size) s[i] = t[i]
    }

    private fun show(s: BitSet): String {
        val sb = StringBuilder()
        for (i in size - 1 downTo 0) sb.append(if (s[i]) "*" else " ")
        return sb.toString()
    }

    @Test
    fun testInitialState() {
        val state = BitSet(size)
        state.set(lines)
        val expected = "                *               "
        assertEquals(expected, show(state))
    }

    @Test
    fun testEvolveRule90() {
        val state = BitSet(size)
        state.set(lines)
        val rule = 90
        evolve(state, rule)
        val expected = "               **               "
        assertEquals(expected, show(state))
    }

    @Test
    fun testEvolveRule30() {
        val state = BitSet(size)
        state.set(lines)
        val rule = 30
        evolve(state, rule)
        val expected = "               **               "
        assertEquals(expected, show(state))
    }

    @Test
    fun testMultipleEvolutions() {
        val state = BitSet(size)
        state.set(lines)
        val rule = 90
        repeat(3) {
            evolve(state, rule)
        }
        val expected = "             *    *             "
        assertEquals(expected, show(state))
    }

    @Test
    fun testWrapAround() {
        val state = BitSet(size)
        state.set(0)
        state.set(size - 1)
        val rule = 90
        evolve(state, rule)
        val expected = " *                             *"
        assertEquals(expected, show(state))
    }
}
