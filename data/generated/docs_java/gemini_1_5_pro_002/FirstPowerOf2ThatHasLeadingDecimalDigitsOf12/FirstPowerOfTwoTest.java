import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FirstPowerOfTwoTest {

    @Test
    void testP12_1() {
        assertEquals(7, FirstPowerOfTwo.p(12, 1));
    }

    @Test
    void testP12_2() {
        assertEquals(80, FirstPowerOfTwo.p(12, 2));
    }

    @Test
    void testP123_45() {
        assertEquals(12710, FirstPowerOfTwo.p(123, 45));
    }

    @Test
    void testP123_12345() {
        assertEquals(114404, FirstPowerOfTwo.p(123, 12345));
    }

    @Test
    void testP123_678910() {
       assertEquals(6092690, FirstPowerOfTwo.p(123, 678910));
    }


    // Additional edge case tests

    @Test
    void testPL_0(){
        assertThrows(IllegalArgumentException.class, () -> FirstPowerOfTwo.p(12, 0));
    }

    @Test
    void testL_SingleDigit(){
        assertEquals(3, FirstPowerOfTwo.p(1, 1));
    }



}
