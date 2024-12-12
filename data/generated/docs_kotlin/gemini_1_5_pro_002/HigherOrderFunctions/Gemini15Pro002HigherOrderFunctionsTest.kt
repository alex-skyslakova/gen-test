import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HigherOrderFunctionsTest {

    @Test
    fun testMapAndAverage() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)

        fun calculateAverage(transform: (Double) -> Double): Double {
            return list.map(transform).average()
        }

        val a = calculateAverage { x -> x + 2 }
        assertEquals(7.0, a, 0.0001)

        val h = calculateAverage { x -> x * x }
        assertEquals(38.5, h, 0.0001)

        val g = calculateAverage { x -> x * x * x }
        assertEquals(332.5, g, 0.0001)


        // Test with an empty list
        val emptyList = emptyList<Double>()
        val emptyListAverage = calculateAverage { x -> x + 2} // Transformation doesn't matter for empty list
        assertTrue(emptyListAverage.isNaN())


        // Test with a list containing NaN
        val nanList = listOf(1.0, 2.0, Double.NaN)
        val nanListAverage = calculateAverage{ x -> x * 2}
        assertTrue(nanListAverage.isNaN())

       // Test with a list containing Infinity
        val infList = listOf(1.0, 2.0, Double.POSITIVE_INFINITY)
        val infListAverage = calculateAverage{ x -> x * 2}
        assertEquals(Double.POSITIVE_INFINITY, infListAverage)
    }
}
