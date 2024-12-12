import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DammAlgorithmTest {

    @Test
    fun testValidDammChecksum() {
        assertTrue(damm("5724"))
        assertTrue(damm("112946"))
    }

    @Test
    fun testInvalidDammChecksum() {
        assertFalse(damm("5727"))
        assertFalse(damm("112949"))
    }

    @Test
    fun testSingleDigitInput() {
        assertTrue(damm("0"))
        assertTrue(damm("1"))
        assertTrue(damm("2"))
        assertTrue(damm("3"))
        assertTrue(damm("4"))
        assertTrue(damm("5"))
        assertTrue(damm("6"))
        assertTrue(damm("7"))
        assertTrue(damm("8"))
        assertTrue(damm("9"))
    }

    @Test
    fun testEmptyInput() {
        assertFalse(damm(""))
    }

    @Test
    fun testNonNumericInput() {
        assertThrows(NumberFormatException::class.java) {
            damm("abc")
        }
    }

    @Test
    fun testMixedNumericAndNonNumericInput() {
        assertThrows(NumberFormatException::class.java) {
            damm("123a")
        }
    }

    @Test
    fun testLargeNumberInput() {
        assertTrue(damm("1234567890123456789012345678901234567890"))
    }

    @Test
    fun testSingleDigitError() {
        assertFalse(damm("5725")) // Single digit error in the number 5724
    }

    @Test
    fun testAdjacentTranspositionError() {
        assertFalse(damm("5742")) // Adjacent transposition error in the number 5724
    }
}
