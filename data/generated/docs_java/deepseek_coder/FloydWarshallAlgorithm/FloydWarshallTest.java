import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class FloydWarshallTest {

    @Test
    public void testFloydWarshall() {
        int[][] weights = {{1, 3, -2}, {2, 1, 4}, {2, 3, 3}, {3, 4, 2}, {4, 2, -1}};
        int numVertices = 4;

        // Capture the output of the printResult method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

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

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testFloydWarshallWithNoEdges() {
        int[][] weights = {};
        int numVertices = 4;

        // Capture the output of the printResult method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FloydWarshall.floydWarshall(weights, numVertices);

        // Expected output
        String expectedOutput = "pair     dist    path\n" +
                                "1 -> 2    ∞     \n" +
                                "1 -> 3    ∞     \n" +
                                "1 -> 4    ∞     \n" +
                                "2 -> 1    ∞     \n" +
                                "2 -> 3    ∞     \n" +
                                "2 -> 4    ∞     \n" +
                                "3 -> 1    ∞     \n" +
                                "3 -> 2    ∞     \n" +
                                "3 -> 4    ∞     \n" +
                                "4 -> 1    ∞     \n" +
                                "4 -> 2    ∞     \n" +
                                "4 -> 3    ∞     \n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testFloydWarshallWithSingleVertex() {
        int[][] weights = {};
        int numVertices = 1;

        // Capture the output of the printResult method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FloydWarshall.floydWarshall(weights, numVertices);

        // Expected output
        String expectedOutput = "pair     dist    path\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testFloydWarshallWithNegativeWeights() {
        int[][] weights = {{1, 2, -1}, {2, 3, -2}, {3, 1, -3}};
        int numVertices = 3;

        // Capture the output of the printResult method
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        FloydWarshall.floydWarshall(weights, numVertices);

        // Expected output
        String expectedOutput = "pair     dist    path\n" +
                                "1 -> 2    -1     1 -> 2\n" +
                                "1 -> 3    -3     1 -> 2 -> 3\n" +
                                "2 -> 1    -5     2 -> 3 -> 1\n" +
                                "2 -> 3    -2     2 -> 3\n" +
                                "3 -> 1    -3     3 -> 1\n" +
                                "3 -> 2    -4     3 -> 1 -> 2\n";

        assertEquals(expectedOutput, outputStream.toString());
    }
}
