import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class Game24Test {

    @Test
    fun testSolve24_simple() {
        assertTrue(solve24(intArrayOf(4, 1, 8, 7))) // (1+8-4)*7
        assertTrue(solve24(intArrayOf(1, 5, 5, 5))) // 5*(5-1/5)
        assertTrue(solve24(intArrayOf(6, 1, 1, 2))) // 6 / (1/2+1) = 4 != 24
    }



    @Test
    fun testSolve24_noSolution() {
        assertFalse(solve24(intArrayOf(1, 1, 1, 1)))
    }

    @Test
    fun testSolve24_withZero() {
        assertTrue(solve24(intArrayOf(0, 4, 6, 7))) // (6+7-4)*4
        assertTrue(solve24(intArrayOf(0, 1, 2, 3))) // (1+2)*3*4
    }

    @Test
    fun testEvalExpr_basicOperations() {
        val numExpr = Expr(op = OpType.NUM, value = 5)
        assertEquals(Frac(5, 1), evalExpr(numExpr))

        val addExpr = Expr(op = OpType.ADD, left = numExpr, right = numExpr)
        assertEquals(Frac(10, 1), evalExpr(addExpr))

        val subExpr = Expr(op = OpType.SUB, left = numExpr, right = numExpr)
        assertEquals(Frac(0, 1), evalExpr(subExpr))


        val mulExpr = Expr(op = OpType.MUL, left = numExpr, right = numExpr)
        assertEquals(Frac(25, 1), evalExpr(mulExpr))

        val divExpr = Expr(op = OpType.DIV, left = numExpr, right = Expr(op= OpType.NUM, value = 1))
        assertEquals(Frac(5, 1), evalExpr(divExpr))
    }


    @Test
    fun testEvalExpr_complexExpression() {

        val expr1 = Expr(op = OpType.NUM, value = 2)
        val expr2 = Expr(op = OpType.NUM, value = 3)
        val expr3 = Expr(op = OpType.NUM, value = 4)

        val mulExpr = Expr(op = OpType.MUL, left = expr2, right = expr3)
        val addExpr = Expr(op = OpType.ADD, left = expr1, right = mulExpr)

        assertEquals(Frac(14,1), evalExpr(addExpr))

    }

    @Test
    fun testEvalExpr_divisionByZero(){
        val expr1 = Expr(op = OpType.NUM, value = 5)
        val expr0 = Expr(op = OpType.NUM, value = 0)

        val divExpr = Expr(op = OpType.DIV, left = expr1, right = expr0)

        assertEquals(Frac(5*1, 0), evalExpr(divExpr))

    }
}

