import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JaroDistanceTest {

    @Test
    void testJaroEmptyStrings() {
        assertEquals(1.0, JaroDistance.jaro("", ""));
    }

    @Test
    void testJaroMarthaMarhta() {
        assertEquals(0.9444444444444445, JaroDistance.jaro("MARTHA", "MARHTA"));
    }

    @Test
    void testJaroDixonDicksonx() {
        assertEquals(0.7666666666666666, JaroDistance.jaro("DIXON", "DICKSONX"));
    }

    @Test
    void testJaroJellyfishSmellyfish() {
        assertEquals(0.8962962962962964, JaroDistance.jaro("JELLYFISH", "SMELLYFISH"));
    }

    @Test
    void testJaroOneEmptyString() {
        assertEquals(0.0, JaroDistance.jaro("CRATE", ""));
        assertEquals(0.0, JaroDistance.jaro("", "TRACE"));

    }
    @Test
    void testJaroNoMatchingCharacters() {
        assertEquals(0.0, JaroDistance.jaro("ABC", "XYZ"));
    }

    @Test
    void testJaroPartialMatch() {
        assertEquals(0.43333333333333335, JaroDistance.jaro("ABCVWXYZ", "CABVWXYZ"));


    }

    @Test
    void testJaroExactMatch() {
        assertEquals(1.0, JaroDistance.jaro("TEST", "TEST"));
    }


}
