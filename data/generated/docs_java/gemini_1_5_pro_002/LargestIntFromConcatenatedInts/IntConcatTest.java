import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class IntConcatTest {

    @Test
    void testJoin() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        assertEquals("123", IntConcat.join(list));
        List<String> strings = Arrays.asList("a", "b", "c");
        assertEquals("abc", IntConcat.join(strings));
        List<Object> mixed = Arrays.asList(1, "b", 3.14);
        assertEquals("1b3.14", IntConcat.join(mixed));
        List<Object> empty = new ArrayList<>();
        assertEquals("", IntConcat.join(empty));

    }



    @Test
    void testMainLogic1() {
        List<Integer> ints1 = new ArrayList<>(Arrays.asList(1, 34, 3, 98, 9, 76, 45, 4));
        Collections.sort(ints1, IntConcat.sorter);
        assertEquals("998764543431", IntConcat.join(ints1));
    }

    @Test
    void testMainLogic2() {
        List<Integer> ints2 = new ArrayList<>(Arrays.asList(54, 546, 548, 60));
        Collections.sort(ints2, IntConcat.sorter);
        assertEquals("6054854654", IntConcat.join(ints2));
    }


    @Test
    void testSorter_sameLength() {
        assertEquals(1, IntConcat.sorter.compare(9,8));
        assertEquals(-1, IntConcat.sorter.compare(8, 9));
         assertEquals(0, IntConcat.sorter.compare(8, 8));
    }
    
    @Test
    void testSorter_differentLength() {
        assertEquals(1, IntConcat.sorter.compare(12, 1)); // 11 vs 112
        assertEquals(-1, IntConcat.sorter.compare(1,12)); //112 vs 11

        assertEquals(1, IntConcat.sorter.compare(997,998));
        assertEquals(-1, IntConcat.sorter.compare(998, 997));



    }

    @Test
    void testSorter_complexCases(){
        assertEquals(-1, IntConcat.sorter.compare(121,12)); //121121 vs 1212
        assertEquals(1, IntConcat.sorter.compare(12, 121));
    }



}
