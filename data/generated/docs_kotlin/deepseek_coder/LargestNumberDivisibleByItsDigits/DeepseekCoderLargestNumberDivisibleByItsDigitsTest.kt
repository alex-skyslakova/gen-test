import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LargestNumberDivisibleByItsDigitsTest {

    @Test
    fun testDivByAll() {
        // Test cases for divByAll function
        assertTrue(135.divByAll("135".toList()))
        assertFalse(123.divByAll("123".toList())) // 123 is not divisible by 2
        assertTrue(1236.divByAll("1236".toList()))
        assertFalse(1234.divByAll("1234".toList())) // 1234 is not divisible by 3
    }

    @Test
    fun testMainLogic() {
        // Test the main logic by capturing the output
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        main(arrayOf())

        val output = outputStream.toString().trim()
        assertEquals("Largest decimal number is 9867312", output)
    }

    @Test
    fun testInvalidCases() {
        // Test cases for invalid numbers
        assertFalse(102.divByAll("102".toList())) // contains '0'
        assertFalse(125.divByAll("125".toList())) // contains '5'
        assertFalse(111.divByAll("111".toList())) // digits are not unique
        assertFalse(120.divByAll("120".toList())) // contains '0'
    }

    @Test
    fun testEdgeCases() {
        // Test edge cases
        assertTrue(1.divByAll("1".toList()))
        assertFalse(0.divByAll("0".toList())) // division by zero
        assertFalse(10.divByAll("10".toList())) // contains '0'
        assertFalse(123456789.divByAll("123456789".toList())) // not all digits divide the number
    }
}
