import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

public class CombinationsAndPermutationsTest {

    @Test
    void testCombinationSmallValues() {
        assertEquals(BigInteger.valueOf(10), CombinationsAndPermutations.combination(5, 2));
        assertEquals(BigInteger.valueOf(3), CombinationsAndPermutations.combination(3, 2));
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.combination(5, 0));
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.combination(5, 5));

    }


    @Test
    void testCombinationLargeValues() {
        assertEquals(new BigInteger("17310309456440"), CombinationsAndPermutations.combination(20, 10));

    }


        @Test
    void testCombinationEdgeCases() {
        assertEquals(BigInteger.ONE, CombinationsAndPermutations.combination(0, 0));
        assertEquals(BigInteger.ZERO, CombinationsAndPermutations.combination(0,1));
        assertEquals(BigInteger.ONE, CombinationsAndPermutations.combination(1, 0));
        assertEquals(BigInteger.ONE, CombinationsAndPermutations.combination(1, 1));
    }



    @Test
    void testPermutationSmallValues() {
        assertEquals(BigInteger.valueOf(20), CombinationsAndPermutations.permutation(5, 2));
        assertEquals(BigInteger.valueOf(6), CombinationsAndPermutations.permutation(3, 2));
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.permutation(5, 0));
        assertEquals(BigInteger.valueOf(120), CombinationsAndPermutations.permutation(5, 5));
    }

    @Test
    void testPermutationLargeValues() {
        assertEquals(new BigInteger("6704425728000"), CombinationsAndPermutations.permutation(20, 10));

    }

        @Test
    void testPermutationEdgeCases() {
        assertEquals(BigInteger.ONE, CombinationsAndPermutations.permutation(0, 0));
        assertEquals(BigInteger.ZERO, CombinationsAndPermutations.permutation(0, 1));
        assertEquals(BigInteger.ONE, CombinationsAndPermutations.permutation(1, 0));
        assertEquals(BigInteger.ONE, CombinationsAndPermutations.permutation(1, 1));

    }


    @Test
    void testDisplay() {
        assertEquals("1.2345 * 10^5", CombinationsAndPermutations.display(new BigInteger("123456"), 5));
        assertEquals("1.2 * 10^1", CombinationsAndPermutations.display(new BigInteger("12"), 50));
        assertEquals("1 * 10^0", CombinationsAndPermutations.display(BigInteger.ONE, 50));

    }


}
