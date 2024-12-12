import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DammAlgorithmTest {

    @Test
    void testValidNumbers() {
        assertTrue(DammAlgorithm.damm("5724"));
        assertTrue(DammAlgorithm.damm("112946"));
    }

    @Test
    void testInvalidNumbers() {
        assertFalse(DammAlgorithm.damm("5727"));
        assertFalse(DammAlgorithm.damm("112949"));
        assertFalse(DammAlgorithm.damm("123")); //Additional test case
        assertFalse(DammAlgorithm.damm("0"));
        assertFalse(DammAlgorithm.damm(""));
        assertFalse(DammAlgorithm.damm("1234567890")); //Longer number

    }

    @Test
    void testSingleDigitError() {
        assertTrue(DammAlgorithm.damm("5724"));
        assertFalse(DammAlgorithm.damm("5734"));  //Changed 2 to 3
        assertFalse(DammAlgorithm.damm("5725")); //Changed 4 to 5
        assertFalse(DammAlgorithm.damm("6724")); //Changed 5 to 6
    }
    
    @Test
    void testAdjacentTranspositionError(){
       assertTrue(DammAlgorithm.damm("5724"));
       assertFalse(DammAlgorithm.damm("7524"));
       assertFalse(DammAlgorithm.damm("5274"));
       assertFalse(DammAlgorithm.damm("5742"));


    }

}
