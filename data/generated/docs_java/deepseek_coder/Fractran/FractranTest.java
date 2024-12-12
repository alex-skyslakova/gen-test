import org.junit.jupiter.api.Test;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

public class FractranTest {

    @Test
    public void testCompile() {
        Fractran fractran = new Fractran("17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1", 2);
        Vector<Integer> expectedNum = new Vector<>();
        Vector<Integer> expectedDen = new Vector<>();

        expectedNum.add(17); expectedDen.add(91);
        expectedNum.add(78); expectedDen.add(85);
        expectedNum.add(19); expectedDen.add(51);
        expectedNum.add(23); expectedDen.add(38);
        expectedNum.add(29); expectedDen.add(33);
        expectedNum.add(77); expectedDen.add(29);
        expectedNum.add(95); expectedDen.add(23);
        expectedNum.add(77); expectedDen.add(19);
        expectedNum.add(1); expectedDen.add(17);
        expectedNum.add(11); expectedDen.add(13);
        expectedNum.add(13); expectedDen.add(11);
        expectedNum.add(15); expectedDen.add(14);
        expectedNum.add(15); expectedDen.add(2);
        expectedNum.add(55); expectedDen.add(1);

        assertEquals(expectedNum, fractran.num);
        assertEquals(expectedDen, fractran.den);
    }

    @Test
    public void testStep() {
        Fractran fractran = new Fractran("17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1", 2);
        assertEquals(15, fractran.step(2));
        assertEquals(825, fractran.step(15));
        assertEquals(725, fractran.step(825));
        assertNull(fractran.step(725));
    }

    @Test
    public void testExec() {
        Fractran fractran = new Fractran("17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1", 2);
        fractran.exec(2);
        // Since exec prints to console, we can't directly assert its output.
        // Instead, we can check the state of the program after execution.
        // For example, we can check if the program halted after the limit.
        assertEquals(15, fractran.step(2));
        assertEquals(825, fractran.step(15));
        assertEquals(725, fractran.step(825));
        assertNull(fractran.step(725));
    }

    @Test
    public void testDump() {
        Fractran fractran = new Fractran("17/91 78/85 19/51 23/38 29/33 77/29 95/23 77/19 1/17 11/13 13/11 15/14 15/2 55/1", 2);
        fractran.dump();
        // Since dump prints to console, we can't directly assert its output.
        // Instead, we can check the state of the program after dumping.
        Vector<Integer> expectedNum = new Vector<>();
        Vector<Integer> expectedDen = new Vector<>();

        expectedNum.add(17); expectedDen.add(91);
        expectedNum.add(78); expectedDen.add(85);
        expectedNum.add(19); expectedDen.add(51);
        expectedNum.add(23); expectedDen.add(38);
        expectedNum.add(29); expectedDen.add(33);
        expectedNum.add(77); expectedDen.add(29);
        expectedNum.add(95); expectedDen.add(23);
        expectedNum.add(77); expectedDen.add(19);
        expectedNum.add(1); expectedDen.add(17);
        expectedNum.add(11); expectedDen.add(13);
        expectedNum.add(13); expectedDen.add(11);
        expectedNum.add(15); expectedDen.add(14);
        expectedNum.add(15); expectedDen.add(2);
        expectedNum.add(55); expectedDen.add(1);

        assertEquals(expectedNum, fractran.num);
        assertEquals(expectedDen, fractran.den);
    }
}
