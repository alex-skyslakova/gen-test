import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CramersRuleTest {

    @Test
    public void testCramersRule() {
        // Given matrix and constants
        CramersRule.Matrix mat = new CramersRule.Matrix(
                Arrays.asList(2d, -1d, 5d, 1d),
                Arrays.asList(3d, 2d, 2d, -6d),
                Arrays.asList(1d, 3d, 3d, -1d),
                Arrays.asList(5d, -2d, -3d, 3d)
        );
        List<Double> b = Arrays.asList(-3d, -32d, -47d, 49d);

        // Expected solution
        List<Double> expectedSolution = Arrays.asList(5d, 3d, -4d, 1d);

        // Actual solution
        List<Double> actualSolution = CramersRule.cramersRule(mat, b);

        // Assertions
        assertEquals(expectedSolution.size(), actualSolution.size());
        for (int i = 0; i < expectedSolution.size(); i++) {
            assertEquals(expectedSolution.get(i), actualSolution.get(i), 0.0001);
        }
    }

    @Test
    public void testDeterminant1x1Matrix() {
        CramersRule.Matrix mat = new CramersRule.Matrix(Arrays.asList(5d));
        double expectedDeterminant = 5d;
        double actualDeterminant = mat.determinant();
        assertEquals(expectedDeterminant, actualDeterminant, 0.0001);
    }

    @Test
    public void testDeterminant2x2Matrix() {
        CramersRule.Matrix mat = new CramersRule.Matrix(
                Arrays.asList(1d, 2d),
                Arrays.asList(3d, 4d)
        );
        double expectedDeterminant = -2d;
        double actualDeterminant = mat.determinant();
        assertEquals(expectedDeterminant, actualDeterminant, 0.0001);
    }

    @Test
    public void testDeterminant3x3Matrix() {
        CramersRule.Matrix mat = new CramersRule.Matrix(
                Arrays.asList(6d, 1d, 1d),
                Arrays.asList(4d, -2d, 5d),
                Arrays.asList(2d, 8d, 7d)
        );
        double expectedDeterminant = -306d;
        double actualDeterminant = mat.determinant();
        assertEquals(expectedDeterminant, actualDeterminant, 0.0001);
    }

    @Test
    public void testReplaceColumn() {
        CramersRule.Matrix mat = new CramersRule.Matrix(
                Arrays.asList(1d, 2d, 3d),
                Arrays.asList(4d, 5d, 6d),
                Arrays.asList(7d, 8d, 9d)
        );
        List<Double> b = Arrays.asList(10d, 11d, 12d);
        CramersRule.Matrix expectedMatrix = new CramersRule.Matrix(
                Arrays.asList(10d, 2d, 3d),
                Arrays.asList(11d, 5d, 6d),
                Arrays.asList(12d, 8d, 9d)
        );
        CramersRule.Matrix actualMatrix = mat.replaceColumn(b, 0);
        assertEquals(expectedMatrix.toString(), actualMatrix.toString());
    }
}
