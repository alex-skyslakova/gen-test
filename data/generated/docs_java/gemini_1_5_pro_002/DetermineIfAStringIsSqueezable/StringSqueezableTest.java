import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringSqueezableTest {

    @Test
    void squeeze_emptyString() {
        assertEquals("", StringSqueezable.squeeze("", ' '));
    }

    @Test
    void squeeze_noRepeats() {
        String input = "abcde";
        assertEquals(input, StringSqueezable.squeeze(input, ' '));
    }

    @Test
    void squeeze_repeatCharNotIncluded() {
        String input = "aabbccddee";
        assertEquals(input, StringSqueezable.squeeze(input, 'f'));
    }


    @Test
    void squeeze_singleRepeat() {
        assertEquals("The better the 4-whel drive, the further you'll be from help when ya get stuck!", 
                     StringSqueezable.squeeze("The better the 4-wheel drive, the further you'll be from help when ya get stuck!", 'e'));
    }

    @Test
    void squeeze_multipleRepeats() {
        assertEquals("headmistreship", StringSqueezable.squeeze("headmistressship", 's'));
    }

    @Test
    void squeeze_repeatsAtBeginning() {
        assertEquals("abc", StringSqueezable.squeeze("aabbc", 'a'));
    }

    @Test
    void squeeze_repeatsAtEnd() {
        assertEquals("abc", StringSqueezable.squeeze("abcc", 'c'));

    }

    @Test
    void squeeze_repeatsThroughout() {
        assertEquals("abcdefgh", StringSqueezable.squeeze("aabbccddeeffggh", 'a'));
    }
    
    @Test
    void squeeze_multipleDifferentRepeats() {
       assertEquals("123456789", StringSqueezable.squeeze("122333444455555666666777777788888888999999999", '5'));
    }

    @Test
    void squeeze_withSpace() {
        assertEquals("--- Harry S Truman  ", StringSqueezable.squeeze("                                                    --- Harry S Truman  ", ' '));
    }
    
    @Test
    void squeeze_withHyphen() {
        assertEquals("\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ", StringSqueezable.squeeze("\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ", '-'));
    }
    
    @Test
    void squeeze_withSeven() {
        assertEquals("..1111111111111111111111111111111111111111111111111111111111111117888", StringSqueezable.squeeze("..1111111111111111111111111111111111111111111111111111111111111117777888", '7'));
    }

    @Test
    void squeeze_withPeriod() {
        assertEquals("I never give 'em hell, I just tell the truth, and they think it's hell. ", StringSqueezable.squeeze("I never give 'em hell, I just tell the truth, and they think it's hell. ", '.'));
    }
    
        @Test
    void squeeze_withR() {
        assertEquals("                                                    --- Hay S Truman  ", StringSqueezable.squeeze("                                                    --- Harry S Truman  ", 'r'));
    }



}
