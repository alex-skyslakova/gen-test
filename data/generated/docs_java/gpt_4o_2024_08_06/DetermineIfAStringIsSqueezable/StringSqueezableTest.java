import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringSqueezableTest {

    @Test
    public void testEmptyString() {
        assertEquals("", StringSqueezable.squeeze("", ' '));
    }

    @Test
    public void testStringWithHyphen() {
        String original = "\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ";
        String expected = "\"If I were two-faced, would I be wearing this one?\" - Abraham Lincoln ";
        assertEquals(expected, StringSqueezable.squeeze(original, '-'));
    }

    @Test
    public void testStringWithSeven() {
        String original = "..1111111111111111111111111111111111111111111111111111111111111117777888";
        String expected = "..111111111111111111111111111111111111111111111111111111111111117888";
        assertEquals(expected, StringSqueezable.squeeze(original, '7'));
    }

    @Test
    public void testStringWithPeriod() {
        String original = "I never give 'em hell, I just tell the truth, and they think it's hell. ";
        String expected = "I never give 'em hell, I just tell the truth, and they think it's hell ";
        assertEquals(expected, StringSqueezable.squeeze(original, '.'));
    }

    @Test
    public void testStringWithSpaces() {
        String original = "                                                    --- Harry S Truman  ";
        String expected = " --- Harry S Truman ";
        assertEquals(expected, StringSqueezable.squeeze(original, ' '));
    }

    @Test
    public void testStringWithMinus() {
        String original = "                                                    --- Harry S Truman  ";
        String expected = "                                                    - Harry S Truman  ";
        assertEquals(expected, StringSqueezable.squeeze(original, '-'));
    }

    @Test
    public void testStringWithR() {
        String original = "                                                    --- Harry S Truman  ";
        String expected = "                                                    --- Hary S Truman  ";
        assertEquals(expected, StringSqueezable.squeeze(original, 'r'));
    }

    @Test
    public void testStringWithFive() {
        String original = "122333444455555666666777777788888888999999999";
        String expected = "122333444455566666677777778888888999999999";
        assertEquals(expected, StringSqueezable.squeeze(original, '5'));
    }

    @Test
    public void testStringWithE() {
        String original = "The better the 4-wheel drive, the further you'll be from help when ya get stuck!";
        String expected = "The beter the 4-whel drive, the further you'll be from help when ya get stuck!";
        assertEquals(expected, StringSqueezable.squeeze(original, 'e'));
    }

    @Test
    public void testStringWithS() {
        String original = "headmistressship";
        String expected = "headmistreship";
        assertEquals(expected, StringSqueezable.squeeze(original, 's'));
    }
}
