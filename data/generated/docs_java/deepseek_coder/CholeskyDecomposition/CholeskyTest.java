import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CholeskyTest {

    @Test
    public void testCholeskyDecompositionExample1() {
        double[][] input = {
            {25, 15, -5},
            {15, 18, 0},
            {-5, 0, 11}
        };

        double[][] expected = {
            {5, 0, 0},
            {3, 3, 0},
            {-1, 1, 3}
        };

        double[][] result = Cholesky.chol(input);
        assertArrayEquals(expected, result, 0.0001);
    }

    @Test
    public void testCholeskyDecompositionExample2() {
        double[][] input = {
            {18, 22, 54, 42},
            {22, 70, 86, 62},
            {54, 86, 174, 134},
            {42, 62, 134, 106}
        };

        double[][] expected = {
            {4.24264, 0.00000, 0.00000, 0.00000},
            {5.18545, 6.56591, 0.00000, 0.00000},
            {12.72792, 3.04604, 1.64974, 0.00000},
            {9.89949, 1.62455, 1.84971, 1.39262}
        };

        double[][] result = Cholesky.chol(input);
        assertArrayEquals(expected, result, 0.0001);
    }

    @Test
    public void testCholeskyDecompositionIdentityMatrix() {
        double[][] input = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        double[][] expected = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };

        double[][] result = Cholesky.chol(input);
        assertArrayEquals(expected, result, 0.0001);
    }

    @Test
    public void testCholeskyDecompositionPascalMatrix() {
        double[][] input = {
            {1, 1, 1, 1},
            {1, 2, 3, 4},
            {1, 3, 6, 10},
            {1, 4, 10, 20}
        };

        double[][] expected = {
            {1, 0, 0, 0},
            {1, 1, 0, 0},
            {1, 2, 1, 0},
            {1, 3, 3, 1}
        };

        double[][] result = Cholesky.chol(input);
        assertArrayEquals(expected, result, 0.0001);
    }

    @Test
    public void testCholeskyDecompositionNonSymmetricMatrix() {
        double[][] input = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // This test is expected to fail because the input matrix is not symmetric
        assertThrows(IllegalArgumentException.class, () -> Cholesky.chol(input));
    }

    @Test
    public void testCholeskyDecompositionNonPositiveDefiniteMatrix() {
        double[][] input = {
            {1, 2, 3},
            {2, 4, 5},
            {3, 5, 6}
        };

        // This test is expected to fail because the input matrix is not positive definite
        assertThrows(IllegalArgumentException.class, () -> Cholesky.chol(input));
    }
}
