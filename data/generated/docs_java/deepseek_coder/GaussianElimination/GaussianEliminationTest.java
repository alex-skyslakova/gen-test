import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GaussianEliminationTest {

    @Test
    public void testSolveWithValidInput() {
        double[][] a = new double[][] {
            {4.0, 1.0, 0.0, 0.0, 0.0},
            {1.0, 4.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 4.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 4.0, 1.0},
            {0.0, 0.0, 0.0, 1.0, 4.0}
        };

        double[][] b = new double[][] {
            {1.0 / 2.0},
            {2.0 / 3.0},
            {3.0 / 4.0},
            {4.0 / 5.0},
            {5.0 / 6.0}
        };

        double expectedDet = 1024.0; // Determinant of the matrix A

        double actualDet = GaussianElimination.solve(a, b);

        assertEquals(expectedDet, actualDet, 1e-10);

        double[] expectedX = {39.0 / 400.0, 11.0 / 100.0, 31.0 / 240.0, 37.0 / 300.0, 71.0 / 400.0};

        for (int i = 0; i < 5; i++) {
            assertEquals(expectedX[i], b[i][0], 1e-10);
        }
    }

    @Test
    public void testSolveWithInvalidDimensions() {
        double[][] a = new double[][] {
            {4.0, 1.0, 0.0, 0.0, 0.0},
            {1.0, 4.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 4.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 4.0, 1.0}
        };

        double[][] b = new double[][] {
            {1.0 / 2.0},
            {2.0 / 3.0},
            {3.0 / 4.0},
            {4.0 / 5.0},
            {5.0 / 6.0}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(a, b);
        });
    }

    @Test
    public void testSolveWithNullInput() {
        double[][] a = null;
        double[][] b = new double[][] {
            {1.0 / 2.0},
            {2.0 / 3.0},
            {3.0 / 4.0},
            {4.0 / 5.0},
            {5.0 / 6.0}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(a, b);
        });

        a = new double[][] {
            {4.0, 1.0, 0.0, 0.0, 0.0},
            {1.0, 4.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 4.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 4.0, 1.0},
            {0.0, 0.0, 0.0, 1.0, 4.0}
        };

        b = null;

        assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(a, b);
        });
    }

    @Test
    public void testSolveWithEmptyInput() {
        double[][] a = new double[0][0];
        double[][] b = new double[][] {
            {1.0 / 2.0},
            {2.0 / 3.0},
            {3.0 / 4.0},
            {4.0 / 5.0},
            {5.0 / 6.0}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(a, b);
        });

        a = new double[][] {
            {4.0, 1.0, 0.0, 0.0, 0.0},
            {1.0, 4.0, 1.0, 0.0, 0.0},
            {0.0, 1.0, 4.0, 1.0, 0.0},
            {0.0, 0.0, 1.0, 4.0, 1.0},
            {0.0, 0.0, 0.0, 1.0, 4.0}
        };

        b = new double[0][0];

        assertThrows(IllegalArgumentException.class, () -> {
            GaussianElimination.solve(a, b);
        });
    }
}
