import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class CholeskyTest {

    @Test
    public void testExample1() {
        double[][] input = {
            {25, 15, -5},
            {15, 18, 0},
            {-5, 0, 11}
        };
        double[][] expectedOutput = {
            {5, 0, 0},
            {3, 3, 0},
            {-1, 1, 3}
        };
        double[][] actualOutput = Cholesky.chol(input);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testExample2() {
        double[][] input = {
            {18, 22, 54, 42},
            {22, 70, 86, 62},
            {54, 86, 174, 134},
            {42, 62, 134, 106}
        };
        double[][] expectedOutput = {
            {4.24264, 0.00000, 0.00000, 0.00000},
            {5.18545, 6.56591, 0.00000, 0.00000},
            {12.72792, 3.04604, 1.64974, 0.00000},
            {9.89949, 1.62455, 1.84971, 1.39262}
        };
        double[][] actualOutput = Cholesky.chol(input);
        for (int i = 0; i < expectedOutput.length; i++) {
            assertArrayEquals(expectedOutput[i], actualOutput[i], 0.00001);
        }
    }

    @Test
    public void testIdentityMatrix() {
        double[][] input = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        double[][] expectedOutput = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        double[][] actualOutput = Cholesky.chol(input);
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPascalMatrix() {
        double[][] input = {
            {1, 1, 1},
            {1, 2, 3},
            {1, 3, 6}
        };
        double[][] expectedOutput = {
            {1, 0, 0},
            {1, 1, 0},
            {1, 2, 1}
        };
        double[][] actualOutput = Cholesky.chol(input);
        assertArrayEquals(expectedOutput, actualOutput);
    }
}
