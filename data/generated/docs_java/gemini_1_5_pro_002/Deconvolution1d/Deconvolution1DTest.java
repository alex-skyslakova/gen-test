import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class Deconvolution1DTest {

    @Test
    void deconv_g_f_equals_h() {
        int[] h = {-8, -9, -3, -1, -6, 7};
        int[] f = {-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1};
        int[] g = {24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96,
                96, 31, 55, 36, 29, -43, -7};

        assertArrayEquals(h, Deconvolution1D.deconv(g, f));
    }

    @Test
    void deconv_g_h_equals_f() {
        int[] h = {-8, -9, -3, -1, -6, 7};
        int[] f = {-3, -6, -1, 8, -6, 3, -1, -9, -9, 3, -2, 5, 2, -2, -7, -1};
        int[] g = {24, 75, 71, -34, 3, 22, -45, 23, 245, 25, 52, 25, -67, -96,
                96, 31, 55, 36, 29, -43, -7};

        assertArrayEquals(f, Deconvolution1D.deconv(g, h));
    }


    @Test
    void deconv_f0_is_zero(){
        int[] f = {0, 1};
        int[] g = {0,1};
        assertThrows(ArithmeticException.class, () -> Deconvolution1D.deconv(g,f));
    }


    @Test
    void deconv_empty_inputs(){
         int[] f = {};
         int[] g = {};
         assertArrayEquals(new int[0], Deconvolution1D.deconv(g,f));
    }

    @Test
    void deconv_g_shorter_than_f(){
         int[] f = {1,2,3};
         int[] g = {1,2};
         assertArrayEquals(new int[0], Deconvolution1D.deconv(g,f));
    }


}
