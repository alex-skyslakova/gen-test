import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestCommonSubstringTest {

    @Test
    public void testLongestCommonSubstring() {
        assertEquals("test", LongestCommonSubstring.lcs("testing123testing", "thisisatest"));
        assertEquals("test", LongestCommonSubstring.lcs("test", "thisisatest"));
        assertEquals("sting", LongestCommonSubstring.lcs("testing", "sting"));
        assertEquals("sting", LongestCommonSubstring.lcs("testing", "thisisasting"));
    }

    @Test
    public void testNoCommonSubstring() {
        assertEquals("", LongestCommonSubstring.lcs("abc", "xyz"));
    }

    @Test
    public void testEmptyStrings() {
        assertEquals("", LongestCommonSubstring.lcs("", ""));
        assertEquals("", LongestCommonSubstring.lcs("abc", ""));
        assertEquals("", LongestCommonSubstring.lcs("", "xyz"));
    }

    @Test
    public void testIdenticalStrings() {
        assertEquals("abc", LongestCommonSubstring.lcs("abc", "abc"));
    }

    @Test
    public void testSingleCharacterCommonSubstring() {
        assertEquals("a", LongestCommonSubstring.lcs("a", "a"));
        assertEquals("a", LongestCommonSubstring.lcs("a", "abc"));
        assertEquals("b", LongestCommonSubstring.lcs("abc", "b"));
    }

    @Test
    public void testSubstringAtDifferentPositions() {
        assertEquals("abc", LongestCommonSubstring.lcs("abcxyz", "xyzabc"));
        assertEquals("xyz", LongestCommonSubstring.lcs("xyzabc", "abcxyz"));
    }
}
