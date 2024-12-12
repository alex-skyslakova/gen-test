import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

public class Game24Test {

    @Test
    void testApplyOperatorAddition() {
        assertEquals(7, Game24.applyOperator(2, 5, '+'));
    }

    @Test
    void testApplyOperatorSubtraction() {
        assertEquals(3, Game24.applyOperator(2, 5, '-'));
    }

    @Test
    void testApplyOperatorMultiplication() {
        assertEquals(10, Game24.applyOperator(2, 5, '*'));
    }

    @Test
    void testApplyOperatorDivision() {
        assertEquals(2.5, Game24.applyOperator(2, 5, '/'));
    }

    @Test
    void testApplyOperatorInvalid() {
        assertEquals(Float.NaN, Game24.applyOperator(2, 5, 'a'));
    }


    @Test
    void testTallyDigits() {
        int[] digits = {1, 2, 3, 4};
        long expectedTally = (1L << (1 * 5)) + (1L << (2 * 5)) + (1L << (3 * 5)) + (1L << (4 * 5));
        assertEquals(expectedTally, Game24.tallyDigits(digits));
    }

    @Test
    void testRandomDigits() {
        int[] digits = Game24.randomDigits();
        assertEquals(4, digits.length);
        for (int digit : digits) {
            assertTrue(digit >= 1 && digit <= 9);
        }
    }

    @Test
    void testRandomDigitsRandomness() { //indirect test for randomness - unlikely to get same sequence twice
        int[] digits1 = Game24.randomDigits();
        int[] digits2 = Game24.randomDigits();
        assertNotEquals(Arrays.toString(digits1), Arrays.toString(digits2));
    }

    @Test
    void testMainCorrect() { //Simulate correct user input
        Game24.r = new Random(1); //for predictable digits in test - 1,8,3,7 will be generated
        TestInputScanner in = new TestInputScanner("8*3");
        Game24.main(new String[0]);
        assertTrue(in.getOutput().contains("Correct!"));
    }

    @Test
    void testMainIncorrect() { //Simulate incorrect user input
        Game24.r = new Random(1); //for predictable digits in test - 1,8,3,7 will be generated
        TestInputScanner in = new TestInputScanner("7+1");
        Game24.main(new String[0]);
        assertTrue(in.getOutput().contains("Not correct."));
    }


    @Test
    void testMainWrongDigits() {//Simulate incorrect user input (wrong digits used)
        Game24.r = new Random(1); //for predictable digits in test - 1,8,3,7 will be generated
        TestInputScanner in = new TestInputScanner("9*2"); 
        Game24.main(new String[0]);
        assertTrue(in.getOutput().contains("Not the same digits."));
    }


}




//Helper class for testing with simulated user input
class TestInputScanner extends java.util.Scanner {

    private final String input;
    private StringBuilder output = new StringBuilder();

    public TestInputScanner(String input) {
        super(input);
        this.input = input;
    }

    @Override
    public String nextLine() {
        return input;
    }

    @Override
    public void print(String s) {
        output.append(s);
    }

    @Override
    public void println(String s) {
        output.append(s).append(System.lineSeparator());
    }

    public String getOutput() {
        return output.toString();
    }
}


