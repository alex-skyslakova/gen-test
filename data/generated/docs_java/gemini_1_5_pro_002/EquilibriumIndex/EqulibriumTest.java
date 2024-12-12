import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class EqulibriumTest {

    @Test
    void testExampleSequence() {
        int[] sequence = {-7, 1, 5, 2, -4, 3, 0};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Equlibrium.equlibrium_indices(sequence);

        String expectedOutput = "3\n6\n";  // Adjust newline character if necessary
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testEmptySequence() {
        int[] sequence = {};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Equlibrium.equlibrium_indices(sequence);

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    void testSingleElementSequence() {
        int[] sequence = {5};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Equlibrium.equlibrium_indices(sequence);

        String expectedOutput = "0\n";
        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    void testAllElementsEqual() {
        int[] sequence = {2, 2, 2, 2, 2};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Equlibrium.equlibrium_indices(sequence);

        String expectedOutput = "2\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testNoEquilibriumIndex() {
        int[] sequence = {1, 2, 3, 4, 5};
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Equlibrium.equlibrium_indices(sequence);

        String expectedOutput = "";
        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    void testLargeSequence() {  // Simulate a long sequence
        int[] sequence = new int[1000];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = i % 2 == 0 ? 1 : -1; // Alternating 1 and -1. Should have an equilibrium point in the middle.
        }

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Equlibrium.equlibrium_indices(sequence);

        String expectedOutput = (sequence.length % 2 == 0? (sequence.length / 2 -1) + "\n" : ""); 
        assertEquals(expectedOutput, outContent.toString());

    }
}
