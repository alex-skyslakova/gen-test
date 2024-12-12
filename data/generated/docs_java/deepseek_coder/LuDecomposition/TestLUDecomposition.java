import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class TestLUDecomposition {

    @Test
    public void testLUDecompositionExample1() {
        double[][] A = {
            {1.0, 3.0, 5.0},
            {2.0, 4.0, 7.0},
            {1.0, 1.0, 0.0}
        };

        double[][] expectedL = {
            {1.0, 0.0, 0.0},
            {0.5, 1.0, 0.0},
            {0.5, -1.0, 1.0}
        };

        double[][] expectedU = {
            {2.0, 4.0, 7.0},
            {0.0, 1.0, 1.5},
            {0.0, 0.0, -2.0}
        };

        double[][] expectedP = {
            {0.0, 1.0, 0.0},
            {1.0, 0.0, 0.0},
            {0.0, 0.0, 1.0}
        };

        double[][][] result = Test.lu(A);
        double[][] L = result[0];
        double[][] U = result[1];
        double[][] P = result[2];

        assertMatrixEquals(expectedL, L, 0.0001);
        assertMatrixEquals(expectedU, U, 0.0001);
        assertMatrixEquals(expectedP, P, 0.0001);
    }

    @Test
    public void testLUDecompositionExample2() {
        double[][] A = {
            {11.0, 9.0, 24.0, 2.0},
            {1.0, 5.0, 2.0, 6.0},
            {3.0, 17.0, 18.0, 1.0},
            {2.0, 5.0, 7.0, 1.0}
        };

        double[][] expectedL = {
            {1.0, 0.0, 0.0, 0.0},
            {0.27273, 1.0, 0.0, 0.0},
            {0.09091, 0.28750, 1.0, 0.0},
            {0.18182, 0.23125, 0.00360, 1.0}
        };

        double[][] expectedU = {
            {11.0, 9.0, 24.0, 2.0},
            {0.0, 14.54545, 11.45455, 0.45455},
            {0.0, 0.0, -3.47500, 5.68750},
            {0.0, 0.0, 0.0, 0.51079}
        };

        double[][] expectedP = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, 0.0, 1.0, 0.0},
            {0.0, 1.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
        };

        double[][][] result = Test.lu(A);
        double[][] L = result[0];
        double[][] U = result[1];
        double[][] P = result[2];

        assertMatrixEquals(expectedL, L, 0.0001);
        assertMatrixEquals(expectedU, U, 0.0001);
        assertMatrixEquals(expectedP, P, 0.0001);
    }

    private void assertMatrixEquals(double[][] expected, double[][] actual, double delta) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], delta);
        }
    }
}
