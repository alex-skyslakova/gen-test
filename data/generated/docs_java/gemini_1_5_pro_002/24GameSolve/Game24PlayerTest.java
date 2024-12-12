import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class Game24PlayerTest {

    Game24Player game = new Game24Player();

    @Test
    void testValidate_validInput() throws Exception {
        List<Integer> digits = Arrays.asList(1, 2, 3, 4);
        game.digits = digits;
        game.validate("1+2+3+4".toCharArray()); // Valid expression
        game.validate("(1+2)*(3+4)".toCharArray()); // Valid expression with parentheses
    }

    @Test
    void testValidate_parenthesesMismatch() {
        game.digits = Arrays.asList(1, 2, 3, 4);
        assertThrows(Exception.class, () -> game.validate("(1+2*(3+4".toCharArray())); // Missing closing parenthesis
        assertThrows(Exception.class, () -> game.validate("1+2)*3+4)".toCharArray()); // Extra closing parenthesis
    }

    @Test
    void testValidate_wrongNumberOfOperators() {
        game.digits = Arrays.asList(1, 2, 3, 4);
        assertThrows(Exception.class, () -> game.validate("1+2+3".toCharArray())); // Too few operators
        assertThrows(Exception.class, () -> game.validate("1+2+3+4+5".toCharArray())); // Too many operators

    }

    @Test
    void testValidate_wrongDigits() {
        game.digits = Arrays.asList(1, 2, 3, 4);
        assertThrows(Exception.class, () -> game.validate("5+6+7+8".toCharArray())); // Different digits
        assertThrows(Exception.class, () -> game.validate("1+2+3+3".toCharArray())); // Repeated digit

    }

    @Test
    void testEvaluate_correct() throws Exception {
        assertTrue(game.evaluate("12+34+".toCharArray())); // Simple addition
        assertTrue(game.evaluate("43*1+".toCharArray()));
        assertTrue(game.evaluate("34+12+".toCharArray()));
        assertTrue(game.evaluate("82/43*-".toCharArray()));  // Correct result

    }
    @Test
    void testEvaluate_incorrect() throws Exception {
        assertFalse(game.evaluate("12+35+".toCharArray())); // Incorrect result
        assertFalse(game.evaluate("12*34+".toCharArray())); // Incorrect result
    }



    @Test
    void testEvaluate_invalidEntry() {
        assertThrows(Exception.class, () -> game.evaluate("12+3+".toCharArray())); // Invalid postfix expression
        assertThrows(Exception.class, () -> game.evaluate("+123".toCharArray())); // Invalid postfix expression

    }



    @Test
    void testApplyOperator() {
        assertEquals(7, game.applyOperator(3, 4, '+'));
        assertEquals(1, game.applyOperator(4, 3, '-'));
        assertEquals(12, game.applyOperator(3, 4, '*'));
        assertEquals(2, game.applyOperator(4, 8, '/'));


    }


    @Test
    void testInfixToPostfix() throws Exception {
        assertArrayEquals("12+34+*".toCharArray(), game.infixToPostfix("1+2*3+4".toCharArray()));
        assertArrayEquals("12+3*4+".toCharArray(), game.infixToPostfix("1+2*3+4".toCharArray()));
        assertArrayEquals("123++4*".toCharArray(), game.infixToPostfix("(1+2+3)*4".toCharArray()));

    }


    @Test
    void testInfixToPostfix_invalidEntry() {
        assertThrows(Exception.class, () -> game.infixToPostfix("(1+2*3+4".toCharArray()));  //Unmatched Parenthesis
    }



    @Test
    void testPostfixToInfix() {
        assertEquals("1 + 2 * 3 + 4", game.postfixToInfix("123*+4+"));
        assertEquals("(1 + 2) * (3 + 4)", game.postfixToInfix("12+34+*"));
        assertEquals("((1 + 2 + 3) * 4)", game.postfixToInfix("123++4*"));
    }


    @Test
    void testPermuteOperators() {
        List<List<Integer>> res = new ArrayList<>();
        game.permuteOperators(res, 4, 64);
        assertEquals(64, res.size());

    }

    @Test
    void testGetSolvableDigits() {
        List<Integer> digits = game.getSolvableDigits();
        assertEquals(4, digits.size());
        assertTrue(game.isSolvable(digits));
    }



}
