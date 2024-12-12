import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import kronecker.Product;

public class ProductTest {

    @Test
    void testProduct_sample1() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{0, 5}, {6, 7}};
        int[][] expected = {{0, 5, 0, 10}, {6, 7, 12, 14}, {0, 15, 0, 20}, {18, 21, 24, 28}};
        assertArrayEquals(expected, Product.product(a, b));
    }

    @Test
    void testProduct_sample2() {
        int[][] a = {{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int[][] b = {{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        int[][] expected = {
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0}
        };
        assertArrayEquals(expected, Product.product(a, b));
    }

    @Test
    void testProduct_singleElementMatrices() {
        int[][] a = {{5}};
        int[][] b = {{7}};
        int[][] expected = {{35}};
        assertArrayEquals(expected, Product.product(a, b));
    }


    @Test
    void testProduct_differentDimensions() {
        int[][] a = {{1, 2}};
        int[][] b = {{3}, {4}};
        int[][] expected = {{3}, {4}, {6}, {8}};
        assertArrayEquals(expected, Product.product(a, b));
    }

    @Test
    void testProduct_zeroMatrix() {
        int[][] a = {{0, 0}, {0, 0}};
        int[][] b = {{1, 2}, {3, 4}};
        int[][] expected = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        assertArrayEquals(expected, Product.product(a, b));
    }
}
