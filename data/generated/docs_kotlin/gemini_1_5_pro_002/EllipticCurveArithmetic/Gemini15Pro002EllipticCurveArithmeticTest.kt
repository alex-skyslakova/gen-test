import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import kotlin.math.abs

class PtTest {

    @Test
    fun testZero() {
        val p = Pt(1.0, 2.0)
        val zero = p.zero
        assertTrue(zero.isZero)
    }

    @Test
    fun testDbl() {
        val p = Pt(2.0, 5.0)
        val expected = Pt(-1.125, -9.71875).round() 
        assertEquals(expected, p.dbl().round())

        val zero = Pt(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        assertEquals(zero, zero.dbl())
    }

    @Test
    fun testUnaryMinus() {
        val p = Pt(2.0, 5.0)
        val expected = Pt(2.0, -5.0)
        assertEquals(expected, -p)
    }
    
    @Test
    fun testPlus_samePoint() {
        val p = Pt(2.0, 5.0)
        assertEquals(p.dbl().round(), (p + p).round())
    }

    @Test
    fun testPlus_differentPoints() {
        val a = Pt(1.0, 2.0)
        val b = Pt(2.0, 3.0)
        val expected = Pt(-0.5, -0.5).round()
        assertEquals(expected, (a + b).round())
    }


    @Test
    fun testPlus_zero() {
        val p = Pt(2.0, 5.0)
        val zero = p.zero
        assertEquals(p, p + zero)
        assertEquals(p, zero + p)
    }

    @Test
    fun testTimes() {
        val a = Pt(1.0, 2.0)
        val result = a * 5

        val expected = Pt(106.708,-1082.454).round()
        assertEquals(expected, result.round())



        val zero = a.zero
        assertEquals(zero, zero * 123)
    }

    @Test
    fun testToPt() {
        val y = 2.0
        val expected = Pt(1.259921, 2.0)
        assertEquals(expected, y.toPt())
    }

    fun Pt.round(): Pt {
        return Pt(String.format("%.3f", x).toDouble(), String.format("%.3f", y).toDouble())
    }

}

