import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;
import java.util.Set;
import java.util.HashSet;

public class LettersTest {

    @Test
    public void testUpperCaseLetters() {
        Set<Character> expectedUpperCaseLetters = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            expectedUpperCaseLetters.add(c);
        }

        Set<Character> actualUpperCaseLetters = new HashSet<>();
        IntStream.rangeClosed(0, 0x10FFFF)
                 .filter(Character::isUpperCase)
                 .limit(72)
                 .forEach(n -> actualUpperCaseLetters.add((char) n));

        assertTrue(actualUpperCaseLetters.containsAll(expectedUpperCaseLetters), 
                   "The set of uppercase letters does not match the expected set.");
    }

    @Test
    public void testLowerCaseLetters() {
        Set<Character> expectedLowerCaseLetters = new HashSet<>();
        for (char c = 'a'; c <= 'z'; c++) {
            expectedLowerCaseLetters.add(c);
        }

        Set<Character> actualLowerCaseLetters = new HashSet<>();
        IntStream.rangeClosed(0, 0x10FFFF)
                 .filter(Character::isLowerCase)
                 .limit(72)
                 .forEach(n -> actualLowerCaseLetters.add((char) n));

        assertTrue(actualLowerCaseLetters.containsAll(expectedLowerCaseLetters), 
                   "The set of lowercase letters does not match the expected set.");
    }
}
