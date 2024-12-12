import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AbundantOddNumbersTest {

    @Test
    void testSumList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(5);
        assertEquals(9, AbundantOddNumbers.sumList(list));

        list.clear();
        assertEquals(0, AbundantOddNumbers.sumList(list));

        list.add(1234);
        assertEquals(1234, AbundantOddNumbers.sumList(list));

    }

    @Test
    void testAbundantOdd_First25() {
        List<Integer> expectedFirst25 = List.of(945, 1575, 2205, 2835, 3465, 4095, 4725, 5355, 5775, 5985, 6435, 6615, 6825, 7245, 7425, 7875, 8085, 8415, 8505, 8925, 9135, 9555, 9765, 10395, 10935); // Precalculated expected values

        List<Integer> result = new ArrayList<>();
        AbundantOddNumbers.abundantOdd(1, 100000, 25, false);

        assertEquals(25, AbundantOddNumbers.result.size()); //check if we got 25 results, before asserting each one of them

        for (int i=0; i < AbundantOddNumbers.result.size(); i++)
            assertEquals(expectedFirst25.get(i), AbundantOddNumbers.result.get(i));
            
    }


    @Test
    void testAbundantOdd_Thousandth() {
        List<Integer> result = new ArrayList<>();

        AbundantOddNumbers.abundantOdd(1, 2500000, 1000, true);

        assertEquals(1000, AbundantOddNumbers.result.size()); //check if we got 1000 results

        int thousandthAbundantOdd = AbundantOddNumbers.result.get(999); //we assert only the last value, the 1000th
        assertEquals(1933995, thousandthAbundantOdd); // Precalculated expected value
    }


     @Test
    void testAbundantOdd_OverBillion() {
        AbundantOddNumbers.abundantOdd(1000000001, 2147483647, 1, false);
        assertEquals(1000000001, AbundantOddNumbers.result.get(0));  // Precalculated expected value
    }

}
