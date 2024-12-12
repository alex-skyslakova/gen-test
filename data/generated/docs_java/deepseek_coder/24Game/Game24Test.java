import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Stack;

public class Game24Test {

    @Test
    public void testApplyOperatorAddition() {
        float result = Game24.applyOperator(2.0f, 3.0f, '+');
        assertEquals(5.0f, result, 0.001f);
    }

    @Test
    public void testApplyOperatorSubtraction() {
        float result = Game24.applyOperator(2.0f, 3.0f, '-');
        assertEquals(1.0f, result, 0.001f);
    }

    @Test
    public void testApplyOperatorMultiplication() {
        float result = Game24.applyOperator(2.0f, 3.0f, '*');
        assertEquals(6.0f, result, 0.001f);
    }

    @Test
    public void testApplyOperatorDivision() {
        float result = Game24.applyOperator(2.0f, 4.0f, '/');
        assertEquals(2.0f, result, 0.001f);
    }

    @Test
    public void testApplyOperatorInvalid() {
        float result = Game24.applyOperator(2.0f, 3.0f, 'x');
        assertTrue(Float.isNaN(result));
    }

    @Test
    public void testTallyDigits() {
        int[] digits = {1, 2, 3, 4};
        long result = Game24.tallyDigits(digits);
        assertEquals(33026L, result);
    }

    @Test
    public void testRandomDigits() {
        int[] digits = Game24.randomDigits();
        assertEquals(4, digits.length);
        for (int digit : digits) {
            assertTrue(digit >= 1 && digit <= 9);
        }
    }

    @Test
    public void testEvaluateExpressionCorrect() {
        Stack<Float> stack = new Stack<>();
        stack.push(1.0f);
        stack.push(2.0f);
        stack.push(3.0f);
        stack.push(4.0f);
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 3 + 4 = 7
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '*')); // 2 * 7 = 14
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 1 + 14 = 15
        assertEquals(15.0f, stack.peek(), 0.001f);
    }

    @Test
    public void testEvaluateExpressionIncorrect() {
        Stack<Float> stack = new Stack<>();
        stack.push(1.0f);
        stack.push(2.0f);
        stack.push(3.0f);
        stack.push(4.0f);
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 3 + 4 = 7
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '*')); // 2 * 7 = 14
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '-')); // 1 - 14 = -13
        assertEquals(-13.0f, stack.peek(), 0.001f);
    }

    @Test
    public void testTallyDigitsWithDifferentDigits() {
        int[] digits = {5, 6, 7, 8};
        long result = Game24.tallyDigits(digits);
        assertEquals(1015826L, result);
    }

    @Test
    public void testTallyDigitsWithRepeatedDigits() {
        int[] digits = {1, 1, 1, 1};
        long result = Game24.tallyDigits(digits);
        assertEquals(32768L, result);
    }

    @Test
    public void testTallyDigitsWithMixedDigits() {
        int[] digits = {1, 2, 2, 3};
        long result = Game24.tallyDigits(digits);
        assertEquals(33026L, result);
    }
}
