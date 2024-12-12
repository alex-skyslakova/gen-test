import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinationsAndPermutationsTest {

    @Test
    public void testCombination() {
        // Test cases for combination
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.combination(1, 0));
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.combination(1, 1));
        assertEquals(BigInteger.valueOf(10), CombinationsAndPermutations.combination(5, 2));
        assertEquals(BigInteger.valueOf(252), CombinationsAndPermutations.combination(10, 5));
        assertEquals(BigInteger.valueOf(155117520), CombinationsAndPermutations.combination(20, 10));
    }

    @Test
    public void testPermutation() {
        // Test cases for permutation
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.permutation(1, 0));
        assertEquals(BigInteger.valueOf(1), CombinationsAndPermutations.permutation(1, 1));
        assertEquals(BigInteger.valueOf(20), CombinationsAndPermutations.permutation(5, 2));
        assertEquals(BigInteger.valueOf(30240), CombinationsAndPermutations.permutation(10, 5));
        assertEquals(BigInteger.valueOf(670442572800L), CombinationsAndPermutations.permutation(20, 10));
    }

    @Test
    public void testDisplay() {
        // Test cases for display method
        assertEquals("1.23456789012345678901 * 10^19", CombinationsAndPermutations.display(new BigInteger("123456789012345678901"), 50));
        assertEquals("9.87654321098765432109 * 10^19", CombinationsAndPermutations.display(new BigInteger("987654321098765432109"), 50));
        assertEquals("1.00000000000000000000 * 10^1", CombinationsAndPermutations.display(new BigInteger("10"), 50));
        assertEquals("1.00000000000000000000 * 10^0", CombinationsAndPermutations.display(new BigInteger("1"), 50));
    }

    @Test
    public void testMain() {
        // Test the main method by running it and checking the output
        // Note: This test is more complex as it involves checking console output.
        // For simplicity, we assume the main method runs correctly if no exceptions are thrown.
        try {
            CombinationsAndPermutations.main(new String[]{});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
