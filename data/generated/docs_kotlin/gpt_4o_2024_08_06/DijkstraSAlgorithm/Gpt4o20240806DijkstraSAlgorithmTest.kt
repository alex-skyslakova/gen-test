import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DijkstraAlgorithmTest {

    private val graphEdges = listOf(
        Edge("a", "b", 7),
        Edge("a", "c", 9),
        Edge("a", "f", 14),
        Edge("b", "c", 10),
        Edge("b", "d", 15),
        Edge("c", "d", 11),
        Edge("c", "f", 2),
        Edge("d", "e", 6),
        Edge("e", "f", 9)
    )

    @Test
    fun testShortestPathToE() {
        val graph = Graph(graphEdges, true)
        graph.dijkstra("a")
        val expectedPath = "a -> c(9) -> d(20) -> e(26)"
        assertEquals(expectedPath, captureOutput { graph.printPath("e") })
    }

    @Test
    fun testShortestPathToF() {
        val graph = Graph(graphEdges, true)
        graph.dijkstra("a")
        val expectedPath = "a -> c(9) -> f(11)"
        assertEquals(expectedPath, captureOutput { graph.printPath("f") })
    }

    @Test
    fun testUnreachableNode() {
        val graph = Graph(graphEdges, true)
        graph.dijkstra("a")
        val expectedOutput = "Graph doesn't contain end vertex 'z'"
        assertEquals(expectedOutput, captureOutput { graph.printPath("z") })
    }

    @Test
    fun testShortestPathInUndirectedGraphToE() {
        val graph = Graph(graphEdges, false)
        graph.dijkstra("a")
        val expectedPath = "a -> b(7) -> d(22) -> e(28)"
        assertEquals(expectedPath, captureOutput { graph.printPath("e") })
    }

    @Test
    fun testShortestPathInUndirectedGraphToF() {
        val graph = Graph(graphEdges, false)
        graph.dijkstra("a")
        val expectedPath = "a -> f(14)"
        assertEquals(expectedPath, captureOutput { graph.printPath("f") })
    }

    private fun captureOutput(block: () -> Unit): String {
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        block()
        return outputStream.toString().trim()
    }
}
