import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.util.Collections.emptyList;

public class CartesianProductTest {

    @Test
    public void testBinaryCartesianProduct() {
        CartesianProduct cp = new CartesianProduct();

        // Test {1, 2} × {3, 4} = {(1, 3), (1, 4), (2, 3), (2, 4)}
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(1, 3), Arrays.asList(1, 4),
                Arrays.asList(2, 3), Arrays.asList(2, 4)
        );
        assertEquals(expected1, cp.product(Arrays.asList(1, 2), Arrays.asList(3, 4)));

        // Test {3, 4} × {1, 2} = {(3, 1), (3, 2), (4, 1), (4, 2)}
        List<List<Integer>> expected2 = Arrays.asList(
                Arrays.asList(3, 1), Arrays.asList(3, 2),
                Arrays.asList(4, 1), Arrays.asList(4, 2)
        );
        assertEquals(expected2, cp.product(Arrays.asList(3, 4), Arrays.asList(1, 2)));

        // Test {1, 2} × {} = {}
        assertEquals(emptyList(), cp.product(Arrays.asList(1, 2), emptyList()));

        // Test {} × {1, 2} = {}
        assertEquals(emptyList(), cp.product(emptyList(), Arrays.asList(1, 2)));
    }

    @Test
    public void testNAryCartesianProduct() {
        CartesianProduct cp = new CartesianProduct();

        // Test {1776, 1789} × {7, 12} × {4, 14, 23} × {0, 1}
        List<List<Integer>> expected3 = Arrays.asList(
                Arrays.asList(1776, 7, 4, 0), Arrays.asList(1776, 7, 4, 1),
                Arrays.asList(1776, 7, 14, 0), Arrays.asList(1776, 7, 14, 1),
                Arrays.asList(1776, 7, 23, 0), Arrays.asList(1776, 7, 23, 1),
                Arrays.asList(1776, 12, 4, 0), Arrays.asList(1776, 12, 4, 1),
                Arrays.asList(1776, 12, 14, 0), Arrays.asList(1776, 12, 14, 1),
                Arrays.asList(1776, 12, 23, 0), Arrays.asList(1776, 12, 23, 1),
                Arrays.asList(1789, 7, 4, 0), Arrays.asList(1789, 7, 4, 1),
                Arrays.asList(1789, 7, 14, 0), Arrays.asList(1789, 7, 14, 1),
                Arrays.asList(1789, 7, 23, 0), Arrays.asList(1789, 7, 23, 1),
                Arrays.asList(1789, 12, 4, 0), Arrays.asList(1789, 12, 4, 1),
                Arrays.asList(1789, 12, 14, 0), Arrays.asList(1789, 12, 14, 1),
                Arrays.asList(1789, 12, 23, 0), Arrays.asList(1789, 12, 23, 1)
        );
        assertEquals(expected3, cp.product(
                Arrays.asList(1776, 1789),
                Arrays.asList(7, 12),
                Arrays.asList(4, 14, 23),
                Arrays.asList(0, 1)
        ));

        // Test {1, 2, 3} × {30} × {500, 100}
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(1, 30, 500), Arrays.asList(1, 30, 100),
                Arrays.asList(2, 30, 500), Arrays.asList(2, 30, 100),
                Arrays.asList(3, 30, 500), Arrays.asList(3, 30, 100)
        );
        assertEquals(expected4, cp.product(
                Arrays.asList(1, 2, 3),
                Arrays.asList(30),
                Arrays.asList(500, 100)
        ));

        // Test {1, 2, 3} × {} × {500, 100}
        assertEquals(emptyList(), cp.product(
                Arrays.asList(1, 2, 3),
                emptyList(),
                Arrays.asList(500, 100)
        ));
    }
}
