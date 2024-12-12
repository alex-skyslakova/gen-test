import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LastLetterFirstLetterTest {

    @Before
    public void setUp() {
        // Reset static variables before each test
        LastLetterFirstLetter.maxPathLength = 0;
        LastLetterFirstLetter.maxPathLengthCount = 0;
        LastLetterFirstLetter.maxPathExample.setLength(0);
    }

    @Test
    public void testMaxPathLength() {
        LastLetterFirstLetter.main(null);
        // Check if the maximum path length is greater than 0
        assertTrue("Maximum path length should be greater than 0", LastLetterFirstLetter.maxPathLength > 0);
    }

    @Test
    public void testMaxPathLengthCount() {
        LastLetterFirstLetter.main(null);
        // Check if there is at least one path of maximum length
        assertTrue("There should be at least one path of maximum length", LastLetterFirstLetter.maxPathLengthCount > 0);
    }

    @Test
    public void testMaxPathExample() {
        LastLetterFirstLetter.main(null);
        // Check if the example path is not empty
        assertTrue("Example path should not be empty", LastLetterFirstLetter.maxPathExample.length() > 0);
    }

    @Test
    public void testExamplePathValidity() {
        LastLetterFirstLetter.main(null);
        // Check if the example path is valid
        String[] path = LastLetterFirstLetter.maxPathExample.toString().trim().split("\\s+");
        for (int i = 0; i < path.length - 1; i++) {
            char lastChar = path[i].charAt(path[i].length() - 1);
            char firstChar = path[i + 1].charAt(0);
            assertEquals("Each word should start with the last letter of the previous word", lastChar, firstChar);
        }
    }
}
