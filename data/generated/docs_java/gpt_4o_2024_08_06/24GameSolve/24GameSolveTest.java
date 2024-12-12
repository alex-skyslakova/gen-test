import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Game24PlayerTest {

    private Game24Player game24Player;

    @BeforeEach
    void setUp() {
        game24Player = new Game24Player();
    }

    @Test
    void testValidateCorrectInput() throws Exception {
        game24Player.digits = List.of(1, 2, 3, 4);
        char[] input = {'1', '+', '2', '+', '3', '+', '4'};
        assertDoesNotThrow(() -> game24Player.validate(input));
    }

    @Test
    void testValidateIncorrectParentheses() {
        game24Player.digits = List.of(1, 2, 3, 4);
        char[] input = {'(', '1', '+', '2', ')', '+', '3', '+', '4', ')'};
        Exception exception = assertThrows(Exception.class, () -> game24Player.validate(input));
        assertEquals("Parentheses mismatch.", exception.getMessage());
    }

    @Test
    void testValidateWrongNumberOfOperators() {
        game24Player.digits = List.of(1, 2, 3, 4);
        char[] input = {'1', '+', '2', '+', '3'};
        Exception exception = assertThrows(Exception.class, () -> game24Player.validate(input));
        assertEquals("Wrong number of operators.", exception.getMessage());
    }

    @Test
    void testValidateNotSameDigits() {
        game24Player.digits = List.of(1, 2, 3, 4);
        char[] input = {'1', '+', '2', '+', '2', '+', '4'};
        Exception exception = assertThrows(Exception.class, () -> game24Player.validate(input));
        assertEquals("Not the same digits.", exception.getMessage());
    }

    @Test
    void testEvaluateCorrectExpression() throws Exception {
        char[] postfix = {'1', '2', '+', '3', '4', '+', '+'};
        assertTrue(game24Player.evaluate(postfix));
    }

    @Test
    void testEvaluateIncorrectExpression() throws Exception {
        char[] postfix = {'1', '2', '+', '3', '4', '-'};
        assertFalse(game24Player.evaluate(postfix));
    }

    @Test
    void testApplyOperatorAddition() {
        assertEquals(5.0, game24Player.applyOperator(2, 3, '+'));
    }

    @Test
    void testApplyOperatorSubtraction() {
        assertEquals(1.0, game24Player.applyOperator(2, 3, '-'));
    }

    @Test
    void testApplyOperatorMultiplication() {
        assertEquals(6.0, game24Player.applyOperator(2, 3, '*'));
    }

    @Test
    void testApplyOperatorDivision() {
        assertEquals(1.5, game24Player.applyOperator(2, 3, '/'));
    }

    @Test
    void testRandomDigits() {
        List<Integer> digits = game24Player.randomDigits();
        assertEquals(4, digits.size());
        digits.forEach(digit -> assertTrue(digit >= 1 && digit <= 9));
    }

    @Test
    void testIsSolvable() {
        List<Integer> solvableDigits = List.of(1, 3, 4, 6);
        assertTrue(game24Player.isSolvable(solvableDigits));
    }

    @Test
    void testInfixToPostfix() throws Exception {
        char[] infix = {'1', '+', '2', '*', '3'};
        char[] expectedPostfix = {'1', '2', '3', '*', '+'};
        assertArrayEquals(expectedPostfix, game24Player.infixToPostfix(infix));
    }

    @Test
    void testPostfixToInfix() {
        String postfix = "12+34+*";
        String expectedInfix = "(1 + 2) * (3 + 4)";
        assertEquals(expectedInfix, game24Player.postfixToInfix(postfix));
    }
}
