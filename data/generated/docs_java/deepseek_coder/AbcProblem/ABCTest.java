import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ABCTest {

    private List<String> blocks = Arrays.asList(
            "BO", "XK", "DQ", "CP", "NA",
            "GT", "RE", "TG", "QD", "FS",
            "JW", "HU", "VI", "AN", "OB",
            "ER", "FS", "LY", "PC", "ZM");

    @Test
    public void testEmptyString() {
        assertTrue(ABC.canMakeWord("", blocks));
    }

    @Test
    public void testSingleLetter() {
        assertTrue(ABC.canMakeWord("A", blocks));
    }

    @Test
    public void testBARK() {
        assertTrue(ABC.canMakeWord("BARK", blocks));
    }

    @Test
    public void testBOOK() {
        assertFalse(ABC.canMakeWord("BOOK", blocks));
    }

    @Test
    public void testTREAT() {
        assertTrue(ABC.canMakeWord("TREAT", blocks));
    }

    @Test
    public void testCOMMON() {
        assertFalse(ABC.canMakeWord("COMMON", blocks));
    }

    @Test
    public void testSQUAD() {
        assertTrue(ABC.canMakeWord("SQUAD", blocks));
    }

    @Test
    public void testCONFUSE() {
        assertTrue(ABC.canMakeWord("CONFUSE", blocks));
    }
}
