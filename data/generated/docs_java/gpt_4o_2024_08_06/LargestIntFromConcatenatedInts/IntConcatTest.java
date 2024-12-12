import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;

public class IntConcatTest {

    @Test
    public void testLargestConcatenationSet1() {
        List<Integer> ints1 = new ArrayList<>(Arrays.asList(1, 34, 3, 98, 9, 76, 45, 4));
        Collections.sort(ints1, new IntConcat().sorter);
        String result = IntConcat.join(ints1);
        assertEquals("998764543431", result);
    }

    @Test
    public void testLargestConcatenationSet2() {
        List<Integer> ints2 = new ArrayList<>(Arrays.asList(54, 546, 548, 60));
        Collections.sort(ints2, new IntConcat().sorter);
        String result = IntConcat.join(ints2);
        assertEquals("6054854654", result);
    }

    @Test
    public void testSingleElement() {
        List<Integer> singleElement = new ArrayList<>(Arrays.asList(5));
        Collections.sort(singleElement, new IntConcat().sorter);
        String result = IntConcat.join(singleElement);
        assertEquals("5", result);
    }

    @Test
    public void testAllSameDigits() {
        List<Integer> sameDigits = new ArrayList<>(Arrays.asList(11, 11, 11));
        Collections.sort(sameDigits, new IntConcat().sorter);
        String result = IntConcat.join(sameDigits);
        assertEquals("111111", result);
    }

    @Test
    public void testDifferentLengths() {
        List<Integer> differentLengths = new ArrayList<>(Arrays.asList(9, 91, 911));
        Collections.sort(differentLengths, new IntConcat().sorter);
        String result = IntConcat.join(differentLengths);
        assertEquals("991911", result);
    }
}
