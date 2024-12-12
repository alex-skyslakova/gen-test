import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Stack;

public class Game24Test {

    @Test
    public void testApplyOperatorAddition() {
        assertEquals(5.0f, Game24.applyOperator(2.0f, 3.0f, '+'));
    }

    @Test
    public void testApplyOperatorSubtraction() {
        assertEquals(1.0f, Game24.applyOperator(2.0f, 3.0f, '-'));
    }

    @Test
    public void testApplyOperatorMultiplication() {
        assertEquals(6.0f, Game24.applyOperator(2.0f, 3.0f, '*'));
    }

    @Test
    public void testApplyOperatorDivision() {
        assertEquals(1.5f, Game24.applyOperator(2.0f, 3.0f, '/'));
    }

    @Test
    public void testApplyOperatorInvalid() {
        assertTrue(Float.isNaN(Game24.applyOperator(2.0f, 3.0f, '^')));
    }

    @Test
    public void testTallyDigits() {
        int[] digits = {1, 2, 3, 4};
        long expectedTally = (1 << (1 * 5)) + (1 << (2 * 5)) + (1 << (3 * 5)) + (1 << (4 * 5));
        assertEquals(expectedTally, Game24.tallyDigits(digits));
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
    public void testCorrectExpression() {
        Stack<Float> stack = new Stack<>();
        stack.push(6.0f);
        stack.push(6.0f);
        stack.push(4.0f);
        stack.push(1.0f);
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 4 + 1 = 5
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '*')); // 6 * 5 = 30
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '-')); // 30 - 6 = 24
        assertEquals(24.0f, stack.peek(), 0.001f);
    }

    @Test
    public void testIncorrectExpression() {
        Stack<Float> stack = new Stack<>();
        stack.push(6.0f);
        stack.push(6.0f);
        stack.push(4.0f);
        stack.push(1.0f);
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 4 + 1 = 5
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 6 + 5 = 11
        stack.push(Game24.applyOperator(stack.pop(), stack.pop(), '+')); // 11 + 6 = 17
        assertNotEquals(24.0f, stack.peek(), 0.001f);
    }

    @Test
    public void testNotSameDigits() {
        int[] digits = {1, 2, 3, 4};
        long total = (1 << (1 * 5)) + (1 << (2 * 5)) + (1 << (3 * 5)) + (1 << (5 * 5)); // Incorrect tally
        assertNotEquals(Game24.tallyDigits(digits), total);
    }
}
