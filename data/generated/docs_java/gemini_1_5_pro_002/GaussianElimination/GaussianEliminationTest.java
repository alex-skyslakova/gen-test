import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class GaussianEliminationTest {

    @Test
    void testNullInput() {
        assertThrows(IllegalArgumentException.class, () -> GaussianElimination.solve(null, null));
        assertThrows(IllegalArgumentException.class, () -> GaussianElimination.solve(new double[0][0], new double[0][0]));
    }

    @Test
    void testInvalidDimensions() {
        assertThrows(IllegalArgumentException.class, () -> GaussianElimination.solve(new double[2][2], new double[3][1]));
        assertThrows(IllegalArgumentException.class, () -> GaussianElimination.solve(new double[3][2], new double[3][1]));
    }

    @Test
    void testSimpleSystem() {
        double[][] a = {{2, 1}, {1, -1}};
        double[][] b = {{8}, {0}};
        double det = GaussianElimination.solve(a, b);
        assertEquals(-3.0, det);
        assertArrayEquals(new double[]{4.0}, b[0], 1e-9);
        assertArrayEquals(new double[]{4.0}, b[1], 1e-9);
    }


    @Test
    void testExampleFromMain() {
        double[][] a = new double[][]{{4.0, 1.0, 0.0, 0.0, 0.0},
                {1.0, 4.0, 1.0, 0.0, 0.0},
                {0.0, 1.0, 4.0, 1.0, 0.0},
                {0.0, 0.0, 1.0, 4.0, 1.0},
                {0.0, 0.0, 0.0, 1.0, 4.0}};

        double[][] b = new double[][]{{1.0 / 2.0},
                {2.0 / 3.0},
                {3.0 / 4.0},
                {4.0 / 5.0},
                {5.0 / 6.0}};

        double[] x = {39.0 / 400.0,
                11.0 / 100.0,
                31.0 / 240.0,
                37.0 / 300.0,
                71.0 / 400.0};

        double det = GaussianElimination.solve(a, b);

        for (int i = 0; i < 5; i++) {
            assertEquals(x[i], b[i][0], 1e-9);
        }
    }

    @Test
    void testSingularMatrix() {
        double[][] a = {{1, 1}, {1, 1}};
        double[][] b = {{1}, {2}};
        double det = GaussianElimination.solve(a, b);
        assertEquals(0.0, det, 1e-9); // Expecting determinant to be close to zero
    }


    @Test
    void testPivotingRequired() {
        double[][] a = {{0, 1}, {1, 0}};
        double[][] b = {{2}, {1}};
        double det = GaussianElimination.solve(a, b);
        assertEquals(-1.0, det);
        assertArrayEquals(new double[]{1.0}, b[0], 1e-9);
        assertArrayEquals(new double[]{2.0}, b[1], 1e-9);
    }

    @Test
    void testZeroDeterminant(){
        double[][] a = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] b = {{1}, {2}, {3}};
        double det = GaussianElimination.solve(a, b);
        assertEquals(0.0, det, 1e-9);
    }


}
