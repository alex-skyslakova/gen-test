import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntConcatTest {

    @Test
    public void testIntConcat1() {
        List<Integer> ints1 = new ArrayList<>(Arrays.asList(1, 34, 3, 98, 9, 76, 45, 4));
        Collections.sort(ints1, IntConcat.sorter);
        String result = IntConcat.join(ints1);
        assertEquals("998764543431", result);
    }

    @Test
    public void testIntConcat2() {
        List<Integer> ints2 = new ArrayList<>(Arrays.asList(54, 546, 548, 60));
        Collections.sort(ints2, IntConcat.sorter);
        String result = IntConcat.join(ints2);
        assertEquals("6054854654", result);
    }
}
