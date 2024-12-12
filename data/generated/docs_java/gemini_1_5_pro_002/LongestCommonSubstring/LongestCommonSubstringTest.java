import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LongestCommonSubstringTest {

    @Test
    void testLCSBasic() {
        assertEquals("test", LongestCommonSubstring.lcs("testing123testing", "thisisatest"));
    }

    @Test
    void testLCSIdenticalSubstring() {
        assertEquals("test", LongestCommonSubstring.lcs("test", "thisisatest"));
    }

    @Test
    void testLCSSuffix() {
        assertEquals("sting", LongestCommonSubstring.lcs("testing", "sting"));
    }


    @Test
    void testLCSMiddleSubstring() {
        assertEquals("sting", LongestCommonSubstring.lcs("testing", "thisisasting"));
    }

    @Test
    void testLCSEmptyStrings() {
        assertEquals("", LongestCommonSubstring.lcs("", ""));
    }

    @Test
    void testLCSOneEmptyString() {
        assertEquals("", LongestCommonSubstring.lcs("testing", ""));
        assertEquals("", LongestCommonSubstring.lcs("", "testing"));
    }

    @Test
    void testLCSNoCommonSubstring() {
        assertEquals("", LongestCommonSubstring.lcs("abc", "def"));
    }

    @Test
    void testLCSDifferentLengths() {
        assertEquals("testing", LongestCommonSubstring.lcs("testing123", "testing"));
        assertEquals("testing", LongestCommonSubstring.lcs("testing", "testing123"));
    }

    @Test
    void testLCSOverlappingSubstrings() {
        assertEquals("abab", LongestCommonSubstring.lcs("ababab", "bababa"));
    }


}
