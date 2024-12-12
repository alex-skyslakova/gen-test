import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCollapsibleTest {

    @Test
    void testEmptyString() {
        assertEquals("", StringCollapsible.collapse(""));
    }

    @Test
    void testNoRepeats() {
        String input = "abcde";
        assertEquals("abcde", StringCollapsible.collapse(input));
    }

    @Test
    void testSingleRepeats() {
        String input = "aabbccddee";
        assertEquals("abcde", StringCollapsible.collapse(input));
    }

    @Test
    void testMultipleRepeats() {
        String input = "aaabbbcccdddeee";
        assertEquals("abcde", StringCollapsible.collapse(input));
    }

    @Test
    void testMixedRepeatsAndNonRepeats() {
        String input = "abbcdeeffggh";
        assertEquals("abcdefgh", StringCollapsible.collapse(input));
    }

    @Test
    void testLincolnQuote() {
        String input = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ";
        String expected = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ";
        assertEquals(expected, StringCollapsible.collapse(input));
    }
    
    @Test
    void testNumberString(){
        String input = "..1111111111111111111111111111111111111111111111111111111111111117777888";
        String expected = ".178";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    void testTrumanQuoteWithSpaces() {
        String input = "                                                    --- Harry S Truman  ";
        String expected = " --- Harry S Truman  ";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    void testNumericRepeats() {
        String input = "122333444455555666666777777788888888999999999";
        String expected = "123456789";
        assertEquals(expected, StringCollapsible.collapse(input));
    }


    @Test
    void testExampleWithSpaces() {
        String input = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!";
        String expected = "The beter the 4-whel drive, the further you'l be from help when ya get stuck!";
        assertEquals(expected, StringCollapsible.collapse(input));
    }


    @Test
    void testHeadmistressship() {
        String input = "headmistressship";
        String expected = "headmistreship";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

}
