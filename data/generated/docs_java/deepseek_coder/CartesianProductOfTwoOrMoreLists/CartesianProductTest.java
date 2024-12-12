import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static java.util.Arrays.asList;

public class CartesianProductTest {

    private final CartesianProduct cartesianProduct = new CartesianProduct();

    @Test
    public void testBinaryProduct() {
        List<?> result1 = cartesianProduct.product(asList(1, 2), asList(3, 4));
        assertEquals(asList(asList(1, 3), asList(1, 4), asList(2, 3), asList(2, 4)), result1);

        List<?> result2 = cartesianProduct.product(asList(3, 4), asList(1, 2));
        assertEquals(asList(asList(3, 1), asList(3, 2), asList(4, 1), asList(4, 2)), result2);
    }

    @Test
    public void testEmptyListProduct() {
        List<?> result1 = cartesianProduct.product(asList(1, 2), asList());
        assertTrue(result1.isEmpty());

        List<?> result2 = cartesianProduct.product(asList(), asList(1, 2));
        assertTrue(result2.isEmpty());
    }

    @Test
    public void testNaryProduct() {
        List<?> result1 = cartesianProduct.product(asList(1776, 1789), asList(7, 12), asList(4, 14, 23), asList(0, 1));
        assertEquals(asList(
                asList(1776, 7, 4, 0), asList(1776, 7, 4, 1), asList(1776, 7, 14, 0), asList(1776, 7, 14, 1), asList(1776, 7, 23, 0), asList(1776, 7, 23, 1),
                asList(1776, 12, 4, 0), asList(1776, 12, 4, 1), asList(1776, 12, 14, 0), asList(1776, 12, 14, 1), asList(1776, 12, 23, 0), asList(1776, 12, 23, 1),
                asList(1789, 7, 4, 0), asList(1789, 7, 4, 1), asList(1789, 7, 14, 0), asList(1789, 7, 14, 1), asList(1789, 7, 23, 0), asList(1789, 7, 23, 1),
                asList(1789, 12, 4, 0), asList(1789, 12, 4, 1), asList(1789, 12, 14, 0), asList(1789, 12, 14, 1), asList(1789, 12, 23, 0), asList(1789, 12, 23, 1)
        ), result1);

        List<?> result2 = cartesianProduct.product(asList(1, 2, 3), asList(30), asList(500, 100));
        assertEquals(asList(
                asList(1, 30, 500), asList(1, 30, 100),
                asList(2, 30, 500), asList(2, 30, 100),
                asList(3, 30, 500), asList(3, 30, 100)
        ), result2);

        List<?> result3 = cartesianProduct.product(asList(1, 2, 3), asList(), asList(500, 100));
        assertTrue(result3.isEmpty());
    }
}
