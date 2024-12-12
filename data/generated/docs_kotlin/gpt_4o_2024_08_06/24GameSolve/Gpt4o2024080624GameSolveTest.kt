import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Game24SolverTest {

    @Test
    fun testSolve24WithSolution() {
        val numbers = intArrayOf(8, 1, 6, 6)
        assertTrue(solve24(numbers), "Expected a solution for numbers: ${numbers.joinToString()}")
    }

    @Test
    fun testSolve24WithoutSolution() {
        val numbers = intArrayOf(1, 1, 1, 1)
        assertFalse(solve24(numbers), "Expected no solution for numbers: ${numbers.joinToString()}")
    }

    @Test
    fun testSolve24EdgeCase() {
        val numbers = intArrayOf(9, 9, 9, 9)
        assertFalse(solve24(numbers), "Expected no solution for numbers: ${numbers.joinToString()}")
    }

    @Test
    fun testEvalExprAddition() {
        val expr = Expr(OpType.ADD, Expr(value = 5), Expr(value = 3))
        val result = evalExpr(expr)
        assertEquals(Frac(8, 1), result, "Expected 5 + 3 to equal 8")
    }

    @Test
    fun testEvalExprSubtraction() {
        val expr = Expr(OpType.SUB, Expr(value = 10), Expr(value = 4))
        val result = evalExpr(expr)
        assertEquals(Frac(6, 1), result, "Expected 10 - 4 to equal 6")
    }

    @Test
    fun testEvalExprMultiplication() {
        val expr = Expr(OpType.MUL, Expr(value = 2), Expr(value = 3))
        val result = evalExpr(expr)
        assertEquals(Frac(6, 1), result, "Expected 2 x 3 to equal 6")
    }

    @Test
    fun testEvalExprDivision() {
        val expr = Expr(OpType.DIV, Expr(value = 8), Expr(value = 2))
        val result = evalExpr(expr)
        assertEquals(Frac(4, 1), result, "Expected 8 / 2 to equal 4")
    }

    @Test
    fun testEvalExprDivisionByZero() {
        val expr = Expr(OpType.DIV, Expr(value = 8), Expr(value = 0))
        val result = evalExpr(expr)
        assertEquals(Frac(8, 0), result, "Expected 8 / 0 to result in a fraction with zero denominator")
    }

    @Test
    fun testShowExpr() {
        val expr = Expr(OpType.ADD, Expr(value = 5), Expr(OpType.MUL, Expr(value = 3), Expr(value = 2)))
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        showExpr(expr, OpType.NUM, false)
        assertEquals("5 + 3 x 2", outputStream.toString().trim(), "Expected expression to be formatted as '5 + 3 x 2'")
    }
}
