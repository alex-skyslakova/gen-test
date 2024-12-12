import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CountingInFactorsTest {

    @Test
    public void testCountInFactors() {
        assertEquals("1", CountingInFactors.countInFactors(1));
        assertEquals("2", CountingInFactors.countInFactors(2));
        assertEquals("3", CountingInFactors.countInFactors(3));
        assertEquals("2 x 2", CountingInFactors.countInFactors(4));
        assertEquals("5", CountingInFactors.countInFactors(5));
        assertEquals("2 x 3", CountingInFactors.countInFactors(6));
        assertEquals("7", CountingInFactors.countInFactors(7));
        assertEquals("2 x 2 x 2", CountingInFactors.countInFactors(8));
        assertEquals("3 x 3", CountingInFactors.countInFactors(9));
        assertEquals("2 x 5", CountingInFactors.countInFactors(10));
        assertEquals("2 x 2 x 2 x 2 x 2 x 67", CountingInFactors.countInFactors(2144));
        assertEquals("9991", CountingInFactors.countInFactors(9991)); // 9991 is prime
        assertEquals("2 x 4996", CountingInFactors.countInFactors(9992));
        assertEquals("3 x 3331", CountingInFactors.countInFactors(9993));
        assertEquals("2 x 4997", CountingInFactors.countInFactors(9994));
        assertEquals("5 x 1999", CountingInFactors.countInFactors(9995));
        assertEquals("2 x 2 x 2 x 3 x 3 x 3 x 37", CountingInFactors.countInFactors(9996));
        assertEquals("7 x 1427", CountingInFactors.countInFactors(9997));
        assertEquals("2 x 4999", CountingInFactors.countInFactors(9998));
        assertEquals("3 x 3333", CountingInFactors.countInFactors(9999));
        assertEquals("2 x 2 x 2 x 5 x 5 x 5", CountingInFactors.countInFactors(10000));
    }
}
