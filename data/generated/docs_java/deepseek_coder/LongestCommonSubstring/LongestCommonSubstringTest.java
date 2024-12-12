import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LongestCommonSubstringTest {

    @Test
    public void testLcs_SameStrings() {
        assertEquals("test", LongestCommonSubstring.lcs("test", "test"));
    }

    @Test
    public void testLcs_NoCommonSubstring() {
        assertEquals("", LongestCommonSubstring.lcs("abc", "def"));
    }

    @Test
    public void testLcs_OneStringEmpty() {
        assertEquals("", LongestCommonSubstring.lcs("", "test"));
    }

    @Test
    public void testLcs_BothStringsEmpty() {
        assertEquals("", LongestCommonSubstring.lcs("", ""));
    }

    @Test
    public void testLcs_FirstStringShorter() {
        assertEquals("test", LongestCommonSubstring.lcs("testing123testing", "thisisatest"));
    }

    @Test
    public void testLcs_SecondStringShorter() {
        assertEquals("test", LongestCommonSubstring.lcs("thisisatest", "testing123testing"));
    }

    @Test
    public void testLcs_PartialMatch() {
        assertEquals("sting", LongestCommonSubstring.lcs("testing", "sting"));
    }

    @Test
    public void testLcs_MultipleMatches() {
        assertEquals("test", LongestCommonSubstring.lcs("testing", "thisisasting"));
    }

    @Test
    public void testLcs_SingleCharacterMatch() {
        assertEquals("t", LongestCommonSubstring.lcs("test", "t"));
    }

    @Test
    public void testLcs_SubstringAtStart() {
        assertEquals("test", LongestCommonSubstring.lcs("testabc", "test"));
    }

    @Test
    public void testLcs_SubstringAtEnd() {
        assertEquals("test", LongestCommonSubstring.lcs("abctest", "test"));
    }

    @Test
    public void testLcs_SubstringInMiddle() {
        assertEquals("test", LongestCommonSubstring.lcs("abc_test_def", "test"));
    }

    @Test
    public void testLcs_CaseInsensitive() {
        assertEquals("test", LongestCommonSubstring.lcs("TEST", "test"));
    }

    @Test
    public void testLcs_SpecialCharacters() {
        assertEquals("test", LongestCommonSubstring.lcs("test@#$", "test"));
    }

    @Test
    public void testLcs_LongStrings() {
        String a = "a".repeat(1000) + "test" + "a".repeat(1000);
        String b = "b".repeat(1000) + "test" + "b".repeat(1000);
        assertEquals("test", LongestCommonSubstring.lcs(a, b));
    }
}
