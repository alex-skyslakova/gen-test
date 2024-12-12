import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ABCTest {

    private static final List<String> blocks = Arrays.asList(
            "BO", "XK", "DQ", "CP", "NA",
            "GT", "RE", "TG", "QD", "FS",
            "JW", "HU", "VI", "AN", "OB",
            "ER", "FS", "LY", "PC", "ZM");


    @Test
    void testEmptyString() {
        assertTrue(ABC.canMakeWord("", blocks));
    }

    @Test
    void testSingleLetterA() {
        assertTrue(ABC.canMakeWord("A", blocks));
    }

    @Test
    void testBARK() {
        assertTrue(ABC.canMakeWord("BARK", blocks));
    }

    @Test
    void testBOOK() {
        assertFalse(ABC.canMakeWord("BOOK", blocks));
    }

    @Test
    void testTREAT() {
        assertTrue(ABC.canMakeWord("TREAT", blocks));
    }

    @Test
    void testCOMMON() {
        assertFalse(ABC.canMakeWord("COMMON", blocks));
    }

    @Test
    void testSQUAD() {
        assertTrue(ABC.canMakeWord("SQUAD", blocks));
    }

    @Test
    void testCONFUSE() {
        assertTrue(ABC.canMakeWord("CONFUSE", blocks));
    }

    @Test
    void testCaseInsensitive() {
        assertTrue(ABC.canMakeWord("bark", blocks));
        assertTrue(ABC.canMakeWord("treat", blocks));
        assertFalse(ABC.canMakeWord("book", blocks));

    }

    @Test
    void testAllBlocksUsed(){
        assertTrue(ABC.canMakeWord("BATCH", blocks));
    }


}
