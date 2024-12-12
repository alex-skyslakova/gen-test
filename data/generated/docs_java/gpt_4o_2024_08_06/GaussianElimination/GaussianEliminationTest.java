import org.junit.Test;
import static org.junit.Assert.*;

public class GaussianEliminationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullMatrixA() {
        double[][] a = null;
        double[][] b = {{1.0}, {2.0}};
        GaussianElimination.solve(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMatrixB() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = null;
        GaussianElimination.solve(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMatrixA() {
        double[][] a = {};
        double[][] b = {{1.0}, {2.0}};
        GaussianElimination.solve(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMatrixB() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {};
        GaussianElimination.solve(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDimensions() {
        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] b = {{1.0, 2.0}};
        GaussianElimination.solve(a, b);
    }

    @Test
    public void testSolve() {
        double[][] a = {
            {4.0, 1.0, 0.0, 0.0, 0.0},
            {1.0, 4.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 4.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 4.0, 1.0},
            {0.0, 0.0, 0.0, 1.0, 4.0}
        };

        double[][] b = {
            {1.0 / 2.0},
            {2.0 / 3.0},
            {3.0 / 4.0},
            {4.0 / 5.0},
            {5.0 / 6.0}
        };

        double[] expectedX = {
            39.0 / 400.0,
            11.0 / 100.0,
            31.0 / 240.0,
            37.0 / 300.0,
            71.0 / 400.0
        };

        double det = GaussianElimination.solve(a, b);

        assertEquals(1024.0, det, 1e-9);

        for (int i = 0; i < expectedX.length; i++) {
            assertEquals(expectedX[i], b[i][0], 1e-9);
        }
    }

    @Test
    public void testDeterminant() {
        double[][] a = {
            {2.0, 1.0, 1.0},
            {1.0, 3.0, 2.0},
            {1.0, 0.0, 0.0}
        };

        double[][] b = {
            {4.0},
            {5.0},
            {6.0}
        };

        double det = GaussianElimination.solve(a, b);

        assertEquals(-1.0, det, 1e-9);
    }
}
