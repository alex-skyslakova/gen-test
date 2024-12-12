import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class AbundantOddNumbersTest {

    @Test
    public void testFirst25AbundantOddNumbers() {
        List<Integer> expectedNumbers = List.of(945, 1575, 2205, 2835, 3465, 4095, 4725, 5355, 5775, 5985, 6435, 6615, 6825, 7245, 7425, 7875, 8085, 8415, 8505, 8925, 9555, 9765, 10395, 10515, 10605);
        List<Integer> expectedSums = List.of(975, 1965, 2520, 3360, 3828, 4890, 5460, 6045, 6435, 6825, 7560, 7560, 8190, 8190, 8190, 9450, 9450, 9450, 9450, 10395, 10395, 10395, 11340, 11340, 11340);

        List<Integer> actualNumbers = new ArrayList<>();
        List<Integer> actualSums = new ArrayList<>();

        AbundantOddNumbers.abundantOdd(1, 100000, 25, false, actualNumbers, actualSums);

        assertEquals(expectedNumbers, actualNumbers);
        assertEquals(expectedSums, actualSums);
    }

    @Test
    public void testThousandthAbundantOddNumber() {
        List<Integer> actualNumbers = new ArrayList<>();
        List<Integer> actualSums = new ArrayList<>();

        AbundantOddNumbers.abundantOdd(1, 2500000, 1000, true, actualNumbers, actualSums);

        assertEquals(33550335, actualNumbers.get(999)); // 1000th element (0-indexed)
        assertEquals(33550336, actualSums.get(999)); // 1000th element (0-indexed)
    }

    @Test
    public void testFirstAbundantOddNumberOverOneBillion() {
        List<Integer> actualNumbers = new ArrayList<>();
        List<Integer> actualSums = new ArrayList<>();

        AbundantOddNumbers.abundantOdd(1000000001, 2147483647, 1, false, actualNumbers, actualSums);

        assertEquals(2016960001, actualNumbers.get(0));
        assertEquals(2016960002, actualSums.get(0));
    }
}
