import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LevenshteinTest {

    @Test
    public void testDistanceKittenSitting() {
        assertEquals(3, Levenshtein.distance("kitten", "sitting"));
    }

    @Test
    public void testDistanceSaturdaySunday() {
        assertEquals(3, Levenshtein.distance("saturday", "sunday"));
    }

    @Test
    public void testDistanceRosettaCodeRaiseThySword() {
        assertEquals(8, Levenshtein.distance("rosettacode", "raisethysword"));
    }

    @Test
    public void testDistanceEmptyStrings() {
        assertEquals(0, Levenshtein.distance("", ""));
    }

    @Test
    public void testDistanceEmptyAndNonEmptyString() {
        assertEquals(5, Levenshtein.distance("", "hello"));
        assertEquals(5, Levenshtein.distance("hello", ""));
    }

    @Test
    public void testDistanceSameStrings() {
        assertEquals(0, Levenshtein.distance("same", "same"));
    }

    @Test
    public void testDistanceSingleCharacterDifference() {
        assertEquals(1, Levenshtein.distance("a", "b"));
    }

    @Test
    public void testDistanceReversedStrings() {
        assertEquals(2, Levenshtein.distance("abc", "cba"));
    }

    @Test
    public void testDistanceCaseInsensitive() {
        assertEquals(0, Levenshtein.distance("Case", "case"));
    }
}
