import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTest {

    @Test
    void dijkstra_withExampleGraph_shortestPathToE() {
        Graph.Edge[] graphEdges = createExampleGraphEdges();
        Graph g = new Graph(graphEdges);
        g.dijkstra("a");

        String expectedPathToE = "a -> c(9) -> d(20) -> e(26)";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        g.printPath("e");
        assertEquals(expectedPathToE + System.lineSeparator(), outContent.toString());

        System.setOut(System.out); // Resetting the output stream
    }


    @Test
    void dijkstra_withExampleGraph_shortestPathToF() {
        Graph.Edge[] graphEdges = createExampleGraphEdges();
        Graph g = new Graph(graphEdges);
        g.dijkstra("a");

         String expectedPathToF = "a -> c(9) -> f(11)";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        g.printPath("f");
        assertEquals(expectedPathToF + System.lineSeparator(), outContent.toString());
        System.setOut(System.out);

    }

    @Test
    void dijkstra_withExampleGraph_shortestPathToA() {
        Graph.Edge[] graphEdges = createExampleGraphEdges();
        Graph g = new Graph(graphEdges);
        g.dijkstra("a");

        String expectedPathToA = "a";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        g.printPath("a");
        assertEquals(expectedPathToA + System.lineSeparator(), outContent.toString());

        System.setOut(System.out);
    }

    @Test
    void dijkstra_withEmptyGraph() {
        Graph g = new Graph(new Graph.Edge[0]);
        g.dijkstra("a");  // Should not throw any exceptions
    }


    @Test
    void dijkstra_withNonExistentStartNode() {
        Graph.Edge[] graphEdges = createExampleGraphEdges();
        Graph g = new Graph(graphEdges);

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new java.io.PrintStream(errContent));
        g.dijkstra("z");
        String expectedError = "Graph doesn't contain start vertex \"z\"\n";
        assertEquals(expectedError, errContent.toString());

        System.setErr(System.err);


    }


    @Test
    void dijkstra_withDisconnectedGraph() {
        Graph.Edge[] edges = {
                new Graph.Edge("a", "b", 7),
                new Graph.Edge("c", "d", 5)
        };
        Graph g = new Graph(edges);
        g.dijkstra("a");
        assertEquals("a -> b(7)", capturePrintPathOutput(g, "b"));
        assertEquals("c -> d(5)", capturePrintPathOutput(g, "d"));
        assertEquals("a(unreached)", capturePrintPathOutput(g, "c")); // c is unreachable from a


    }


    @Test
    void printPath_withNonExistentEndNode() {
        Graph.Edge[] graphEdges = createExampleGraphEdges();
        Graph g = new Graph(graphEdges);

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new java.io.PrintStream(errContent));
        g.printPath("nonexistent");
        assertEquals("Graph doesn't contain end vertex \"nonexistent\"\n", errContent.toString());
        System.setErr(System.err); // Resetting error output stream

    }




    private Graph.Edge[] createExampleGraphEdges() {
        return new Graph.Edge[]{
                new Graph.Edge("a", "b", 7),
                new Graph.Edge("a", "c", 9),
                new Graph.Edge("a", "f", 14),
                new Graph.Edge("b", "c", 10),
                new Graph.Edge("b", "d", 15),
                new Graph.Edge("c", "d", 11),
                new Graph.Edge("c", "f", 2),
                new Graph.Edge("d", "e", 6),
                new Graph.Edge("e", "f", 9)
        };
    }

    private String capturePrintPathOutput(Graph g, String endName) {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        g.printPath(endName);
        System.setOut(System.out); // Resetting the output stream
        return outContent.toString().trim();
    }
}
