import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LoopsWithMultipleRangesTest {

    @Test
    void testLoopsWithMultipleRanges() {
        long x = 5;
        long y = -5;
        long z = -2;
        long one = 1;
        long three = 3;
        long seven = 7;

        List<Long> expectedJList = new ArrayList<>(List.of(
                -3L, 0L, 3L, 6L, 9L, 12L, 15L, 18L, 21L, 24L, 27L,
                -7L, -2L, 3L,
                555L, 556L, 557L, 558L, 559L, 560L,
                22L, 19L, 16L, 13L, 10L, 7L, 4L, 1L, -2L, -5L, -8L, -11L, -14L, -17L, -20L, -23L, -26L,
                1927L, 1928L, 1929L, 1930L, 1931L, 1932L, 1933L, 1934L, 1935L, 1936L, 1937L, 1938L, 1939L,
                5L, 3L, 1L, -1L, -3L, -5L,
                161051L, 161052L
        ));

        List<Long> actualJList = new ArrayList<>();
        for (long j = -three; j <= LoopsWithMultipleRanges.pow(3, 3); j += three) actualJList.add(j);
        for (long j = -seven; j <= seven; j += x) actualJList.add(j);
        for (long j = 555; j <= 550 - y; j += 1) actualJList.add(j);
        for (long j = 22; j >= -28; j += -three) actualJList.add(j);
        for (long j = 1927; j <= 1939; j += 1) actualJList.add(j);
        for (long j = x; j >= y; j += z) actualJList.add(j);
        for (long j = LoopsWithMultipleRanges.pow(11, x); j <= LoopsWithMultipleRanges.pow(11, x) + one; j += 1) actualJList.add(j);


        assertIterableEquals(expectedJList, actualJList);

       long expectedSum = 169277L;
       long expectedProd = -147425088L;


        long actualSum = 0;
        long actualProd = 1;
        List<Long> prodList = new ArrayList<>();

        for (long j : actualJList) {
            actualSum += Math.abs(j);
            if (Math.abs(actualProd) < LoopsWithMultipleRanges.pow(2, 27) && j != 0) {
                prodList.add(j);
                actualProd *= j;
            }
        }

        assertEquals(expectedSum, actualSum);
        assertEquals(expectedProd, actualProd);

    }
}
