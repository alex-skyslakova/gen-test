import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class EKGSequenceConvergenceTest {

    @Test
    void testEKG2() {
        List<Integer> expected = List.of(1, 2, 4, 6, 3, 9, 12, 8, 10, 5);
        List<Integer> actual = EKGSequenceConvergence.ekg(2, 10);
        assertEquals(expected, actual);
    }

    @Test
    void testEKG5() {
        List<Integer> expected = List.of(1, 5, 10, 2, 4, 8, 16, 6, 12, 3);
        List<Integer> actual = EKGSequenceConvergence.ekg(5, 10);
        assertEquals(expected, actual);
    }

    @Test
    void testEKG7() {
        List<Integer> expected = List.of(1, 7, 14, 2, 4, 8, 16, 6, 12, 3);
        List<Integer> actual = EKGSequenceConvergence.ekg(7, 10);
        assertEquals(expected, actual);
    }

    @Test
    void testEKG9() {
        List<Integer> expected = List.of(1, 9, 3, 6, 12, 4, 8, 16, 2, 10);
        List<Integer> actual = EKGSequenceConvergence.ekg(9, 10);
        assertEquals(expected, actual);
    }

    @Test
    void testEKG10() {
        List<Integer> expected = List.of(1, 10, 2, 4, 8, 16, 6, 12, 3, 9);
        List<Integer> actual = EKGSequenceConvergence.ekg(10, 10);
        assertEquals(expected, actual);
    }


    @Test
    void testSameSeq() {
        List<Integer> seq1 = List.of(1, 5, 10, 2, 4, 8, 16, 6, 12, 3);
        List<Integer> seq2 = List.of(1, 7, 14, 2, 4, 8, 16, 6, 12, 3);
        assertTrue(EKGSequenceConvergence.sameSeq(seq1, seq2, 6)); // Converge at the 7th term (index 6)
        assertFalse(EKGSequenceConvergence.sameSeq(seq1, seq2, 1));
     }

    @Test
    void testGCD() {
        assertEquals(5, EKGSequenceConvergence.gcd(5, 10));
        assertEquals(1, EKGSequenceConvergence.gcd(5, 7));
        assertEquals(4, EKGSequenceConvergence.gcd(8, 12));
        assertEquals(6, EKGSequenceConvergence.gcd(6,12));
        assertEquals(6, EKGSequenceConvergence.gcd(12, 6));


    }


}
