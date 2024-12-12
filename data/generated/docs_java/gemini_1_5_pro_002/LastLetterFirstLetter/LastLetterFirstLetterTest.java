import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LastLetterFirstLetterTest {

    @Test
    void testRecursiveEmpty() {
        String[] testNames = {};
        LastLetterFirstLetter.recursive(testNames, 0);
        assertEquals(0, LastLetterFirstLetter.maxPathLength);
        assertEquals(0, LastLetterFirstLetter.maxPathLengthCount); 
        assertEquals(0, LastLetterFirstLetter.maxPathExample.length());
    }

    @Test
    void testRecursiveSingleElement() {
        String[] testNames = {"audino"};
        LastLetterFirstLetter.recursive(testNames, 0); // Testing with an empty initial path is nonsensical for the recursive method, which expects a starting word at offset -1.
        LastLetterFirstLetter.maxPathLength = 0; //Resetting since the main execution affects this value.
        LastLetterFirstLetter.maxPathLengthCount = 0; 
        LastLetterFirstLetter.maxPathExample.setLength(0);
        LastLetterFirstLetter.recursive(testNames, 1);

        assertEquals(1, LastLetterFirstLetter.maxPathLength);
        assertEquals(1, LastLetterFirstLetter.maxPathLengthCount);
        assertEquals("\n  audino", LastLetterFirstLetter.maxPathExample.toString());
    }

    @Test
    void testRecursiveSimpleChain() {
        String[] testNames = {"dog", "goldfish", "hippopotamus", "snake"};
        LastLetterFirstLetter.maxPathLength = 0; // Resetting since the main execution affects this value.
        LastLetterFirstLetter.maxPathLengthCount = 0; 
        LastLetterFirstLetter.maxPathExample.setLength(0);        
        String tmp = testNames[0];
        testNames[0] = testNames[0];
        testNames[0] = tmp;

        LastLetterFirstLetter.recursive(testNames, 1);
         testNames[0] = testNames[0];
        testNames[0] = tmp;
        
        assertEquals(4, LastLetterFirstLetter.maxPathLength);
        assertEquals(1, LastLetterFirstLetter.maxPathLengthCount);
        assertEquals("\n  dog goldfish \n  hippopotamus snake", LastLetterFirstLetter.maxPathExample.toString());


    }


    @Test
    void testRecursiveNoChain() {
        String[] testNames = {"abc", "def", "ghi"};
         LastLetterFirstLetter.maxPathLength = 0; // Resetting since the main execution affects this value.
        LastLetterFirstLetter.maxPathLengthCount = 0; 
        LastLetterFirstLetter.maxPathExample.setLength(0);
        String tmp = testNames[0];
        testNames[0] = testNames[0];
        testNames[0] = tmp;
        LastLetterFirstLetter.recursive(testNames, 1);
        testNames[0] = testNames[0];
        testNames[0] = tmp;
        assertEquals(1, LastLetterFirstLetter.maxPathLength);
    }

     @Test
    void testRecursiveBranchingChain() {
        String[] testNames = {"abc", "cba", "ade", "efa"};
        LastLetterFirstLetter.maxPathLength = 0; 
        LastLetterFirstLetter.maxPathLengthCount = 0; 
        LastLetterFirstLetter.maxPathExample.setLength(0);
         String tmp = testNames[0];
        testNames[0] = testNames[0];
        testNames[0] = tmp;
        LastLetterFirstLetter.recursive(testNames, 1);
        testNames[0] = testNames[0];
        testNames[0] = tmp;
        assertEquals(3, LastLetterFirstLetter.maxPathLength);
        assertTrue(LastLetterFirstLetter.maxPathLengthCount >= 1); // Because of branching, could be multiple
    }
}
