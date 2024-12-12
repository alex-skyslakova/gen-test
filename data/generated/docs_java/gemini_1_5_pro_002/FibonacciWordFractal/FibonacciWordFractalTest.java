import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FibonacciWordFractalTest {

    @Test
    void testWordFractal_n0() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(0);
        assertEquals("", fractal.wordFractal(0));
    }

    @Test
    void testWordFractal_n1() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(1);
        assertEquals("1", fractal.wordFractal(1));
    }

    @Test
    void testWordFractal_n2() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(2);
        assertEquals("0", fractal.wordFractal(2));
    }

    @Test
    void testWordFractal_n3() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(3);
        assertEquals("01", fractal.wordFractal(3));
    }

    @Test
    void testWordFractal_n4() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(4);
        assertEquals("010", fractal.wordFractal(4));
    }

    @Test
    void testWordFractal_n5() {
        FibonacciWordFractal fractal = new FibonacciWordFractal(5);
        assertEquals("01001", fractal.wordFractal(5));
    }

    @Test
    void testDrawWordFractal_EmptyWord() {
        // No assertions possible on drawing directly.  Testing for no exceptions.
        FibonacciWordFractal fractal = new FibonacciWordFractal(0); // Empty word
        fractal.drawWordFractal(null, 0, 0, 0, 0); 
    }


    @Test
    void testDrawWordFractal_OnlyOnes() {
        // No assertions possible on drawing directly.  Testing for no exceptions.
        FibonacciWordFractal fractal = new FibonacciWordFractal(1); // "1" only.
        fractal.wordFractal = "111"; // Force setting wordFractal for testing
        fractal.drawWordFractal(null, 0, 0, 1, 1);
    }

    @Test
    void testDrawWordFractal_WithZeros() {
        // No assertions possible on drawing directly.  Testing for no exceptions.
        FibonacciWordFractal fractal = new FibonacciWordFractal(4);  // "010"
        fractal.wordFractal = "01001"; // Force setting for test case
        fractal.drawWordFractal(null, 0, 0, 1, 1);
    }


}
