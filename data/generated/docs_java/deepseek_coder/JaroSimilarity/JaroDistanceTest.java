import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JaroDistanceTest {

    @Test
    public void testJaroExactMatch() {
        assertEquals(1.0, JaroDistance.jaro("MARTHA", "MARTHA"), 0.0001);
    }

    @Test
    public void testJaroNoMatch() {
        assertEquals(0.0, JaroDistance.jaro("AAAA", "BBBB"), 0.0001);
    }

    @Test
    public void testJaroPartialMatch() {
        assertEquals(0.8222, JaroDistance.jaro("MARTHA", "MARHTA"), 0.0001);
    }

    @Test
    public void testJaroDifferentLengths() {
        assertEquals(0.7667, JaroDistance.jaro("DIXON", "DICKSONX"), 0.0001);
    }

    @Test
    public void testJaroTranspositions() {
        assertEquals(0.8963, JaroDistance.jaro("JELLYFISH", "SMELLYFISH"), 0.0001);
    }

    @Test
    public void testJaroEmptyStrings() {
        assertEquals(1.0, JaroDistance.jaro("", ""), 0.0001);
    }

    @Test
    public void testJaroOneEmptyString() {
        assertEquals(0.0, JaroDistance.jaro("MARTHA", ""), 0.0001);
        assertEquals(0.0, JaroDistance.jaro("", "MARTHA"), 0.0001);
    }

    @Test
    public void testJaroSingleCharacterMatch() {
        assertEquals(1.0, JaroDistance.jaro("A", "A"), 0.0001);
    }

    @Test
    public void testJaroSingleCharacterNoMatch() {
        assertEquals(0.0, JaroDistance.jaro("A", "B"), 0.0001);
    }

    @Test
    public void testJaroCaseInsensitivity() {
        assertEquals(0.8222, JaroDistance.jaro("MARTHA", "marhta"), 0.0001);
    }

    @Test
    public void testJaroDifferentMatchDistance() {
        assertEquals(0.5000, JaroDistance.jaro("ABCD", "EFGH"), 0.0001);
    }

    @Test
    public void testJaroLongStrings() {
        assertEquals(0.9444, JaroDistance.jaro("REPUBLIC OF FRANCE", "REPUBLIC OF FRANCE"), 0.0001);
    }

    @Test
    public void testJaroLongStringsWithTranspositions() {
        assertEquals(0.9111, JaroDistance.jaro("REPUBLIC OF FRANCE", "REPUBLIC OF FRNACE"), 0.0001);
    }
}
