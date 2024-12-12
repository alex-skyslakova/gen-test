import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.BitSet

const val SIZE  = 32
const val RULE  = 90

fun ruleTest(x: Int) = (RULE and (1 shl (7 and x))) != 0

infix fun Boolean.shl(bitCount: Int) = (if (this) 1 else 0) shl bitCount

fun Boolean.toInt() = if (this) 1 else 0

fun evolve(s: BitSet) {
    val t = BitSet(SIZE)
    t[SIZE - 1] = ruleTest((s[0] shl 2) or (s[SIZE - 1] shl 1) or s[SIZE - 2].toInt())
    t[0] = ruleTest((s[1] shl 2) or (s[0] shl 1) or s[SIZE - 1].toInt())
    for (i in 1 until SIZE - 1) {
        t[i] = ruleTest((s[i + 1] shl 2) or (s[i] shl 1) or s[i - 1].toInt())
    }
    for (i in 0 until SIZE) s[i] = t[i]
}


class CellularAutomatonTest {

    @Test
    fun testRuleTest() {
        // Test all 8 possible 3-bit inputs for rule 90 (0b01011010)
        assertFalse(ruleTest(0b111)) // 7
        assertTrue(ruleTest(0b110))  // 6
        assertFalse(ruleTest(0b101)) // 5
        assertTrue(ruleTest(0b100))  // 4
        assertTrue(ruleTest(0b011))  // 3
        assertFalse(ruleTest(0b010)) // 2
        assertTrue(ruleTest(0b001))  // 1
        assertFalse(ruleTest(0b000)) // 0
    }


    @Test
    fun testEvolveSingleCenter() {
        val state = BitSet(SIZE)
        state.set(SIZE / 2)
        evolve(state)
        assertTrue(state[SIZE / 2 - 1])
        assertTrue(state[SIZE / 2 + 1])
        assertFalse(state[SIZE / 2])
        // Check wrapping for single cell at the edges.
        val leftEdge = BitSet(SIZE)
        leftEdge.set(0)
        evolve(leftEdge)
        assertTrue(leftEdge[1])
        assertTrue(leftEdge[SIZE - 1])

        val rightEdge = BitSet(SIZE)
        rightEdge.set(SIZE-1)
        evolve(rightEdge)
        assertTrue(rightEdge[SIZE - 2])
        assertTrue(rightEdge[0])

    }

    @Test
    fun testEvolveAllSet() {
        val state = BitSet(SIZE)
        state.set(0, SIZE) // Set all bits
        evolve(state)
        for (i in 0 until SIZE) {
            assertFalse(state[i])
        }
    }

    @Test
    fun testEvolveAlternating() {
        val state = BitSet(SIZE)
        for (i in 0 until SIZE step 2) {
            state.set(i)
        }
        evolve(state)
        for (i in 0 until SIZE) {
            assertTrue(state[i])
        }
    }
}

