import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class DammAlgorithmTest {

    @Test
    fun testValidChecksum() {
        // Valid checksums
        assertTrue(damm("5724"))
        assertTrue(damm("112946"))
    }

    @Test
    fun testInvalidChecksum() {
        // Invalid checksums
        assertFalse(damm("5727"))
        assertFalse(damm("112949"))
    }

    @Test
    fun testSingleDigitError() {
        // Single digit error should be detected
        assertFalse(damm("5725")) // Changed last digit from 4 to 5
        assertFalse(damm("112947")) // Changed last digit from 6 to 7
    }

    @Test
    fun testAdjacentTranspositionError() {
        // Adjacent transposition error should be detected
        assertFalse(damm("5274")) // Transposed 7 and 2
        assertFalse(damm("112964")) // Transposed 4 and 6
    }

    @Test
    fun testEmptyString() {
        // Edge case: empty string
        assertTrue(damm("")) // Should be valid as no digits to process
    }

    @Test
    fun testSingleDigitString() {
        // Edge case: single digit string
        assertFalse(damm("5")) // Single digit, not a valid checksum
    }
}
