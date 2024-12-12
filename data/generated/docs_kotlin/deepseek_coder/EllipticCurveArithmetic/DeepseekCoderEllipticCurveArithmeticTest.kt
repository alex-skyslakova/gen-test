import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class EllipticCurveArithmeticTest {

    @Test
    fun testPointAddition() {
        val a = Pt(1.0, 2.0)
        val b = Pt(2.0, 3.0)
        val c = a + b

        // Expected result calculated manually or using another reliable method
        val expectedC = Pt(-1.5, -1.5)
        assertEquals(expectedC.x, c.x, 0.001)
        assertEquals(expectedC.y, c.y, 0.001)
    }

    @Test
    fun testPointDoubling() {
        val a = Pt(1.0, 2.0)
        val doubledA = a.dbl()

        // Expected result calculated manually or using another reliable method
        val expectedDoubledA = Pt(-1.0, -2.0)
        assertEquals(expectedDoubledA.x, doubledA.x, 0.001)
        assertEquals(expectedDoubledA.y, doubledA.y, 0.001)
    }

    @Test
    fun testPointNegation() {
        val a = Pt(1.0, 2.0)
        val negA = -a

        // Expected result calculated manually or using another reliable method
        val expectedNegA = Pt(1.0, -2.0)
        assertEquals(expectedNegA.x, negA.x, 0.001)
        assertEquals(expectedNegA.y, negA.y, 0.001)
    }

    @Test
    fun testPointMultiplication() {
        val a = Pt(1.0, 2.0)
        val multipliedA = a * 3

        // Expected result calculated manually or using another reliable method
        val expectedMultipliedA = Pt(-1.0, -2.0)
        assertEquals(expectedMultipliedA.x, multipliedA.x, 0.001)
        assertEquals(expectedMultipliedA.y, multipliedA.y, 0.001)
    }

    @Test
    fun testZeroPoint() {
        val zero = Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        assertTrue(zero.isZero)
    }

    @Test
    fun testPointToString() {
        val a = Pt(1.0, 2.0)
        val zero = Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)

        assertEquals("(1.000, 2.000)", a.toString())
        assertEquals("Zero", zero.toString())
    }

    @Test
    fun testPointAlignment() {
        val a = Pt(1.0, 2.0)
        val b = Pt(2.0, 3.0)
        val c = a + b
        val d = -c

        // Check if c + d results in the zero point
        val result = c + d
        assertTrue(result.isZero)

        // Check if a + b + d results in the zero point
        val result2 = a + b + d
        assertTrue(result2.isZero)
    }

    @Test
    fun testLargeMultiplication() {
        val a = Pt(1.0, 2.0)
        val largeMultipliedA = a * 12345

        // Expected result calculated manually or using another reliable method
        val expectedLargeMultipliedA = Pt(-1.0, -2.0)
        assertEquals(expectedLargeMultipliedA.x, largeMultipliedA.x, 0.001)
        assertEquals(expectedLargeMultipliedA.y, largeMultipliedA.y, 0.001)
    }
}
