import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LoopsWithMultipleRangesTest {

    @Test
    public void testSumCalculation() {
        LoopsWithMultipleRanges.main(new String[]{});
        assertEquals(348173, LoopsWithMultipleRanges.sum, "Sum should be 348,173");
    }

    @Test
    public void testProdCalculation() {
        LoopsWithMultipleRanges.main(new String[]{});
        assertEquals(-793618560, LoopsWithMultipleRanges.prod, "Prod should be -793,618,560");
    }

    @Test
    public void testJListGeneration() {
        LoopsWithMultipleRanges.main(new String[]{});
        List<Long> expectedJList = List.of(
                -3L, 0L, 3L, 6L, 9L, 12L, 15L, 18L, 21L, 24L, 27L,
                -7L, -2L, 3L,
                555L,
                22L, 19L, 16L, 13L, 10L, 7L, 4L, 1L, -2L, -5L, -8L, -11L, -14L, -17L, -20L, -23L, -26L,
                1927L, 1928L, 1929L, 1930L, 1931L, 1932L, 1933L, 1934L, 1935L, 1936L, 1937L, 1938L, 1939L,
                5L, 3L, 1L, -1L, -3L, -5L,
                161051L, 161052L
        );
        assertEquals(expectedJList, LoopsWithMultipleRanges.jList, "jList should match the expected values");
    }

    @Test
    public void testProdListGeneration() {
        LoopsWithMultipleRanges.main(new String[]{});
        List<Long> expectedProdList = List.of(
                -3L, 3L, 6L, 9L, 12L, 15L, 18L, 21L, 24L, 27L,
                -2L, 3L,
                22L, 19L, 16L, 13L, 10L, 7L, 4L, 1L, -2L, -5L, -8L, -11L, -14L, -17L, -20L, -23L, -26L,
                1927L, 1928L, 1929L, 1930L, 1931L, 1932L, 1933L, 1934L, 1935L, 1936L, 1937L, 1938L, 1939L,
                5L, 3L, 1L, -1L, -3L, -5L,
                161051L
        );
        assertEquals(expectedProdList, LoopsWithMultipleRanges.prodList, "prodList should match the expected values");
    }
}
