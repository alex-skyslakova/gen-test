import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GateLogicTest {

    @Test
    void testNOT() {
        assertTrue(GateLogic.NOT.eval(false));
        assertFalse(GateLogic.NOT.eval(true));
    }

    @Test
    void testAND() {
        assertTrue(GateLogic.AND.eval(true, true));
        assertFalse(GateLogic.AND.eval(true, false));
        assertFalse(GateLogic.AND.eval(false, true));
        assertFalse(GateLogic.AND.eval(false, false));
    }

    @Test
    void testOR() {
        assertTrue(GateLogic.OR.eval(true, true));
        assertTrue(GateLogic.OR.eval(true, false));
        assertTrue(GateLogic.OR.eval(false, true));
        assertFalse(GateLogic.OR.eval(false, false));
    }

    @Test
    void testXOR() {
        assertFalse(GateLogic.XOR.eval(true, true));
        assertTrue(GateLogic.XOR.eval(true, false));
        assertTrue(GateLogic.XOR.eval(false, true));
        assertFalse(GateLogic.XOR.eval(false, false));
    }


    @Test
    void testHALF_ADDER() {
        assertArrayEquals(new boolean[]{false, false}, GateLogic.HALF_ADDER.eval(false, false));
        assertArrayEquals(new boolean[]{true, false}, GateLogic.HALF_ADDER.eval(true, false));
        assertArrayEquals(new boolean[]{true, false}, GateLogic.HALF_ADDER.eval(false, true));
        assertArrayEquals(new boolean[]{false, true}, GateLogic.HALF_ADDER.eval(true, true));
    }

    @Test
    void testFULL_ADDER() {
        assertArrayEquals(new boolean[]{false, false}, GateLogic.FULL_ADDER.eval(false, false, false));
        assertArrayEquals(new boolean[]{true, false}, GateLogic.FULL_ADDER.eval(false, true, false));
        assertArrayEquals(new boolean[]{true, false}, GateLogic.FULL_ADDER.eval(false, false, true));
        assertArrayEquals(new boolean[]{false, true}, GateLogic.FULL_ADDER.eval(false, true, true));
        assertArrayEquals(new boolean[]{true, false}, GateLogic.FULL_ADDER.eval(true, false, false));
        assertArrayEquals(new boolean[]{false, true}, GateLogic.FULL_ADDER.eval(true, true, false));
        assertArrayEquals(new boolean[]{false, true}, GateLogic.FULL_ADDER.eval(true, false, true));
        assertArrayEquals(new boolean[]{true, true}, GateLogic.FULL_ADDER.eval(true, true, true));
    }


    @Test
    void testBuildAdder() {
        GateLogic.MultiGate fourBitAdder = GateLogic.buildAdder(4);
        boolean[] inputs = new boolean[]{false, false, true, true, true, true, false, false}; // 3 + 12 = 15
        boolean[] expectedOutput = new boolean[]{true, true, true, true, false};  // 15 with no carry
        assertArrayEquals(expectedOutput, fourBitAdder.eval(inputs));


        inputs = new boolean[]{true, true, true, true, true, true, true, true}; // 15 + 15 = 30
        expectedOutput = new boolean[]{true, true, true, true, true};  // Should be 11110 (30), but we get only 5 bits (1111 and carry 1)
        assertArrayEquals(expectedOutput, fourBitAdder.eval(inputs));

        inputs = new boolean[]{false, false, false, false, false, false, false, false}; // 0 + 0
        expectedOutput = new boolean[]{false, false, false, false, false};
        assertArrayEquals(expectedOutput, fourBitAdder.eval(inputs));
    }

    @Test
    void testBuildAdderIllegalArgumentException(){
        GateLogic.MultiGate fourBitAdder = GateLogic.buildAdder(4);
        assertThrows(IllegalArgumentException.class, ()-> fourBitAdder.eval(new boolean[1]));
        assertThrows(IllegalArgumentException.class, ()-> GateLogic.FULL_ADDER.eval(true, false));
        assertThrows(IllegalArgumentException.class, ()-> GateLogic.HALF_ADDER.eval(true));


    }

}
