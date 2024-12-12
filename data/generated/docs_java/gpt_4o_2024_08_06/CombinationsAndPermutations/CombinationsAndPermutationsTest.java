import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationsAndPermutationsTest {

    @Test
    public void testPermutationExactIntegerArithmetic() {
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.permutation(1, 0));
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.permutation(2, 1));
        assertEquals(BigInteger.valueOf(6), CombinationsAndPermutations.permutation(3, 2));
        assertEquals(BigInteger.valueOf(20), CombinationsAndPermutations.permutation(5, 2));
        assertEquals(BigInteger.valueOf(332640), CombinationsAndPermutations.permutation(12, 6));
    }

    @Test
    public void testCombinationExactIntegerArithmetic() {
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.combination(10, 0));
        assertEquals(BigInteger.valueOf(252), CombinationsAndPermutations.combination(10, 5));
        assertEquals(BigInteger.valueOf(15504), CombinationsAndPermutations.combination(20, 10));
        assertEquals(BigInteger.valueOf(593775), CombinationsAndPermutations.combination(30, 15));
        assertEquals(BigInteger.valueOf(548354040), CombinationsAndPermutations.combination(60, 30));
    }

    @Test
    public void testPermutationFloatingPointArithmetic() {
        assertEquals("2.0 * 10^1", CombinationsAndPermutations.display(CombinationsAndPermutations.permutation(5, 2), 50));
        assertEquals("5.0 * 10^299", CombinationsAndPermutations.display(CombinationsAndPermutations.permutation(1000, 500), 50));
    }

    @Test
    public void testCombinationFloatingPointArithmetic() {
        assertEquals("1.0 * 10^29", CombinationsAndPermutations.display(CombinationsAndPermutations.combination(100, 50), 50));
        assertEquals("2.7 * 10^299", CombinationsAndPermutations.display(CombinationsAndPermutations.combination(1000, 500), 50));
    }
}
