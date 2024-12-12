import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class FloydWarshallTest {

    @Test
    fun testFloydWarshallAlgorithm() {
        val weights = arrayOf(
            intArrayOf(1, 3, -2),
            intArrayOf(2, 1, 4),
            intArrayOf(2, 3, 3),
            intArrayOf(3, 4, 2),
            intArrayOf(4, 2, -1)
        )
        val nVertices = 4

        // Redirect standard output to capture the print statements
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Execute the Floyd-Warshall algorithm
        FloydWarshall.doCalcs(weights, nVertices)

        // Expected output
        val expectedOutput = """
            pair     dist    path
            1 -> 2    -1     1 -> 3 -> 4 -> 2
            1 -> 3    -2     1 -> 3
            1 -> 4     0     1 -> 3 -> 4
            2 -> 1     4     2 -> 1
            2 -> 3     2     2 -> 1 -> 3
            2 -> 4     4     2 -> 1 -> 3 -> 4
            3 -> 1     5     3 -> 4 -> 2 -> 1
            3 -> 2     1     3 -> 4 -> 2
            3 -> 4     2     3 -> 4
            4 -> 1     3     4 -> 2 -> 1
            4 -> 2    -1     4 -> 2
            4 -> 3     1     4 -> 2 -> 1 -> 3
        """.trimIndent()

        // Compare the captured output with the expected output
        assertEquals(expectedOutput, outputStream.toString().trim())

        // Reset the standard output
        System.setOut(System.out)
    }
}
