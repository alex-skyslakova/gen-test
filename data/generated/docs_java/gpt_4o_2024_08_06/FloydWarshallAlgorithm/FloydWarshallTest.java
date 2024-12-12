import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FloydWarshallTest {

    @Test
    public void testFloydWarshall() {
        int[][] weights = {{1, 3, -2}, {2, 1, 4}, {2, 3, 3}, {3, 4, 2}, {4, 2, -1}};
        int numVertices = 4;

        // Redirect output to capture the print statements
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FloydWarshall.floydWarshall(weights, numVertices);

        // Expected output
        String expectedOutput = "pair     dist    path\n" +
                "1 -> 2    -1     1 -> 3 -> 4 -> 2\n" +
                "1 -> 3    -2     1 -> 3\n" +
                "1 -> 4     0     1 -> 3 -> 4\n" +
                "2 -> 1     4     2 -> 1\n" +
                "2 -> 3     2     2 -> 1 -> 3\n" +
                "2 -> 4     4     2 -> 1 -> 3 -> 4\n" +
                "3 -> 1     5     3 -> 4 -> 2 -> 1\n" +
                "3 -> 2     1     3 -> 4 -> 2\n" +
                "3 -> 4     2     3 -> 4\n" +
                "4 -> 1     3     4 -> 2 -> 1\n" +
                "4 -> 2    -1     4 -> 2\n" +
                "4 -> 3     1     4 -> 2 -> 1 -> 3\n";

        assertEquals(expectedOutput, outContent.toString());
    }
}
