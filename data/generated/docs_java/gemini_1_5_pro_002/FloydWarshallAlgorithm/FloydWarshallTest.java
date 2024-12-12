import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FloydWarshallTest {

    @Test
    void testFloydWarshall_exampleCase() {
        int[][] weights = {{1, 3, -2}, {2, 1, 4}, {2, 3, 3}, {3, 4, 2}, {4, 2, -1}};
        int numVertices = 4;

        // Redirect System.out to capture the printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FloydWarshall.floydWarshall(weights, numVertices);

        String expectedOutput = "pair     dist    path\n" +
                "1 -> 3    -2     1 -> 3\n" +
                "1 -> 4    0     1 -> 3 -> 4\n" +
                "1 -> 2    -1     1 -> 3 -> 4 -> 2\n" +
                "3 -> 4    2     3 -> 4\n" +
                "3 -> 2    1     3 -> 4 -> 2\n" +
                "3 -> 1    5     3 -> 4 -> 2 -> 1\n" +
                "4 -> 2    -1     4 -> 2\n" +
                "4 -> 1    3     4 -> 2 -> 1\n" +
                "4 -> 3    1     4 -> 2 -> 1 -> 3\n" +
                "2 -> 1    4     2 -> 1\n" +
                "2 -> 3    2     2 -> 1 -> 3\n" +
                "2 -> 4    4     2 -> 1 -> 3 -> 4\n";


        assertEquals(expectedOutput, outContent.toString());

        // Restore System.out
        System.setOut(System.out);
    }


    @Test
    void testFloydWarshall_noEdges() {
        int[][] weights = {};
        int numVertices = 3;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FloydWarshall.floydWarshall(weights, numVertices);

        String expectedOutput = "pair     dist    path\n" +
                "1 -> 2    2147483647     1 -> 2\n" +
                "1 -> 3    2147483647     1 -> 3\n" +
                "2 -> 1    2147483647     2 -> 1\n" +
                "2 -> 3    2147483647     2 -> 3\n" +
                "3 -> 1    2147483647     3 -> 1\n" +
                "3 -> 2    2147483647     3 -> 2\n"; // Distances should be infinity (represented by max int value)

        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);

    }

    @Test
    void testFloydWarshall_singleVertex() {
        int[][] weights = {};
        int numVertices = 1;

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        FloydWarshall.floydWarshall(weights, numVertices);

        String expectedOutput = "pair     dist    path\n"; //No paths for a single vertex

        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);

    }


    import java.io.ByteArrayOutputStream;
    import java.io.PrintStream;
}
