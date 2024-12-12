import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

    private Graph graph;

    @BeforeEach
    void setUp() {
        Graph.Edge[] edges = {
            new Graph.Edge("a", "b", 7),
            new Graph.Edge("a", "c", 9),
            new Graph.Edge("a", "f", 14),
            new Graph.Edge("b", "c", 10),
            new Graph.Edge("b", "d", 15),
            new Graph.Edge("c", "d", 11),
            new Graph.Edge("c", "f", 2),
            new Graph.Edge("d", "e", 6),
            new Graph.Edge("e", "f", 9),
        };
        graph = new Graph(edges);
    }

    @Test
    void testDijkstraShortestPathToE() {
        graph.dijkstra("a");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        graph.printPath("e");
        String expectedOutput = "a -> c(9) -> f(11) -> e(17)\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDijkstraShortestPathToF() {
        graph.dijkstra("a");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        graph.printPath("f");
        String expectedOutput = "a -> c(9) -> f(11)\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDijkstraUnreachableVertex() {
        graph.dijkstra("a");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        graph.printPath("z"); // Assuming 'z' is not in the graph
        String expectedOutput = "Graph doesn't contain end vertex \"z\"\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDijkstraSourceVertex() {
        graph.dijkstra("a");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        graph.printPath("a");
        String expectedOutput = "a\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testDijkstraAllPaths() {
        graph.dijkstra("a");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        graph.printAllPaths();
        String expectedOutput = "a\n" +
                                "a -> b(7)\n" +
                                "a -> c(9)\n" +
                                "a -> c(9) -> d(20)\n" +
                                "a -> c(9) -> f(11) -> e(17)\n" +
                                "a -> c(9) -> f(11)\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testGraphInitialization() {
        Map<String, Graph.Vertex> vertices = graph.graph;
        assertNotNull(vertices.get("a"));
        assertNotNull(vertices.get("b"));
        assertNotNull(vertices.get("c"));
        assertNotNull(vertices.get("d"));
        assertNotNull(vertices.get("e"));
        assertNotNull(vertices.get("f"));
        assertNull(vertices.get("z")); // Assuming 'z' is not in the graph
    }

    @Test
    void testVertexInitialization() {
        Graph.Vertex vertexA = graph.graph.get("a");
        assertEquals("a", vertexA.name);
        assertEquals(Integer.MAX_VALUE, vertexA.dist);
        assertNull(vertexA.previous);
        assertEquals(3, vertexA.neighbours.size());
    }
}
