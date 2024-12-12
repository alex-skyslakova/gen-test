import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CountingInFactorsTest {

    @Test
    void testCountInFactors_1() {
        assertEquals("1", CountingInFactors.countInFactors(1));
    }

    @Test
    void testCountInFactors_2() {
        assertEquals("2", CountingInFactors.countInFactors(2));
    }

    @Test
    void testCountInFactors_3() {
        assertEquals("3", CountingInFactors.countInFactors(3));
    }

    @Test
    void testCountInFactors_4() {
        assertEquals("2 x 2", CountingInFactors.countInFactors(4));
    }

    @Test
    void testCountInFactors_5() {
        assertEquals("5", CountingInFactors.countInFactors(5));
    }

    @Test
    void testCountInFactors_6() {
        assertEquals("2 x 3", CountingInFactors.countInFactors(6));
    }

    @Test
    void testCountInFactors_7() {
        assertEquals("7", CountingInFactors.countInFactors(7));
    }

    @Test
    void testCountInFactors_8() {
        assertEquals("2 x 2 x 2", CountingInFactors.countInFactors(8));
    }

    @Test
    void testCountInFactors_9() {
        assertEquals("3 x 3", CountingInFactors.countInFactors(9));
    }

    @Test
    void testCountInFactors_10() {
        assertEquals("2 x 5", CountingInFactors.countInFactors(10));
    }
    
    @Test
    void testCountInFactors_12() {
        assertEquals("2 x 2 x 3", CountingInFactors.countInFactors(12));
    }

    @Test
    void testCountInFactors_2144() {
        assertEquals("2 x 2 x 2 x 2 x 2 x 67", CountingInFactors.countInFactors(2144));
    }

    @Test
    void testCountInFactors_9991() {
        assertEquals("9991", CountingInFactors.countInFactors(9991));
    }
    @Test
    void testCountInFactors_9997() {
        assertEquals("13 x 769", CountingInFactors.countInFactors(9997));
    }
     @Test
    void testCountInFactors_9998() {
        assertEquals("2 x 4999", CountingInFactors.countInFactors(9998));
    }


     @Test
    void testCountInFactors_9999() {
        assertEquals("3 x 3 x 11 x 101", CountingInFactors.countInFactors(9999));
    }

     @Test
    void testCountInFactors_10000() {
        assertEquals("2 x 2 x 2 x 2 x 5 x 5 x 5 x 5", CountingInFactors.countInFactors(10000));
    }


}
