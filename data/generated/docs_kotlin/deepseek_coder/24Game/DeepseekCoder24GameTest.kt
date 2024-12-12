import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

class Game24Test {

    @Test
    fun testApplyOperatorAddition() {
        val result = '+'.applyOperator(3.0f, 4.0f)
        assertEquals(7.0f, result)
    }

    @Test
    fun testApplyOperatorSubtraction() {
        val result = '-'.applyOperator(3.0f, 4.0f)
        assertEquals(1.0f, result)
    }

    @Test
    fun testApplyOperatorMultiplication() {
        val result = '*'.applyOperator(3.0f, 4.0f)
        assertEquals(12.0f, result)
    }

    @Test
    fun testApplyOperatorDivision() {
        val result = '/'.applyOperator(3.0f, 4.0f)
        assertEquals(4.0f / 3.0f, result)
    }

    @Test
    fun testTally() {
        val digits = listOf(1, 2, 3, 4)
        val result = tally(digits)
        assertEquals(1107296256L, result)
    }

    @Test
    fun testCorrectExpression() {
        val input = "4*3*2*1"
        val cin = Scanner(input)
        val s = Stack<Float>()
        var total = 0L
        val digits = listOf(4, 3, 2, 1)

        for (c in cin.nextLine()) {
            when (c) {
                in '0'..'9' -> {
                    val d = c - '0'
                    total += (1 shl (d * 5)).toLong()
                    s += d.toFloat()
                }
                else ->
                    if ("+/-*".indexOf(c) != -1) {
                        s += c.applyOperator(s.pop(), s.pop())
                    }
            }
        }

        assertEquals(tally(digits), total)
        assertEquals(24.0f, s.peek())
    }

    @Test
    fun testIncorrectExpression() {
        val input = "4*3*2+1"
        val cin = Scanner(input)
        val s = Stack<Float>()
        var total = 0L
        val digits = listOf(4, 3, 2, 1)

        for (c in cin.nextLine()) {
            when (c) {
                in '0'..'9' -> {
                    val d = c - '0'
                    total += (1 shl (d * 5)).toLong()
                    s += d.toFloat()
                }
                else ->
                    if ("+/-*".indexOf(c) != -1) {
                        s += c.applyOperator(s.pop(), s.pop())
                    }
            }
        }

        assertEquals(tally(digits), total)
        assertNotEquals(24.0f, s.peek())
    }

    @Test
    fun testDifferentDigits() {
        val input = "4*3*2*1"
        val cin = Scanner(input)
        val s = Stack<Float>()
        var total = 0L
        val digits = listOf(4, 3, 2, 5) // Different digits

        for (c in cin.nextLine()) {
            when (c) {
                in '0'..'9' -> {
                    val d = c - '0'
                    total += (1 shl (d * 5)).toLong()
                    s += d.toFloat()
                }
                else ->
                    if ("+/-*".indexOf(c) != -1) {
                        s += c.applyOperator(s.pop(), s.pop())
                    }
            }
        }

        assertNotEquals(tally(digits), total)
    }

    @Test
    fun testInvalidOperator() {
        val input = "4*3*2^1" // Using '^' which is not allowed
        val cin = Scanner(input)
        val s = Stack<Float>()
        var total = 0L
        val digits = listOf(4, 3, 2, 1)

        for (c in cin.nextLine()) {
            when (c) {
                in '0'..'9' -> {
                    val d = c - '0'
                    total += (1 shl (d * 5)).toLong()
                    s += d.toFloat()
                }
                else ->
                    if ("+/-*".indexOf(c) != -1) {
                        s += c.applyOperator(s.pop(), s.pop())
                    }
            }
        }

        assertEquals(tally(digits), total)
        assertNotEquals(24.0f, s.peek())
    }
}
