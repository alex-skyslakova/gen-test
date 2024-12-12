import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

public class FractranTest {

    @Test
    public void testCompile() {
        Fractran fractran = new Fractran("17/91 78/85 19/51", 2);
        assertEquals(3, fractran.num.size());
        assertEquals(3, fractran.den.size());
        assertEquals(17, fractran.num.get(0));
        assertEquals(91, fractran.den.get(0));
        assertEquals(78, fractran.num.get(1));
        assertEquals(85, fractran.den.get(1));
        assertEquals(19, fractran.num.get(2));
        assertEquals(51, fractran.den.get(2));
    }

    @Test
    public void testStep() {
        Fractran fractran = new Fractran("15/2 55/1", 2);
        assertEquals(15, fractran.step(2)); // 2 * (15/2) = 15
        assertEquals(825, fractran.step(15)); // 15 * (55/1) = 825
    }

    @Test
    public void testExec() {
        Fractran fractran = new Fractran("15/2 55/1", 2);
        Vector<Integer> expectedSequence = new Vector<>();
        expectedSequence.add(2);
        expectedSequence.add(15);
        expectedSequence.add(825);

        Vector<Integer> actualSequence = new Vector<>();
        int val = 2;
        int n = 0;
        while (val != null && n < fractran.limit) {
            actualSequence.add(val);
            val = fractran.step(val);
            n++;
        }

        assertEquals(expectedSequence, actualSequence);
    }

    @Test
    public void testHaltCondition() {
        Fractran fractran = new Fractran("3/2", 2);
        assertNull(fractran.step(2)); // 2 * (3/2) = 3, but 3 * (3/2) is not an integer
    }
}
