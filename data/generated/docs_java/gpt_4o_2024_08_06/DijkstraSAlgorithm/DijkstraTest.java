import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DijkstraTest {

    private Graph graph;

    @Before
    public void setUp() {
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
    public void testShortestPathToE() {
        graph.dijkstra("a");
        Graph.Vertex endVertex = graph.graph.get("e");
        assertNotNull("Vertex e should be reachable", endVertex);
        assertEquals("Shortest path cost to e should be 26", 26, endVertex.dist);
    }

    @Test
    public void testShortestPathToF() {
        graph.dijkstra("a");
        Graph.Vertex endVertex = graph.graph.get("f");
        assertNotNull("Vertex f should be reachable", endVertex);
        assertEquals("Shortest path cost to f should be 11", 11, endVertex.dist);
    }

    @Test
    public void testShortestPathToUnreachableNode() {
        Graph.Edge[] edges = {
            new Graph.Edge("a", "b", 7),
            new Graph.Edge("b", "c", 10),
        };
        Graph disconnectedGraph = new Graph(edges);
        disconnectedGraph.dijkstra("a");
        Graph.Vertex endVertex = disconnectedGraph.graph.get("c");
        assertNotNull("Vertex c should be reachable", endVertex);
        assertEquals("Shortest path cost to c should be 17", 17, endVertex.dist);

        endVertex = disconnectedGraph.graph.get("d");
        assertNull("Vertex d should be unreachable", endVertex);
    }

    @Test
    public void testShortestPathToSelf() {
        graph.dijkstra("a");
        Graph.Vertex startVertex = graph.graph.get("a");
        assertNotNull("Vertex a should be reachable", startVertex);
        assertEquals("Shortest path cost to a should be 0", 0, startVertex.dist);
    }

    @Test
    public void testGraphWithoutStartVertex() {
        graph.dijkstra("z");
        Graph.Vertex endVertex = graph.graph.get("e");
        assertNotNull("Vertex e should be reachable", endVertex);
        assertEquals("Shortest path cost to e should remain as infinity", Integer.MAX_VALUE, endVertex.dist);
    }
}
