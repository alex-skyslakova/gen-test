import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class LoopsWithMultipleRangesTest {

    @Test
    public void testLoopWithMultipleRanges() {
        long x = 5;
        long y = -5;
        long z = -2;
        long one = 1;
        long three = 3;
        long seven = 7;

        List<Long> expectedJList = new ArrayList<>();
        for ( long j = -three     ; j <= Math.pow(3, 3)        ; j += three )  expectedJList.add(j);
        for ( long j = -seven     ; j <= seven                 ; j += x )      expectedJList.add(j);
        for ( long j = 555        ; j <= 550-y                 ; j += 1 )      expectedJList.add(j);
        for ( long j = 22         ; j >= -28                   ; j += -three ) expectedJList.add(j);
        for ( long j = 1927       ; j <= 1939                  ; j += 1 )      expectedJList.add(j);
        for ( long j = x          ; j >= y                     ; j += z )      expectedJList.add(j);
        for ( long j = Math.pow(11, x) ; j <= Math.pow(11, x) + one ; j += 1 ) expectedJList.add(j);

        long expectedSum = 0;
        long expectedProd = 1;
        List<Long> expectedProdList = new ArrayList<>();

        for ( long j : expectedJList ) {
            expectedSum += Math.abs(j);
            if ( Math.abs(expectedProd) < Math.pow(2, 27) && j != 0 ) {
                expectedProdList.add(j);
                expectedProd *= j;
            }            
        }

        // Run the actual code
        LoopsWithMultipleRanges.main(new String[]{});

        // Validate the results
        assertEquals(expectedSum, LoopsWithMultipleRanges.sum, "Sum should match expected value");
        assertEquals(expectedProd, LoopsWithMultipleRanges.prod, "Product should match expected value");
    }
}
