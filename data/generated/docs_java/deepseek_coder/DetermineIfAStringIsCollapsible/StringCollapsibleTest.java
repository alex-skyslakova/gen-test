import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCollapsibleTest {

    @Test
    public void testEmptyString() {
        String input = "";
        String expected = "";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testNoRepeatedCharacters() {
        String input = "abcdefg";
        String expected = "abcdefg";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testAllRepeatedCharacters() {
        String input = "aaaaaaaa";
        String expected = "a";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testMixedCharacters() {
        String input = "aabbccddeeffgghh";
        String expected = "abcdefgh";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample1() {
        String input = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ";
        String expected = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample2() {
        String input = "..1111111111111111111111111111111111111111111111111111111111111117777888";
        String expected = ".178";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample3() {
        String input = "I never give 'em hell, I just tell the truth, and they think it's hell. ";
        String expected = "I never give 'em hell, I just tell the truth, and they think it's hell. ";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample4() {
        String input = "                                                    --- Harry S Truman  ";
        String expected = " --- Harry S Truman ";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample5() {
        String input = "122333444455555666666777777788888888999999999";
        String expected = "123456789";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample6() {
        String input = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!";
        String expected = "The beter the 4-whel drive, the further you'l be from help when ya get stuck!";
        assertEquals(expected, StringCollapsible.collapse(input));
    }

    @Test
    public void testExample7() {
        String input = "headmistressship";
        String expected = "headmistreship";
        assertEquals(expected, StringCollapsible.collapse(input));
    }
}
