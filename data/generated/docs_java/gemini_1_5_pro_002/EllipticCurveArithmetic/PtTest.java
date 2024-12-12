import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PtTest {

    @Test
    void testZero() {
        Pt zero = Pt.zero();
        assertTrue(zero.isZero());
    }

    @Test
    void testFromY() {
        Pt p = Pt.fromY(2);
        assertEquals(1.0, p.x, 1e-9);
        assertEquals(2.0, p.y, 1e-9);
    }

    @Test
    void testDbl() {
        Pt p = Pt.fromY(2);
        Pt doubled = p.dbl();
        assertEquals(-3.0, doubled.x, 1e-9);
        assertEquals(-2.0, doubled.y, 1e-9);

        Pt zero = Pt.zero();
        assertSame(zero, zero.dbl());

    }
    
    @Test
    void testNeg() {
        Pt p = Pt.fromY(2);
        Pt neg = p.neg();
        assertEquals(1.0, neg.x, 1e-9);
        assertEquals(-2.0, neg.y, 1e-9);

    }

    @Test
    void testPlus_samePoints() {
        Pt a = Pt.fromY(2);
        Pt sum = a.plus(a);
        assertEquals(-3.0, sum.x, 1e-9);
        assertEquals(-2.0, sum.y, 1e-9);

    }



    @Test
    void testPlus_differentPoints() {
        Pt a = Pt.fromY(1);
        Pt b = Pt.fromY(2);
        Pt sum = a.plus(b);
        assertEquals(-1.148, sum.x, 1e-3); 
        assertEquals(-1.805, sum.y, 1e-3);

    }

    @Test
    void testPlus_zero() {
        Pt a = Pt.fromY(2);
        Pt zero = Pt.zero();
        assertSame(a, a.plus(zero));
        assertSame(a, zero.plus(a));
        assertSame(zero, zero.plus(zero));

    }

    @Test
    void testMult() {
        Pt a = Pt.fromY(1);
        Pt product = a.mult(12345);
        // These values are precalculated using the provided code. Adjust precision if needed
        assertEquals(272381.170, product.x, 0.001);
        assertEquals(4477850.285, product.y, 0.001);
        
        Pt zero = Pt.zero();
        assertSame(zero, zero.mult(5));

        Pt p = Pt.fromY(2);
        assertEquals(p, p.mult(1));

    }

    @Test
    void testIsZero(){
        Pt zero = Pt.zero();
        assertTrue(zero.isZero());

        Pt notZero = Pt.fromY(2);
        assertFalse(notZero.isZero());

    }

    @Test
    void testToString_Zero(){
        assertEquals("Zero", Pt.zero().toString());

    }


    @Test
    void testToString_NonZero(){
        Pt p = Pt.fromY(2);
        assertEquals("(1.000,2.000)", p.toString());

    }



}
