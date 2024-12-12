import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GateLogicTest {

    @Test
    public void testNOTGate() {
        GateLogic.OneInputGate notGate = GateLogic.NOT;
        assertFalse(notGate.eval(true));
        assertTrue(notGate.eval(false));
    }

    @Test
    public void testANDGate() {
        GateLogic.TwoInputGate andGate = GateLogic.AND;
        assertTrue(andGate.eval(true, true));
        assertFalse(andGate.eval(true, false));
        assertFalse(andGate.eval(false, true));
        assertFalse(andGate.eval(false, false));
    }

    @Test
    public void testORGate() {
        GateLogic.TwoInputGate orGate = GateLogic.OR;
        assertTrue(orGate.eval(true, true));
        assertTrue(orGate.eval(true, false));
        assertTrue(orGate.eval(false, true));
        assertFalse(orGate.eval(false, false));
    }

    @Test
    public void testXORGate() {
        GateLogic.TwoInputGate xorGate = GateLogic.XOR;
        assertFalse(xorGate.eval(true, true));
        assertTrue(xorGate.eval(true, false));
        assertTrue(xorGate.eval(false, true));
        assertFalse(xorGate.eval(false, false));
    }

    @Test
    public void testHALF_ADDER() {
        GateLogic.MultiGate halfAdder = GateLogic.HALF_ADDER;
        assertArrayEquals(new boolean[]{false, false}, halfAdder.eval(false, false));
        assertArrayEquals(new boolean[]{true, false}, halfAdder.eval(true, false));
        assertArrayEquals(new boolean[]{true, false}, halfAdder.eval(false, true));
        assertArrayEquals(new boolean[]{false, true}, halfAdder.eval(true, true));
    }

    @Test
    public void testFULL_ADDER() {
        GateLogic.MultiGate fullAdder = GateLogic.FULL_ADDER;
        assertArrayEquals(new boolean[]{false, false}, fullAdder.eval(false, false, false));
        assertArrayEquals(new boolean[]{true, false}, fullAdder.eval(false, true, false));
        assertArrayEquals(new boolean[]{true, false}, fullAdder.eval(false, false, true));
        assertArrayEquals(new boolean[]{false, true}, fullAdder.eval(false, true, true));
        assertArrayEquals(new boolean[]{true, false}, fullAdder.eval(true, false, false));
        assertArrayEquals(new boolean[]{false, true}, fullAdder.eval(true, true, false));
        assertArrayEquals(new boolean[]{false, true}, fullAdder.eval(true, false, true));
        assertArrayEquals(new boolean[]{true, true}, fullAdder.eval(true, true, true));
    }

    @Test
    public void test4BitAdder() {
        GateLogic.MultiGate fourBitAdder = GateLogic.buildAdder(4);

        // Test case 1: 0000 + 0000 = 0000 (0 + 0 = 0)
        boolean[] inputs1 = {false, false, false, false, false, false, false, false};
        assertArrayEquals(new boolean[]{false, false, false, false, false}, fourBitAdder.eval(inputs1));

        // Test case 2: 1111 + 0001 = 10000 (15 + 1 = 16)
        boolean[] inputs2 = {true, true, true, true, false, false, false, true};
        assertArrayEquals(new boolean[]{true, false, false, false, false}, fourBitAdder.eval(inputs2));

        // Test case 3: 1010 + 0101 = 1111 (10 + 5 = 15)
        boolean[] inputs3 = {true, false, true, false, false, true, false, true};
        assertArrayEquals(new boolean[]{false, true, true, true, true}, fourBitAdder.eval(inputs3));

        // Test case 4: 1100 + 0011 = 1111 (12 + 3 = 15)
        boolean[] inputs4 = {true, true, false, false, false, false, true, true};
        assertArrayEquals(new boolean[]{false, true, true, true, true}, fourBitAdder.eval(inputs4));
    }
}
