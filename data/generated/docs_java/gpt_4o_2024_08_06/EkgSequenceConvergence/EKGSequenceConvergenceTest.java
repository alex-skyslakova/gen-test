import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.List;

public class EKGSequenceConvergenceTest {

    @Test
    public void testEKG2() {
        List<Integer> expected = List.of(1, 2, 4, 6, 3, 9, 12, 8, 10, 5);
        assertEquals(expected, EKGSequenceConvergence.ekg(2, 10));
    }

    @Test
    public void testEKG5() {
        List<Integer> expected = List.of(1, 5, 10, 2, 4, 8, 6, 3, 9, 12);
        assertEquals(expected, EKGSequenceConvergence.ekg(5, 10));
    }

    @Test
    public void testEKG7() {
        List<Integer> expected = List.of(1, 7, 14, 2, 4, 8, 10, 5, 15, 3);
        assertEquals(expected, EKGSequenceConvergence.ekg(7, 10));
    }

    @Test
    public void testEKG9() {
        List<Integer> expected = List.of(1, 9, 3, 6, 2, 4, 8, 10, 5, 15);
        assertEquals(expected, EKGSequenceConvergence.ekg(9, 10));
    }

    @Test
    public void testEKG10() {
        List<Integer> expected = List.of(1, 10, 2, 4, 8, 6, 3, 9, 12, 14);
        assertEquals(expected, EKGSequenceConvergence.ekg(10, 10));
    }

    @Test
    public void testConvergenceEKG5AndEKG7() {
        List<Integer> ekg5 = EKGSequenceConvergence.ekg(5, 100);
        List<Integer> ekg7 = EKGSequenceConvergence.ekg(7, 100);
        int convergenceIndex = -1;
        for (int i = 1; i < ekg5.size(); i++) {
            if (ekg5.get(i).equals(ekg7.get(i)) && EKGSequenceConvergence.sameSeq(ekg5, ekg7, i)) {
                convergenceIndex = i + 1; // Convert to 1-based index
                break;
            }
        }
        assertEquals(18, convergenceIndex); // Assuming convergence happens at term 18
    }
}
