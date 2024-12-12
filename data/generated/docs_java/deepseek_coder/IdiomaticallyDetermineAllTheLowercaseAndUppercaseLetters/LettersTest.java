import org.junit.jupiter.api.Test;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

public class LettersTest {

    @Test
    public void testUpperCaseLetters() {
        // Capture the output of the upper case letters
        StringBuilder upperCaseLetters = new StringBuilder();
        IntStream.rangeClosed(0, 0x10FFFF)
                 .filter(Character::isUpperCase)
                 .limit(72)
                 .forEach(n -> upperCaseLetters.append((char) n));

        // Expected upper case letters in the Latin alphabet
        String expectedUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Assert that the captured letters match the expected upper case letters
        assertEquals(expectedUpperCase, upperCaseLetters.toString());
    }

    @Test
    public void testLowerCaseLetters() {
        // Capture the output of the lower case letters
        StringBuilder lowerCaseLetters = new StringBuilder();
        IntStream.rangeClosed(0, 0x10FFFF)
                 .filter(Character::isLowerCase)
                 .limit(72)
                 .forEach(n -> lowerCaseLetters.append((char) n));

        // Expected lower case letters in the Latin alphabet
        String expectedLowerCase = "abcdefghijklmnopqrstuvwxyz";

        // Assert that the captured letters match the expected lower case letters
        assertEquals(expectedLowerCase, lowerCaseLetters.toString());
    }

    @Test
    public void testUpperCaseLettersLimit() {
        // Capture the output of the upper case letters
        StringBuilder upperCaseLetters = new StringBuilder();
        IntStream.rangeClosed(0, 0x10FFFF)
                 .filter(Character::isUpperCase)
                 .limit(72)
                 .forEach(n -> upperCaseLetters.append((char) n));

        // Assert that the captured letters are exactly 72 characters long
        assertEquals(72, upperCaseLetters.length());
    }

    @Test
    public void testLowerCaseLettersLimit() {
        // Capture the output of the lower case letters
        StringBuilder lowerCaseLetters = new StringBuilder();
        IntStream.rangeClosed(0, 0x10FFFF)
                 .filter(Character::isLowerCase)
                 .limit(72)
                 .forEach(n -> lowerCaseLetters.append((char) n));

        // Assert that the captured letters are exactly 72 characters long
        assertEquals(72, lowerCaseLetters.length());
    }
}
