import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JacobiSymbolTest {

    @Test
    void testJacobiSymbol_basicCases() {
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 5));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(2, 5));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(3, 5));
        assertEquals(1, JacobiSymbol.jacobiSymbol(4, 5));
        assertEquals(0, JacobiSymbol.jacobiSymbol(5, 5));

        assertEquals(0, JacobiSymbol.jacobiSymbol(15,15));
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 1));

    }

    @Test
    void testJacobiSymbol_largerNumbers() {
        assertEquals(1, JacobiSymbol.jacobiSymbol(2, 21));
        assertEquals(-1, JacobiSymbol.jacobiSymbol(5, 21));
        assertEquals(1, JacobiSymbol.jacobiSymbol(4, 25));
        assertEquals(1, JacobiSymbol.jacobiSymbol(2, 101));

    }

    @Test
    void testJacobiSymbol_kGreaterThanN() {
        assertEquals(1, JacobiSymbol.jacobiSymbol(7, 5));  // 7 % 5 = 2, same as (2|5) which is -1
        assertEquals(-1, JacobiSymbol.jacobiSymbol(12,5));
    }

    @Test
    void testJacobiSymbol_evenN() {
        assertThrows(IllegalArgumentException.class, () -> JacobiSymbol.jacobiSymbol(1, 4));
        assertThrows(IllegalArgumentException.class, () -> JacobiSymbol.jacobiSymbol(3, 6));
        assertThrows(IllegalArgumentException.class, () -> JacobiSymbol.jacobiSymbol(5, 10));
    }

    @Test
    void testJacobiSymbol_negativeK(){
        assertThrows(IllegalArgumentException.class, () -> JacobiSymbol.jacobiSymbol(-1, 5));
        assertThrows(IllegalArgumentException.class, () -> JacobiSymbol.jacobiSymbol(-3, 7));


    }
    @Test
    void testJacobiSymbol_kZero(){
                assertEquals(0, JacobiSymbol.jacobiSymbol(0,5));
                assertEquals(0, JacobiSymbol.jacobiSymbol(0,17));
    }


    @Test
    void testJacobiSymbol_nOne() {
        assertEquals(1, JacobiSymbol.jacobiSymbol(1, 1));
        assertEquals(1, JacobiSymbol.jacobiSymbol(2, 1));
        assertEquals(1, JacobiSymbol.jacobiSymbol(3, 1));
    }



}

