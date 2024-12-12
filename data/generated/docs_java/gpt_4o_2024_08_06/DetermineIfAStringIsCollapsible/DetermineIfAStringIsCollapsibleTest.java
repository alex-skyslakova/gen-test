import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringCollapsibleTest {

    @Test
    void testEmptyString() {
        assertEquals("", StringCollapsible.collapse(""));
    }

    @Test
    void testStringWithNoRepetitions() {
        assertEquals("abc", StringCollapsible.collapse("abc"));
    }

    @Test
    void testStringWithRepetitions() {
        assertEquals("abc", StringCollapsible.collapse("aabbcc"));
    }

    @Test
    void testStringWithMultipleRepetitions() {
        assertEquals("a", StringCollapsible.collapse("aaaa"));
    }

    @Test
    void testStringWithSpaces() {
        assertEquals("a b c", StringCollapsible.collapse("a  b  c"));
    }

    @Test
    void testStringWithSpecialCharacters() {
        assertEquals("!@#", StringCollapsible.collapse("!!@@##"));
    }

    @Test
    void testStringWithNumbers() {
        assertEquals("123", StringCollapsible.collapse("112233"));
    }

    @Test
    void testLongString() {
        assertEquals("The beter the 4-whel drive, the further you'l be from help when ya get stuck!", 
                     StringCollapsible.collapse("The better the 4-wheel drive, the further you'll be from help when ya get stuck!"));
    }

    @Test
    void testStringWithQuotes() {
        assertEquals("\"If I were two-faced, would I be wearing this one?\" - Abraham Lincoln", 
                     StringCollapsible.collapse("\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln"));
    }

    @Test
    void testStringWithNumbersAndDots() {
        assertEquals(".1", StringCollapsible.collapse("..111"));
    }

    @Test
    void testStringWithRepeatedSpaces() {
        assertEquals(" - Harry S Truman", 
                     StringCollapsible.collapse("                                                    --- Harry S Truman"));
    }

    @Test
    void testStringWithComplexRepetitions() {
        assertEquals("headmistreship", StringCollapsible.collapse("headmistressship"));
    }
}
