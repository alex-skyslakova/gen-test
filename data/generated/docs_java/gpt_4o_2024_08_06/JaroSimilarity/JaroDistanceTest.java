import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JaroDistanceTest {

    @Test
    public void testExactMatch() {
        assertEquals(1.0, JaroDistance.jaro("IDENTICAL", "IDENTICAL"), 0.001);
    }

    @Test
    public void testCompletelyDifferent() {
        assertEquals(0.0, JaroDistance.jaro("ABC", "XYZ"), 0.001);
    }

    @Test
    public void testExample1() {
        assertEquals(0.944, JaroDistance.jaro("MARTHA", "MARHTA"), 0.001);
    }

    @Test
    public void testExample2() {
        assertEquals(0.767, JaroDistance.jaro("DIXON", "DICKSONX"), 0.001);
    }

    @Test
    public void testExample3() {
        assertEquals(0.896, JaroDistance.jaro("JELLYFISH", "SMELLYFISH"), 0.001);
    }

    @Test
    public void testEmptyStrings() {
        assertEquals(1.0, JaroDistance.jaro("", ""), 0.001);
    }

    @Test
    public void testOneEmptyString() {
        assertEquals(0.0, JaroDistance.jaro("NONEMPTY", ""), 0.001);
        assertEquals(0.0, JaroDistance.jaro("", "NONEMPTY"), 0.001);
    }

    @Test
    public void testSingleCharacterMatch() {
        assertEquals(1.0, JaroDistance.jaro("A", "A"), 0.001);
    }

    @Test
    public void testSingleCharacterNoMatch() {
        assertEquals(0.0, JaroDistance.jaro("A", "B"), 0.001);
    }

    @Test
    public void testPartialMatch() {
        assertEquals(0.733, JaroDistance.jaro("CRATE", "TRACE"), 0.001);
    }
}
