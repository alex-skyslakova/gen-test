import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.regex.Pattern;

public class GuessingTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void testCorrectGuessOnFirstTry() {
        // Assuming the random number generated is 5
        systemInMock.provideLines("5");
        exit.expectSystemExit();
        
        Guessing.main(new String[]{});
        
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Guess the number between 1 and 10: "));
        assertTrue(output.contains("Well guessed!"));
    }

    @Test
    public void testIncorrectThenCorrectGuess() {
        // Assuming the random number generated is 7
        systemInMock.provideLines("3", "7");
        exit.expectSystemExit();
        
        Guessing.main(new String[]{});
        
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Guess the number between 1 and 10: "));
        assertTrue(output.contains("Wrong! Guess again: "));
        assertTrue(output.contains("Well guessed!"));
    }

    @Test
    public void testMultipleIncorrectGuesses() {
        // Assuming the random number generated is 2
        systemInMock.provideLines("1", "3", "4", "2");
        exit.expectSystemExit();
        
        Guessing.main(new String[]{});
        
        String output = systemOutRule.getLog();
        assertTrue(output.contains("Guess the number between 1 and 10: "));
        assertTrue(output.contains("Wrong! Guess again: "));
        assertTrue(output.contains("Well guessed!"));
    }
}
