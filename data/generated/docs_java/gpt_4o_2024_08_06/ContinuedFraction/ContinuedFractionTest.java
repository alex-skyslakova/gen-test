import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class ContinuedFractionTest {

    static double calc(Function<Integer, Integer[]> f, int n) {
        double temp = 0;

        for (int ni = n; ni >= 1; ni--) {
            Integer[] p = f.apply(ni);
            temp = p[1] / (double) (p[0] + temp);
        }
        return f.apply(0)[0] + temp;
    }

    @Test
    public void testSquareRootOf2() {
        Function<Integer, Integer[]> sqrt2Function = n -> new Integer[]{n > 0 ? 2 : 1, 1};
        double result = calc(sqrt2Function, 200);
        assertEquals(Math.sqrt(2), result, 1e-9, "Square root of 2 calculation failed");
    }

    @Test
    public void testNapierConstant() {
        Function<Integer, Integer[]> eFunction = n -> new Integer[]{n > 0 ? n : 2, n > 1 ? (n - 1) : 1};
        double result = calc(eFunction, 200);
        assertEquals(Math.E, result, 1e-9, "Napier's Constant calculation failed");
    }

    @Test
    public void testPi() {
        Function<Integer, Integer[]> piFunction = n -> new Integer[]{n > 0 ? 6 : 3, (int) Math.pow(2 * n - 1, 2)};
        double result = calc(piFunction, 200);
        assertEquals(Math.PI, result, 1e-9, "Pi calculation failed");
    }
}
