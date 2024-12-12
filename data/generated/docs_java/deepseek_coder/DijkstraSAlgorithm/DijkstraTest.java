import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class DijkstraTest {

    @Test
    public void testDijkstraAlgorithm() {
        // Define the graph edges
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

        // Create the graph
        Graph graph = new Graph(edges);

        // Run Dijkstra's algorithm starting from node "a"
        graph.dijkstra("a");

        // Test the shortest path to node "e"
        Graph.Vertex e = graph.graph.get("e");
        assertNotNull(e);
        assertEquals(26, e.dist);
        assertEquals("d", e.previous.name);

        // Test the shortest path to node "f"
        Graph.Vertex f = graph.graph.get("f");
        assertNotNull(f);
        assertEquals(11, f.dist);
        assertEquals("c", f.previous.name);

        // Test the shortest path to node "b"
        Graph.Vertex b = graph.graph.get("b");
        assertNotNull(b);
        assertEquals(7, b.dist);
        assertEquals("a", b.previous.name);

        // Test the shortest path to node "c"
        Graph.Vertex c = graph.graph.get("c");
        assertNotNull(c);
        assertEquals(9, c.dist);
        assertEquals("a", c.previous.name);

        // Test the shortest path to node "d"
        Graph.Vertex d = graph.graph.get("d");
        assertNotNull(d);
        assertEquals(20, d.dist);
        assertEquals("c", d.previous.name);

        // Test the shortest path to node "a" (source node)
        Graph.Vertex a = graph.graph.get("a");
        assertNotNull(a);
        assertEquals(0, a.dist);
        assertNull(a.previous);
    }
}
