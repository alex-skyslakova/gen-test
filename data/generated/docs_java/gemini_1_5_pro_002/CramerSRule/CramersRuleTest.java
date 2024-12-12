import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CramersRuleTest {

    @Test
    void testCramersRule_2x2() {
        Matrix mat = new Matrix(Arrays.asList(2d, 1d), Arrays.asList(1d, 2d));
        List<Double> b = Arrays.asList(3d, 5d);
        List<Double> expected = Arrays.asList(1d, 1d);
        List<Double> result = CramersRule.cramersRule(mat, b);
        assertListEquals(expected, result);
    }

    @Test
    void testCramersRule_3x3() {
        Matrix mat = new Matrix(Arrays.asList(3d, 2d, -1d), 
                                Arrays.asList(2d, -2d, 4d), 
                                Arrays.asList(-1d, 0.5, -1d));
        List<Double> b = Arrays.asList(1d, -2d, 0d);
        List<Double> expected = Arrays.asList(1d, -2d, -2d);
        List<Double> result = CramersRule.cramersRule(mat, b);

        assertListEquals(expected, result);
    }


    @Test
    void testCramersRule_givenExample() {
        Matrix mat = new Matrix(Arrays.asList(2d, -1d, 5d, 1d), 
                                Arrays.asList(3d, 2d, 2d, -6d), 
                                Arrays.asList(1d, 3d, 3d, -1d),
                                Arrays.asList(5d, -2d, -3d, 3d));
        List<Double> b = Arrays.asList(-3d, -32d, -47d, 49d);
        List<Double> expected = Arrays.asList(2.0, 3.0, -5.0, 7.0);
        List<Double> result = CramersRule.cramersRule(mat, b);
        assertListEquals(expected, result);

    }
    
    @Test
    void testCramersRule_ZeroDeterminant(){
        Matrix mat = new Matrix(Arrays.asList(1d, 2d), Arrays.asList(2d, 4d));
        List<Double> b = Arrays.asList(3d, 5d);
        assertThrows(ArithmeticException.class, () -> CramersRule.cramersRule(mat,b));
    }



    private void assertListEquals(List<Double> expected, List<Double> actual) {
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), 0.001);
        }
    }
}
