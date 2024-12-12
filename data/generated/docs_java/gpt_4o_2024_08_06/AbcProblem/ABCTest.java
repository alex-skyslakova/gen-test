import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class ABCTest {

    @Test
    public void testCanMakeWord() {
        List<String> blocks = Arrays.asList(
                "BO", "XK", "DQ", "CP", "NA",
                "GT", "RE", "TG", "QD", "FS",
                "JW", "HU", "VI", "AN", "OB",
                "ER", "FS", "LY", "PC", "ZM");

        assertTrue(ABC.canMakeWord("A", blocks));
        assertTrue(ABC.canMakeWord("BARK", blocks));
        assertFalse(ABC.canMakeWord("BOOK", blocks));
        assertTrue(ABC.canMakeWord("TREAT", blocks));
        assertFalse(ABC.canMakeWord("COMMON", blocks));
        assertTrue(ABC.canMakeWord("SQUAD", blocks));
        assertTrue(ABC.canMakeWord("CONFUSE", blocks));
    }

    @Test
    public void testEmptyWord() {
        List<String> blocks = Arrays.asList(
                "BO", "XK", "DQ", "CP", "NA",
                "GT", "RE", "TG", "QD", "FS",
                "JW", "HU", "VI", "AN", "OB",
                "ER", "FS", "LY", "PC", "ZM");

        assertTrue(ABC.canMakeWord("", blocks));
    }

    @Test
    public void testCaseInsensitivity() {
        List<String> blocks = Arrays.asList(
                "BO", "XK", "DQ", "CP", "NA",
                "GT", "RE", "TG", "QD", "FS",
                "JW", "HU", "VI", "AN", "OB",
                "ER", "FS", "LY", "PC", "ZM");

        assertTrue(ABC.canMakeWord("bark", blocks));
        assertTrue(ABC.canMakeWord("Bark", blocks));
        assertTrue(ABC.canMakeWord("bArK", blocks));
    }

    @Test
    public void testSingleLetter() {
        List<String> blocks = Arrays.asList(
                "BO", "XK", "DQ", "CP", "NA",
                "GT", "RE", "TG", "QD", "FS",
                "JW", "HU", "VI", "AN", "OB",
                "ER", "FS", "LY", "PC", "ZM");

        assertTrue(ABC.canMakeWord("B", blocks));
        assertTrue(ABC.canMakeWord("O", blocks));
        assertFalse(ABC.canMakeWord("Z", blocks)); // Z is available, but only once
    }
}
