import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountingInFactorsTest {

    @Test
    public void testCountInFactorsForPrimeNumbers() {
        assertEquals("2", CountingInFactors.countInFactors(2));
        assertEquals("3", CountingInFactors.countInFactors(3));
        assertEquals("5", CountingInFactors.countInFactors(5));
        assertEquals("7", CountingInFactors.countInFactors(7));
        assertEquals("11", CountingInFactors.countInFactors(11));
    }

    @Test
    public void testCountInFactorsForCompositeNumbers() {
        assertEquals("2 x 2", CountingInFactors.countInFactors(4));
        assertEquals("2 x 3", CountingInFactors.countInFactors(6));
        assertEquals("2 x 2 x 2", CountingInFactors.countInFactors(8));
        assertEquals("3 x 3", CountingInFactors.countInFactors(9));
        assertEquals("2 x 5", CountingInFactors.countInFactors(10));
    }

    @Test
    public void testCountInFactorsForLargeCompositeNumbers() {
        assertEquals("2 x 2 x 2 x 2 x 2 x 67", CountingInFactors.countInFactors(2144));
        assertEquals("2 x 2 x 2 x 5 x 5 x 5", CountingInFactors.countInFactors(1000));
        assertEquals("2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2", CountingInFactors.countInFactors(1024));
    }

    @Test
    public void testCountInFactorsForOne() {
        assertEquals("1", CountingInFactors.countInFactors(1));
    }

    @Test
    public void testCountInFactorsForLargePrimeNumbers() {
        assertEquals("99991", CountingInFactors.countInFactors(99991));
        assertEquals("99997", CountingInFactors.countInFactors(99997));
    }

    @Test
    public void testCountInFactorsForEdgeCases() {
        assertEquals("2 x 2 x 3 x 3 x 5 x 7 x 11 x 13", CountingInFactors.countInFactors(180180));
        assertEquals("2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2 x 2", CountingInFactors.countInFactors(1048576));
    }
}
