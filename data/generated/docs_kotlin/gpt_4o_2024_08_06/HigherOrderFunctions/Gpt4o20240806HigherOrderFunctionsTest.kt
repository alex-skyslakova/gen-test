import kotlin.test.Test
import kotlin.test.assertEquals

class HigherOrderFunctionsTest {

    @Test
    fun testAverageAfterAddingTwo() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val expectedAverage = list.map { it + 2 }.average()
        assertEquals(7.0, expectedAverage, "The average after adding 2 to each element should be 7.0")
    }

    @Test
    fun testAverageOfSquares() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val expectedAverage = list.map { it * it }.average()
        assertEquals(38.5, expectedAverage, "The average of squares should be 38.5")
    }

    @Test
    fun testAverageOfCubes() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0)
        val expectedAverage = list.map { it * it * it }.average()
        assertEquals(302.5, expectedAverage, "The average of cubes should be 302.5")
    }
}
