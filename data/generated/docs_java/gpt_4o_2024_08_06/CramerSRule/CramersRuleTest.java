import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class CramersRuleTest {

    @Test
    public void testCramersRuleSolution() {
        Matrix mat = new Matrix(Arrays.asList(2d, -1d, 5d, 1d), 
                                Arrays.asList(3d, 2d, 2d, -6d), 
                                Arrays.asList(1d, 3d, 3d, -1d),
                                Arrays.asList(5d, -2d, -3d, 3d));
        List<Double> b = Arrays.asList(-3d, -32d, -47d, 49d);
        List<Double> expectedSolution = Arrays.asList(1d, -5d, -2d, 3d);
        List<Double> actualSolution = CramersRule.cramersRule(mat, b);
        assertEquals(expectedSolution, actualSolution);
    }

    @Test
    public void testDeterminantOfMatrix() {
        Matrix mat = new Matrix(Arrays.asList(2d, -1d, 5d, 1d), 
                                Arrays.asList(3d, 2d, 2d, -6d), 
                                Arrays.asList(1d, 3d, 3d, -1d),
                                Arrays.asList(5d, -2d, -3d, 3d));
        double expectedDeterminant = 240;
        double actualDeterminant = mat.determinant();
        assertEquals(expectedDeterminant, actualDeterminant, 1e-9);
    }

    @Test
    public void testSingularMatrix() {
        Matrix singularMat = new Matrix(Arrays.asList(1d, 2d, 3d), 
                                        Arrays.asList(4d, 5d, 6d), 
                                        Arrays.asList(7d, 8d, 9d));
        double determinant = singularMat.determinant();
        assertEquals(0, determinant, 1e-9);
    }

    @Test
    public void testReplaceColumn() {
        Matrix mat = new Matrix(Arrays.asList(1d, 2d, 3d), 
                                Arrays.asList(4d, 5d, 6d), 
                                Arrays.asList(7d, 8d, 9d));
        List<Double> b = Arrays.asList(10d, 11d, 12d);
        Matrix replacedMatrix = mat.replaceColumn(b, 1);
        Matrix expectedMatrix = new Matrix(Arrays.asList(1d, 10d, 3d), 
                                           Arrays.asList(4d, 11d, 6d), 
                                           Arrays.asList(7d, 12d, 9d));
        assertEquals(expectedMatrix.toString(), replacedMatrix.toString());
    }

    @Test
    public void testCofactorMatrix() {
        Matrix mat = new Matrix(Arrays.asList(1d, 2d, 3d), 
                                Arrays.asList(4d, 5d, 6d), 
                                Arrays.asList(7d, 8d, 9d));
        Matrix cofactor = mat.coFactor(0, 0);
        Matrix expectedCofactor = new Matrix(Arrays.asList(5d, 6d), 
                                             Arrays.asList(8d, 9d));
        assertEquals(expectedCofactor.toString(), cofactor.toString());
    }
}
