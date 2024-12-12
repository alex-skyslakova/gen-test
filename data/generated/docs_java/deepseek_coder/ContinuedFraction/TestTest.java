import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.Math.pow;

public class TestTest {

    private static final double DELTA = 1e-6; // Tolerance for floating-point comparisons

    @Test
    public void testCalcForSquareRootOf2() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? 2 : 1, 1};
        double result = Test.calc(f, 200);
        assertEquals(Math.sqrt(2), result, DELTA);
    }

    @Test
    public void testCalcForNapierConstant() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? n : 2, n > 1 ? (n - 1) : 1};
        double result = Test.calc(f, 200);
        assertEquals(Math.E, result, DELTA);
    }

    @Test
    public void testCalcForPi() {
        Function<Integer, Integer[]> f = n -> new Integer[]{n > 0 ? 6 : 3, (int) pow(2 * n - 1, 2)};
        double result = Test.calc(f, 200);
        assertEquals(Math.PI, result, DELTA);
    }
}
