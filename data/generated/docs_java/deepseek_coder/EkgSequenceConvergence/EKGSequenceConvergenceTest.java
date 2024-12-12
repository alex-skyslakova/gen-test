import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EKGSequenceConvergenceTest {

    @Test
    public void testEKG2() {
        List<Integer> expected = Arrays.asList(1, 2, 4, 6, 3, 9, 12, 8, 10, 5);
        List<Integer> result = EKGSequenceConvergence.ekg(2, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testEKG5() {
        List<Integer> expected = Arrays.asList(1, 5, 10, 2, 4, 6, 3, 9, 12, 8);
        List<Integer> result = EKGSequenceConvergence.ekg(5, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testEKG7() {
        List<Integer> expected = Arrays.asList(1, 7, 14, 2, 4, 6, 3, 9, 12, 8);
        List<Integer> result = EKGSequenceConvergence.ekg(7, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testEKG9() {
        List<Integer> expected = Arrays.asList(1, 9, 3, 6, 2, 4, 8, 10, 5, 15);
        List<Integer> result = EKGSequenceConvergence.ekg(9, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testEKG10() {
        List<Integer> expected = Arrays.asList(1, 10, 2, 4, 6, 3, 9, 12, 8, 14);
        List<Integer> result = EKGSequenceConvergence.ekg(10, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testConvergenceEKG5AndEKG7() {
        List<Integer> ekg5 = EKGSequenceConvergence.ekg(5, 100);
        List<Integer> ekg7 = EKGSequenceConvergence.ekg(7, 100);
        boolean foundConvergence = false;
        for (int i = 1; i < ekg5.size(); i++) {
            if (ekg5.get(i) == ekg7.get(i) && EKGSequenceConvergence.sameSeq(ekg5, ekg7, i)) {
                foundConvergence = true;
                assertEquals(21, i + 1); // Expected convergence at term 21
                break;
            }
        }
        assertTrue(foundConvergence, "Convergence not found for EKG(5) and EKG(7)");
    }

    @Test
    public void testSameSeq() {
        List<Integer> seq1 = Arrays.asList(1, 2, 4, 6, 3, 9, 12, 8, 10, 5);
        List<Integer> seq2 = Arrays.asList(1, 2, 4, 6, 3, 9, 12, 8, 10, 5);
        assertTrue(EKGSequenceConvergence.sameSeq(seq1, seq2, 10));

        List<Integer> seq3 = Arrays.asList(1, 2, 4, 6, 3, 9, 12, 8, 10, 5);
        List<Integer> seq4 = Arrays.asList(1, 2, 4, 6, 3, 9, 12, 8, 10, 7);
        assertFalse(EKGSequenceConvergence.sameSeq(seq3, seq4, 10));
    }

    @Test
    public void testGCD() {
        assertEquals(1, EKGSequenceConvergence.gcd(3, 5));
        assertEquals(2, EKGSequenceConvergence.gcd(4, 6));
        assertEquals(3, EKGSequenceConvergence.gcd(9, 6));
        assertEquals(5, EKGSequenceConvergence.gcd(10, 15));
    }
}
