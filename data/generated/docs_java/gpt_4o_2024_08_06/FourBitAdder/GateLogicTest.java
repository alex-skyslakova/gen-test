import org.junit.Test;
import static org.junit.Assert.*;

public class GateLogicTest {

    @Test
    public void testNOTGate() {
        assertFalse(GateLogic.NOT.eval(true));
        assertTrue(GateLogic.NOT.eval(false));
    }

    @Test
    public void testANDGate() {
        assertTrue(GateLogic.AND.eval(true, true));
        assertFalse(GateLogic.AND.eval(true, false));
        assertFalse(GateLogic.AND.eval(false, true));
        assertFalse(GateLogic.AND.eval(false, false));
    }

    @Test
    public void testORGate() {
        assertTrue(GateLogic.OR.eval(true, true));
        assertTrue(GateLogic.OR.eval(true, false));
        assertTrue(GateLogic.OR.eval(false, true));
        assertFalse(GateLogic.OR.eval(false, false));
    }

    @Test
    public void testXORGate() {
        assertFalse(GateLogic.XOR.eval(true, true));
        assertTrue(GateLogic.XOR.eval(true, false));
        assertTrue(GateLogic.XOR.eval(false, true));
        assertFalse(GateLogic.XOR.eval(false, false));
    }

    @Test
    public void testHalfAdder() {
        boolean[] result = GateLogic.HALF_ADDER.eval(false, false);
        assertFalse(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.HALF_ADDER.eval(false, true);
        assertTrue(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.HALF_ADDER.eval(true, false);
        assertTrue(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.HALF_ADDER.eval(true, true);
        assertFalse(result[0]); // Sum
        assertTrue(result[1]); // Carry
    }

    @Test
    public void testFullAdder() {
        boolean[] result = GateLogic.FULL_ADDER.eval(false, false, false);
        assertFalse(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(false, false, true);
        assertTrue(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(false, true, false);
        assertTrue(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(false, true, true);
        assertFalse(result[0]); // Sum
        assertTrue(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(true, false, false);
        assertTrue(result[0]); // Sum
        assertFalse(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(true, false, true);
        assertFalse(result[0]); // Sum
        assertTrue(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(true, true, false);
        assertFalse(result[0]); // Sum
        assertTrue(result[1]); // Carry

        result = GateLogic.FULL_ADDER.eval(true, true, true);
        assertTrue(result[0]); // Sum
        assertTrue(result[1]); // Carry
    }

    @Test
    public void test4BitAdder() {
        GateLogic.MultiGate adder = GateLogic.buildAdder(4);

        // Test 0 + 0 = 0
        boolean[] result = adder.eval(false, false, false, false, false, false, false, false);
        assertArrayEquals(new boolean[]{false, false, false, false, false}, result);

        // Test 1 + 1 = 2
        result = adder.eval(true, false, false, false, true, false, false, false);
        assertArrayEquals(new boolean[]{false, true, false, false, false}, result);

        // Test 15 + 1 = 16
        result = adder.eval(true, true, true, true, true, false, false, false);
        assertArrayEquals(new boolean[]{false, false, false, false, true}, result);

        // Test 7 + 8 = 15
        result = adder.eval(true, true, true, false, false, false, false, true);
        assertArrayEquals(new boolean[]{true, true, true, true, false}, result);

        // Test 8 + 8 = 16
        result = adder.eval(false, false, false, true, false, false, false, true);
        assertArrayEquals(new boolean[]{false, false, false, false, true}, result);
    }
}
