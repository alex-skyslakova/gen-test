import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class PowerTreeTest {

    @Test
    void testPathZero() {
        assertEquals(PowerTree.path(0).size(), 0);
    }

    @Test
    void testPathOne() {
        List<Integer> path1 = PowerTree.path(1);
        assertEquals(path1.size(), 1);
        assertEquals(path1.get(0), Integer.valueOf(1));
    }

    @Test
    void testPathSmall() {
        List<Integer> path5 = PowerTree.path(5);
        assertEquals(path5.size(), 3);
        assertEquals(path5.get(0), 1);
        assertEquals(path5.get(1), 2);
        assertEquals(path5.get(2), 5);
    }


    @Test
    void testPathLarge() {
        List<Integer> path43 = PowerTree.path(43);
        assertEquals(path43.size(), 7);
        assertEquals(path43.get(0), 1);
        assertEquals(path43.get(1), 2);
        assertEquals(path43.get(2), 3);
        assertEquals(path43.get(3), 5);
        assertEquals(path43.get(4), 10);
        assertEquals(path43.get(5), 20);
        assertEquals(path43.get(6), 40);
        assertEquals(path43.get(7), 43);


    }


    @Test
    void testTreePowZero() {
        assertEquals(PowerTree.treePow(2.0, 0), BigDecimal.ONE);
    }

    @Test
    void testTreePowOne() {
        assertEquals(PowerTree.treePow(2.0, 1), BigDecimal.valueOf(2.0));
    }

    @Test
    void testTreePowTwo() {
        assertEquals(PowerTree.treePow(2.0, 2), BigDecimal.valueOf(4.0));
    }

    @Test
    void testTreePowLarge() {
        assertEquals(PowerTree.treePow(2.0, 17), BigDecimal.valueOf(131072.0));
    }


    @Test
    void testTreePowFractional() {
         assertEquals(PowerTree.treePow(1.1, 81).doubleValue(), 3411.974350, 1e-6); // Using delta for comparison
    }



}
