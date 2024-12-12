import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class HigherOrderFunctionsTest {

    @Test
    fun testMapAndAverage() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)

        // Test the transformation and average for the first function (x + 2)
        val a = list.map { x -> x + 2 }.average()
        assertEquals(7.5, a, 0.0001)

        // Test the transformation and average for the second function (x * x)
        val h = list.map { x -> x * x }.average()
        assertEquals(38.5, h, 0.0001)

        // Test the transformation and average for the third function (x * x * x)
        val g = list.map { x -> x * x * x }.average()
        assertEquals(372.5, g, 0.0001)
    }
}
