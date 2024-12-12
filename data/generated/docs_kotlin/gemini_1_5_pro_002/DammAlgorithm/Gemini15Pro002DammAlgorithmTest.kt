import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DammAlgorithmTest {

    @Test
    fun testValidNumbers() {
        assertTrue(damm("5724"))
        assertTrue(damm("112946"))
        assertTrue(damm("3"))
        assertTrue(damm("0"))
         assertTrue(damm("12345678905")) // Longer valid number


    }

    @Test
    fun testInvalidNumbers() {
        assertFalse(damm("5727"))
        assertFalse(damm("112949"))
        assertFalse(damm("1"))
        assertFalse(damm("12345678904")) // Longer invalid number
        assertFalse(damm("")) // Empty string
        assertFalse(damm("a")) // Non-digit character
    }


    @Test
    fun testSingleDigitError() {
        // Original valid number
        val validNumber = "5724"
        assertTrue(damm(validNumber))

        // Introduce single digit errors
        for (i in validNumber.indices) {
            for (j in 0..9) {
                val alteredNumber = validNumber.toCharArray()
                alteredNumber[i] = (j + '0'.toInt()).toChar()
                if (String(alteredNumber) != validNumber) {
                   assertFalse(damm(String(alteredNumber)))
                }
            }
        }
    }

    @Test
    fun testAdjacentTranspositionError() {
        // Original valid number
        val validNumber = "5724"
        assertTrue(damm(validNumber))

         // Introduce adjacent transposition errors
        for (i in 0 until validNumber.length - 1) {
            val alteredNumber = validNumber.toCharArray()
            val temp = alteredNumber[i]
            alteredNumber[i] = alteredNumber[i + 1]
            alteredNumber[i + 1] = temp
            assertFalse(damm(String(alteredNumber)))

        }
    }



}
