import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.*;

public class TestTest {

    @Test
    void calc_sqrt2() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? 2 : 1, 1};
        double result = Test.calc(f, 200);
        assertEquals(Math.sqrt(2), result, 1e-10);
    }

    @Test
    void calc_e() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? n : 2, n > 1 ? (n - 1) : 1};
        double result = Test.calc(f, 200);
        assertEquals(Math.E, result, 1e-10);
    }

    @Test
    void calc_pi() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? 6 : 3, (int) Math.pow(2 * n - 1, 2)};
        double result = Test.calc(f, 200);
        assertEquals(Math.PI, result, 1e-2); // Reduced precision for Pi due to slower convergence
    }

    @Test
    void calc_zeroIterations() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? 2 : 1, 1};
        double result = Test.calc(f, 0);
        assertEquals(1, result, 1e-10); // Tests the case when n=0, should return a0
    }


    @Test
    void calc_oneIteration() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? 2 : 1, 1};
        double result = Test.calc(f, 1);
        assertEquals(1.5, result, 1e-10); // Test the case when n=1, performs a single iteration.
    }



}
