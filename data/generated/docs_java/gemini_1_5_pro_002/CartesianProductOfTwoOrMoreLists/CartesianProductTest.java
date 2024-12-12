import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartesianProductTest {

    private final CartesianProduct cartesianProduct = new CartesianProduct();

    @Test
    void testBinaryProduct1() {
        List<Integer> l1 = asList(1, 2);
        List<Integer> l2 = asList(3, 4);
        List<?> expected = asList(asList(1, 3), asList(1, 4), asList(2, 3), asList(2, 4));
        assertEquals(expected, cartesianProduct.product(l1, l2));
    }

    @Test
    void testBinaryProduct2() {
        List<Integer> l1 = asList(3, 4);
        List<Integer> l2 = asList(1, 2);
        List<?> expected = asList(asList(3, 1), asList(3, 2), asList(4, 1), asList(4, 2));
        assertEquals(expected, cartesianProduct.product(l1, l2));
    }

    @Test
    void testBinaryProductEmpty1() {
        List<Integer> l1 = asList(1, 2);
        List<Integer> l2 = emptyList();
        List<?> expected = emptyList();
        assertEquals(expected, cartesianProduct.product(l1, l2));
    }

    @Test
    void testBinaryProductEmpty2() {
        List<Integer> l1 = emptyList();
        List<Integer> l2 = asList(1, 2);
        List<?> expected = emptyList();
        assertEquals(expected, cartesianProduct.product(l1, l2));
    }


    @Test
    void testNaryProduct1() {
        List<Integer> l1 = asList(1776, 1789);
        List<Integer> l2 = asList(7, 12);
        List<Integer> l3 = asList(4, 14, 23);
        List<Integer> l4 = asList(0, 1);

        List<?> expected = asList(
                asList(1776, 7, 4, 0), asList(1776, 7, 4, 1), asList(1776, 7, 14, 0), asList(1776, 7, 14, 1),
                asList(1776, 7, 23, 0), asList(1776, 7, 23, 1), asList(1776, 12, 4, 0), asList(1776, 12, 4, 1),
                asList(1776, 12, 14, 0), asList(1776, 12, 14, 1), asList(1776, 12, 23, 0), asList(1776, 12, 23, 1),
                asList(1789, 7, 4, 0), asList(1789, 7, 4, 1), asList(1789, 7, 14, 0), asList(1789, 7, 14, 1),
                asList(1789, 7, 23, 0), asList(1789, 7, 23, 1), asList(1789, 12, 4, 0), asList(1789, 12, 4, 1),
                asList(1789, 12, 14, 0), asList(1789, 12, 14, 1), asList(1789, 12, 23, 0), asList(1789, 12, 23, 1)
        );
        assertEquals(expected, cartesianProduct.product(l1, l2, l3, l4));

    }


    @Test
    void testNaryProduct2() {
        List<Integer> l1 = asList(1, 2, 3);
        List<Integer> l2 = asList(30);
        List<Integer> l3 = asList(500, 100);

        List<?> expected = asList(
                asList(1, 30, 500), asList(1, 30, 100), asList(2, 30, 500),
                asList(2, 30, 100), asList(3, 30, 500), asList(3, 30, 100));
        assertEquals(expected, cartesianProduct.product(l1, l2, l3));
    }

    @Test
    void testNaryProductEmpty() {
        List<Integer> l1 = asList(1, 2, 3);
        List<Integer> l2 = emptyList();
        List<Integer> l3 = asList(500, 100);

        List<?> expected = emptyList();

        assertEquals(expected, cartesianProduct.product(l1, l2, l3));
    }

    @Test
    void testNoArgs(){
        assertEquals(emptyList(), cartesianProduct.product());
    }

    @Test
    void testOneArg(){
        assertEquals(emptyList(), cartesianProduct.product(asList(1,2)));
    }
}
