import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LargestNumberDivisibleByItsDigitsTest {

    fun Int.divByAll(digits: List<Char>) = digits.all { this % (it - '0') == 0 }

    fun findLargestDecimal(): Int {
        val magic = 9 * 8 * 7
        val high = 9876432 / magic * magic
        for (i in high downTo magic step magic) {
            if (i % 10 == 0) continue            // can't end in '0'
            val s = i.toString()
            if ('0' in s || '5' in s) continue   // can't contain '0' or '5'
            val sd = s.toCharArray().distinct()
            if (sd.size != s.length) continue    // digits must be unique
            if (i.divByAll(sd)) {
                return i
            }
        }
        return -1 // Indicate no solution found
    }


    @Test
    fun testExample() {
        val num = 135
        val s = num.toString()
        val sd = s.toCharArray().distinct()
        assertTrue(num.divByAll(sd))
    }

    @Test
    fun testLargestDecimal() {
        assertEquals(9867312, findLargestDecimal())
    }

    @Test
    fun testNoZero() {
        val num = 102
        val s = num.toString()
        val sd = s.toCharArray().distinct()
        assertFalse(num.divByAll(sd))

    }

    @Test
    fun testNoFiveWithEvenLastDigit(){
        val num = 2456
        val s = num.toString()
        val sd = s.toCharArray().distinct()
        assertFalse(num.divByAll(sd))

    }

        @Test
    fun testDuplicateDigits(){
        val num = 1223
        val s = num.toString()
        val sd = s.toCharArray().distinct()
        assertFalse(num.divByAll(sd))

    }


    @Test
    fun testDivisibleByAllDigits_true() {
        val num = 321
        val digits = num.toString().toCharArray().distinct()
        assertTrue(num.divByAll(digits))
    }

     @Test
    fun testDivisibleByAllDigits_false() {
        val num = 328 // Not divisible by 3
        val digits = num.toString().toCharArray().distinct()
        assertFalse(num.divByAll(digits))
    }



}
