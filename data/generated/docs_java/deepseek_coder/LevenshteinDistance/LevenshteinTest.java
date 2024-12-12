import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinTest {

    @Test
    public void testDistance_SameStrings() {
        assertEquals(0, Levenshtein.distance("kitten", "kitten"));
    }

    @Test
    public void testDistance_CaseInsensitive() {
        assertEquals(3, Levenshtein.distance("kitten", "SITTING"));
    }

    @Test
    public void testDistance_DifferentLengths() {
        assertEquals(3, Levenshtein.distance("kitten", "sitting"));
    }

    @Test
    public void testDistance_EmptyStrings() {
        assertEquals(0, Levenshtein.distance("", ""));
        assertEquals(7, Levenshtein.distance("", "kitten"));
        assertEquals(7, Levenshtein.distance("kitten", ""));
    }

    @Test
    public void testDistance_WikipediaExample() {
        assertEquals(3, Levenshtein.distance("kitten", "sitting"));
    }

    @Test
    public void testDistance_ReversedStrings() {
        assertEquals(8, Levenshtein.distance("rosettacode", "raisethysword"));
        assertEquals(8, Levenshtein.distance("rosettacode", "drowsyhtesiar"));
    }

    @Test
    public void testDistance_SingleCharacter() {
        assertEquals(1, Levenshtein.distance("a", "b"));
        assertEquals(1, Levenshtein.distance("a", ""));
        assertEquals(1, Levenshtein.distance("", "b"));
    }

    @Test
    public void testDistance_MultipleSubstitutions() {
        assertEquals(2, Levenshtein.distance("abc", "def"));
    }

    @Test
    public void testDistance_MultipleInsertions() {
        assertEquals(3, Levenshtein.distance("abc", "abcdef"));
    }

    @Test
    public void testDistance_MultipleDeletions() {
        assertEquals(3, Levenshtein.distance("abcdef", "abc"));
    }

    @Test
    public void testDistance_MixedOperations() {
        assertEquals(3, Levenshtein.distance("kitten", "sittin"));
        assertEquals(3, Levenshtein.distance("sittin", "kitten"));
    }

    @Test
    public void testDistance_LongStrings() {
        assertEquals(10, Levenshtein.distance("abcdefghij", "klmnopqrst"));
    }
}
