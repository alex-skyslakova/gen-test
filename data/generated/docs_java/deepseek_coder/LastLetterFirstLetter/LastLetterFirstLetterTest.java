import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LastLetterFirstLetterTest {

    @BeforeEach
    void setUp() {
        // Reset static variables before each test
        LastLetterFirstLetter.maxPathLength = 0;
        LastLetterFirstLetter.maxPathLengthCount = 0;
        LastLetterFirstLetter.maxPathExample.setLength(0);
    }

    @Test
    void testRecursiveWithSingleName() {
        String[] singleName = {"audino"};
        LastLetterFirstLetter.recursive(singleName, 1);
        assertEquals(1, LastLetterFirstLetter.maxPathLength);
        assertEquals(1, LastLetterFirstLetter.maxPathLengthCount);
        assertEquals("\n  audino", LastLetterFirstLetter.maxPathExample.toString());
    }

    @Test
    void testRecursiveWithTwoNames() {
        String[] twoNames = {"audino", "nosepass"};
        LastLetterFirstLetter.recursive(twoNames, 1);
        assertEquals(2, LastLetterFirstLetter.maxPathLength);
        assertEquals(1, LastLetterFirstLetter.maxPathLengthCount);
        assertEquals("\n  audino nosepass", LastLetterFirstLetter.maxPathExample.toString());
    }

    @Test
    void testRecursiveWithNoValidSequence() {
        String[] noValidSequence = {"audino", "bagon"};
        LastLetterFirstLetter.recursive(noValidSequence, 1);
        assertEquals(1, LastLetterFirstLetter.maxPathLength);
        assertEquals(2, LastLetterFirstLetter.maxPathLengthCount);
        assertTrue(LastLetterFirstLetter.maxPathExample.toString().contains("audino") ||
                   LastLetterFirstLetter.maxPathExample.toString().contains("bagon"));
    }

    @Test
    void testRecursiveWithFullList() {
        LastLetterFirstLetter.main(new String[]{});
        // Expected values based on the provided list of names
        assertEquals(23, LastLetterFirstLetter.maxPathLength);
        assertEquals(1, LastLetterFirstLetter.maxPathLengthCount);
        // Example path should contain a valid sequence of 23 names
        assertTrue(LastLetterFirstLetter.maxPathExample.toString().contains("audino"));
        assertTrue(LastLetterFirstLetter.maxPathExample.toString().contains("nosepass"));
        // Add more specific checks if needed
    }

    @Test
    void testRecursiveWithEmptyList() {
        String[] emptyList = {};
        LastLetterFirstLetter.recursive(emptyList, 0);
        assertEquals(0, LastLetterFirstLetter.maxPathLength);
        assertEquals(0, LastLetterFirstLetter.maxPathLengthCount);
        assertEquals("", LastLetterFirstLetter.maxPathExample.toString());
    }

    @Test
    void testRecursiveWithInvalidNames() {
        String[] invalidNames = {"audino", "invalid"};
        LastLetterFirstLetter.recursive(invalidNames, 1);
        assertEquals(1, LastLetterFirstLetter.maxPathLength);
        assertEquals(2, LastLetterFirstLetter.maxPathLengthCount);
        assertTrue(LastLetterFirstLetter.maxPathExample.toString().contains("audino") ||
                   LastLetterFirstLetter.maxPathExample.toString().contains("invalid"));
    }
}
