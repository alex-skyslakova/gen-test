import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ABTest {

    @Test
    fun testValidInput() {
        assertEquals(4, calculateSum("2 2"))
        assertEquals(5, calculateSum("3 2"))
        assertEquals(0, calculateSum("-1000 1000"))
        assertEquals(-2000, calculateSum("-1000 -1000"))
        assertEquals(2000, calculateSum("1000 1000"))
    }

    @Test
    fun testInvalidInputFormat() {
        assertThrows(IllegalArgumentException::class.java) { calculateSum("2, 2") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("2 2 2") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("two two") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("2") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("2 ") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum(" 2") }
    }

    @Test
    fun testOutOfRangeInput() {
        assertThrows(IllegalArgumentException::class.java) { calculateSum("1001 0") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("0 1001") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("-1001 0") }
        assertThrows(IllegalArgumentException::class.java) { calculateSum("0 -1001") }
    }

    private fun calculateSum(input: String): Int {
        val r = Regex("""-?\d+[ ]+-?\d+""")
        if (!input.matches(r)) {
            throw IllegalArgumentException("Invalid input format")
        }
        val index = input.lastIndexOf(' ')
        val a = input.substring(0, index).trimEnd().toInt()
        val b = input.substring(index + 1).toInt()
        if (Math.abs(a) > 1000 || Math.abs(b) > 1000) {
            throw IllegalArgumentException("Numbers out of range")
        }
        return a + b
    }
}
