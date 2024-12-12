import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class CholeskyTest {

    @Test
    void testChol_example1() {
        double[][] test1 = {{25, 15, -5},
                {15, 18, 0},
                {-5, 0, 11}};
        double[][] expected1 = {{5, 0, 0},
                {3, 3, 0},
                {-1, 1, 3}};
        double[][] result1 = Cholesky.chol(test1);

        assertTrue(Arrays.deepEquals(expected1, result1));
       }

    @Test
    void testChol_example2() {
        double[][] test2 = {{18, 22, 54, 42},
                {22, 70, 86, 62},
                {54, 86, 174, 134},
                {42, 62, 134, 106}};
        double[][] expected2 = {{4.24264, 0, 0, 0},
                {5.18545, 6.56591, 0, 0},
                {12.72792, 3.04604, 1.64974, 0},
                {9.89949, 1.62455, 1.84971, 1.39262}};

        double[][] result2 = Cholesky.chol(test2);
        // Due to floating point precision checking with delta
        assertTrue(deepEqualsWithDelta(expected2, result2, 0.00001));
       }


    @Test
    void testChol_pascalUpperTriangle() {
        double[][] pascalUpper = {{1, 1, 1}, {0, 1, 2}, {0, 0, 1}};
        double[][] expected = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        double[][] result = Cholesky.chol(pascalUpper);
        assertTrue(Arrays.deepEquals(expected, result));

    }

    @Test
    void testChol_pascalSymmetric() {
        double[][] pascalSymmetric = {{1, 1, 1}, {1, 2, 3}, {1, 3, 6}};
        double[][] expected = {{1, 0, 0}, {1, 1, 0}, {1, 2, 1}};
        double[][] result = Cholesky.chol(pascalSymmetric);
        assertTrue(Arrays.deepEquals(expected, result));
    }


    // Helper function to compare 2D double arrays with a delta for floating-point comparisons.
    private boolean deepEqualsWithDelta(double[][] arr1, double[][] arr2, double delta) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i].length != arr2[i].length) {
                return false;
            }
            for (int j = 0; j < arr1[i].length; j++) {
                if (Math.abs(arr1[i][j] - arr2[i][j]) > delta) {
                    return false;
                }
            }
        }
        return true;
    }

}

