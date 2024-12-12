import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FloydWarshallTest {

    @Test
    fun testExampleCase() {
        val weights = arrayOf(
            intArrayOf(1, 3, -2),
            intArrayOf(2, 1, 4),
            intArrayOf(2, 3, 3),
            intArrayOf(3, 4, 2),
            intArrayOf(4, 2, -1)
        )
        val nVertices = 4

        val expectedDistances = arrayOf(
            doubleArrayOf(0.0, -1.0, -2.0, 0.0),
            doubleArrayOf(4.0, 0.0, 2.0, 4.0),
            doubleArrayOf(5.0, 1.0, 0.0, 2.0),
            doubleArrayOf(3.0, -1.0, 1.0, 0.0)
        )


        val dist = Array(nVertices) { DoubleArray(nVertices) { Double.POSITIVE_INFINITY } }
        for (w in weights) dist[w[0] - 1][w[1] - 1] = w[2].toDouble()

        val next = Array(nVertices) { IntArray(nVertices) }
        for (i in 0 until next.size) {
            for (j in 0 until next.size) {
                if (i != j) next[i][j] = j + 1
            }
        }

        for (k in 0 until nVertices) {
            for (i in 0 until nVertices) {
                for (j in 0 until nVertices) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                        next[i][j] = next[i][k]
                    }
                }
            }
        }


        for (i in 0 until nVertices) {
            for (j in 0 until nVertices) {
                assertEquals(expectedDistances[i][j], dist[i][j])
            }
        }

    }



    @Test
    fun testNoEdges() {
        val weights = arrayOf<IntArray>()
        val nVertices = 4
        val expectedDistances = arrayOf(
                doubleArrayOf(0.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
                doubleArrayOf(Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
                doubleArrayOf(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0, Double.POSITIVE_INFINITY),
                doubleArrayOf(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0)
        )


        val dist = Array(nVertices) { DoubleArray(nVertices) { Double.POSITIVE_INFINITY } }
        for (w in weights) dist[w[0] - 1][w[1] - 1] = w[2].toDouble()

        for(i in 0 until nVertices) dist[i][i] = 0.0


        val next = Array(nVertices) { IntArray(nVertices) }
        for (i in 0 until next.size) {
            for (j in 0 until next.size) {
                if (i != j) next[i][j] = j + 1
            }
        }

        for (k in 0 until nVertices) {
            for (i in 0 until nVertices) {
                for (j in 0 until nVertices) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                        next[i][j] = next[i][k]
                    }
                }
            }
        }


        for (i in 0 until nVertices) {
            for (j in 0 until nVertices) {
                assertEquals(expectedDistances[i][j], dist[i][j])
            }
        }
    }



    @Test
    fun testOneNodeGraph(){
        val weights = arrayOf<IntArray>()
        val nVertices = 1
        val expectedDistances = arrayOf(
            doubleArrayOf(0.0)
        )


        val dist = Array(nVertices) { DoubleArray(nVertices) { Double.POSITIVE_INFINITY } }
        for (w in weights) dist[w[0] - 1][w[1] - 1] = w[2].toDouble()

        for(i in 0 until nVertices) dist[i][i] = 0.0

        val next = Array(nVertices) { IntArray(nVertices) }


        for (k in 0 until nVertices) {
            for (i in 0 until nVertices) {
                for (j in 0 until nVertices) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]
                        next[i][j] = next[i][k]
                    }
                }
            }
        }


        for (i in 0 until nVertices) {
            for (j in 0 until nVertices) {
                assertEquals(expectedDistances[i][j], dist[i][j])
            }
        }
    }

}
