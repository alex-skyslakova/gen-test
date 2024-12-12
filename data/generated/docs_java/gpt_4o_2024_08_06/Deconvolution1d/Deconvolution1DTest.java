import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class Deconvolution1DTest {

    @Test
    public void testDeconvWithF() {
        int[] g = {24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7};
        int[] f = {-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1};
        int[] expectedH = {-8, -9, -3, -1, -6, 7};

        int[] resultH = Deconvolution1D.deconv(g, f);
        assertArrayEquals("deconv(g, f) should equal h", expectedH, resultH);
    }

    @Test
    public void testDeconvWithH() {
        int[] g = {24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96, 96, 31, 55, 36, 29, -43, -7};
        int[] h = {-8, -9, -3, -1, -6, 7};
        int[] expectedF = {-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1};

        int[] resultF = Deconvolution1D.deconv(g, h);
        assertArrayEquals("deconv(g, h) should equal f", expectedF, resultF);
    }
}
