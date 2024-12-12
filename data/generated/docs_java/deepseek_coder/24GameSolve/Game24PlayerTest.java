import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Game24PlayerTest {

    private Game24Player game24Player = new Game24Player();

    @Test
    public void testValidate_ValidInput() throws Exception {
        char[] input = {'1', '2', '+', '3', '*', '4'};
        game24Player.digits = Arrays.asList(1, 2, 3, 4);
        game24Player.validate(input);
    }

    @Test
    public void testValidate_ParenthesesMismatch() {
        char[] input = {'1', '(', '2', '+', '3', ')'};
        game24Player.digits = Arrays.asList(1, 2, 3, 4);
        Exception exception = assertThrows(Exception.class, () -> {
            game24Player.validate(input);
        });
        assertEquals("Parentheses mismatch.", exception.getMessage());
    }

    @Test
    public void testValidate_WrongNumberOfOperators() {
        char[] input = {'1', '2', '+', '3'};
        game24Player.digits = Arrays.asList(1, 2, 3, 4);
        Exception exception = assertThrows(Exception.class, () -> {
            game24Player.validate(input);
        });
        assertEquals("Wrong number of operators.", exception.getMessage());
    }

    @Test
    public void testValidate_NotTheSameDigits() {
        char[] input = {'1', '2', '+', '3', '*', '5'};
        game24Player.digits = Arrays.asList(1, 2, 3, 4);
        Exception exception = assertThrows(Exception.class, () -> {
            game24Player.validate(input);
        });
        assertEquals("Not the same digits.", exception.getMessage());
    }

    @Test
    public void testEvaluate_CorrectExpression() throws Exception {
        char[] postfix = {'1', '2', '+', '3', '*', '4', '*'};
        assertTrue(game24Player.evaluate(postfix));
    }

    @Test
    public void testEvaluate_IncorrectExpression() throws Exception {
        char[] postfix = {'1', '2', '+', '3', '*', '5', '*'};
        assertFalse(game24Player.evaluate(postfix));
    }

    @Test
    public void testApplyOperator_Addition() {
        assertEquals(5.0f, game24Player.applyOperator(2.0f, 3.0f, '+'));
    }

    @Test
    public void testApplyOperator_Subtraction() {
        assertEquals(1.0f, game24Player.applyOperator(2.0f, 3.0f, '-'));
    }

    @Test
    public void testApplyOperator_Multiplication() {
        assertEquals(6.0f, game24Player.applyOperator(2.0f, 3.0f, '*'));
    }

    @Test
    public void testApplyOperator_Division() {
        assertEquals(1.5f, game24Player.applyOperator(2.0f, 3.0f, '/'));
    }

    @Test
    public void testRandomDigits() {
        List<Integer> digits = game24Player.randomDigits();
        assertEquals(4, digits.size());
        for (int digit : digits) {
            assertTrue(digit >= 1 && digit <= 9);
        }
    }

    @Test
    public void testGetSolvableDigits() {
        List<Integer> digits = game24Player.getSolvableDigits();
        assertTrue(game24Player.isSolvable(digits));
    }

    @Test
    public void testIsSolvable_True() {
        List<Integer> digits = Arrays.asList(1, 2, 3, 4);
        assertTrue(game24Player.isSolvable(digits));
    }

    @Test
    public void testIsSolvable_False() {
        List<Integer> digits = Arrays.asList(1, 1, 1, 1);
        assertFalse(game24Player.isSolvable(digits));
    }

    @Test
    public void testPostfixToInfix() {
        String postfix = "12+3*4*";
        String infix = game24Player.postfixToInfix(postfix);
        assertEquals("((1 + 2) * 3) * 4", infix);
    }

    @Test
    public void testInfixToPostfix() throws Exception {
        char[] infix = {'1', '+', '2', '*', '3', '*', '4'};
        char[] postfix = game24Player.infixToPostfix(infix);
        assertArrayEquals(new char[]{'1', '2', '3', '*', '+', '4', '*'}, postfix);
    }

    @Test
    public void testPermute() {
        List<Integer> lst = Arrays.asList(1, 2, 3);
        Set<List<Integer>> res = new HashSet<>();
        game24Player.permute(lst, res, 0);
        assertEquals(6, res.size());
    }

    @Test
    public void testPermuteOperators() {
        List<List<Integer>> res = new ArrayList<>();
        game24Player.permuteOperators(res, 4, 64);
        assertEquals(64, res.size());
    }
}
