import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.BitSet

class ElementaryCellularAutomatonTest {

    @Test
    fun testRuleTest() {
        // Rule 90 in binary is 01011010
        // Test all 8 possible combinations
        assertFalse(ruleTest(0b111)) // 0
        assertTrue(ruleTest(0b110))  // 1
        assertFalse(ruleTest(0b101)) // 0
        assertTrue(ruleTest(0b100))  // 1
        assertTrue(ruleTest(0b011))  // 1
        assertFalse(ruleTest(0b010)) // 0
        assertTrue(ruleTest(0b001))  // 1
        assertFalse(ruleTest(0b000)) // 0
    }

    @Test
    fun testEvolve() {
        val initialState = BitSet(SIZE)
        initialState.set(LINES)

        val expectedState1 = BitSet(SIZE)
        expectedState1.set(LINES - 1)
        expectedState1.set(LINES + 1)

        evolve(initialState)
        assertEquals(expectedState1, initialState)

        val expectedState2 = BitSet(SIZE)
        expectedState2.set(LINES - 2)
        expectedState2.set(LINES)
        expectedState2.set(LINES + 2)

        evolve(initialState)
        assertEquals(expectedState2, initialState)
    }

    @Test
    fun testShow() {
        val state = BitSet(SIZE)
        state.set(0)
        state.set(SIZE - 1)

        val expectedOutput = " *" + " ".repeat(SIZE - 2) + "*\n"
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        show(state)
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testMain() {
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString()
        assertTrue(output.contains("Rule 90:"))
        assertTrue(output.contains("*"))
        assertTrue(output.contains(" "))
    }
}
