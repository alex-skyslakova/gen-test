import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Locale;

public class TestTest {

    @Test
    void dotProductTest() {
        double[] a = {1, 2, 3};
        double[] b = {4, 5, 6};
        assertEquals(32.0, Test.dotProduct(a, b));
    }

    @Test
    void matrixMulTest() {
        double[][] a = {{1, 2}, {3, 4}};
        double[][] b = {{5, 6}, {7, 8}};
        double[][] expected = {{19, 22}, {43, 50}};
        double[][] result = Test.matrixMul(a, b);
        assertArrayEquals(expected, result);


    }


    @Test
    void pivotizeTest() {
        double[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[][] expected = {{0, 0, 1}, {0, 1, 0}, {1, 0, 0}};
        double [][] result = Test.pivotize(m);

        assertTrue(Arrays.deepEquals(expected,result));

         m = {{1, 2, 3}, {7, 5, 6}, {4, 8, 9}};
         expected = {{0, 0, 1}, {1, 0, 0}, {0, 1, 0}};
         result = Test.pivotize(m);

        assertTrue(Arrays.deepEquals(expected,result));


    }


    @Test
    void luTest1() {
        double[][] a = {{1.0, 3, 5}, {2.0, 4, 7}, {1.0, 1, 0}};
        double[][][] lu = Test.lu(a);

        double[][] expectedL = {{1.0, 0.0, 0.0}, {0.5, 1.0, 0.0}, {0.5, -1.0, 1.0}};
        double[][] expectedU = {{2.0, 4.0, 7.0}, {0.0, 1.0, 1.5}, {0.0, 0.0, -2.0}};
        double[][] expectedP = {{0, 1, 0}, {1, 0, 0}, {0, 0, 1}};


        assertTrue(Arrays.deepEquals(expectedL, lu[0]));
        assertTrue(Arrays.deepEquals(expectedU, lu[1]));
        assertTrue(Arrays.deepEquals(expectedP, lu[2]));


    }



    @Test
    void luTest2() {
        double[][] b = {{11.0, 9, 24, 2}, {1.0, 5, 2, 6}, {3.0, 17, 18, 1}, {2.0, 5, 7, 1}};

        double[][][] lu = Test.lu(b);


        double[][] expectedL = {{1.00000, 0.00000, 0.00000, 0.00000}, {0.27273, 1.00000, 0.00000, 0.00000}, {0.09091, 0.28750, 1.00000, 0.00000}, {0.18182, 0.23125, 0.00360, 1.00000}};
        double[][] expectedU = {{11.00000, 9.00000, 24.00000, 2.00000}, {0.00000, 14.54545, 11.45455, 0.45455}, {0.00000, 0.00000, -3.47500, 5.68750}, {0.00000, 0.00000, 0.00000, 0.51079}};
        double[][] expectedP = {{1, 0, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 0, 1}};

       assertTrue(Arrays.deepEquals(expectedL, lu[0]));
        assertTrue(Arrays.deepEquals(expectedU, lu[1]));
        assertTrue(Arrays.deepEquals(expectedP, lu[2]));

    }

    private void assertArrayEquals(double[][] expected, double[][] actual) {
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], 0.001);
        }
    }

}
