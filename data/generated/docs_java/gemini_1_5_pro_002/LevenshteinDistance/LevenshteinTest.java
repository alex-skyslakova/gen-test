import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LevenshteinTest {

    @Test
    void testKittenSitting() {
        assertEquals(3, Levenshtein.distance("kitten", "sitting"));
    }

    @Test
    void testSaturdaySunday() {
        assertEquals(3, Levenshtein.distance("saturday", "sunday"));
    }

    @Test
    void testRosettaCodeRaisethysword() {
        assertEquals(8, Levenshtein.distance("rosettacode", "raisethysword"));
    }

    @Test
    void testEmptyStrings() {
        assertEquals(0, Levenshtein.distance("", ""));
    }

    @Test
    void testEmptyStringAndNonEmptyString() {
        assertEquals(5, Levenshtein.distance("", "hello"));
        assertEquals(5, Levenshtein.distance("hello", ""));
    }

    @Test
    void testSameString() {
        assertEquals(0, Levenshtein.distance("hello", "hello"));
    }

    @Test
    void testCaseInsensitivity() {
        assertEquals(3, Levenshtein.distance("Kitten", "sitting"));
        assertEquals(3, Levenshtein.distance("kitten", "Sitting"));
        assertEquals(3, Levenshtein.distance("KITTEN", "SITTING"));

    }

    @Test
    void testReverseStrings(){
        assertEquals(Levenshtein.distance("kitten", "sitting"), Levenshtein.distance("nettik", "gnittis"));
        assertEquals(Levenshtein.distance("saturday", "sunday"), Levenshtein.distance("yadrutsa", "yadnus"));

    }

    @Test
    void testOneEditDistance(){
        assertEquals(1, Levenshtein.distance("cat", "cats")); // insertion
        assertEquals(1, Levenshtein.distance("cats", "cat")); // deletion
        assertEquals(1, Levenshtein.distance("cat", "bat")); // substitution

    }


}
