import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EllipticCurveArithmeticTest {

    @Test
    fun testZeroPoint() {
        val zero = Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        assertTrue(zero.isZero)
    }

    @Test
    fun testPointAdditionWithZero() {
        val p = Pt(1.0, 2.0)
        val zero = Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        assertEquals(p, p + zero)
        assertEquals(p, zero + p)
    }

    @Test
    fun testPointDoubling() {
        val p = Pt(1.0, 2.0)
        val doubled = p.dbl()
        assertEquals(doubled, p + p)
    }

    @Test
    fun testPointAddition() {
        val p1 = Pt(1.0, 2.0)
        val p2 = Pt(2.0, 3.0)
        val result = p1 + p2
        // Check if the result is a valid point on the curve
        assertTrue(result.y * result.y == result.x * result.x * result.x + 0 * result.x + 7)
    }

    @Test
    fun testPointNegation() {
        val p = Pt(1.0, 2.0)
        val negated = -p
        assertEquals(Pt(1.0, -2.0), negated)
    }

    @Test
    fun testPointAdditionInverse() {
        val p1 = Pt(1.0, 2.0)
        val p2 = Pt(2.0, 3.0)
        val sum = p1 + p2
        val negatedSum = -sum
        assertEquals(Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY), sum + negatedSum)
    }

    @Test
    fun testPointMultiplication() {
        val p = Pt(1.0, 2.0)
        val multiplied = p * 3
        val expected = p + p + p
        assertEquals(expected, multiplied)
    }

    @Test
    fun testPointMultiplicationWithZero() {
        val p = Pt(1.0, 2.0)
        val zero = Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        assertEquals(zero, p * 0)
    }

    @Test
    fun testPointMultiplicationWithOne() {
        val p = Pt(1.0, 2.0)
        assertEquals(p, p * 1)
    }
}
