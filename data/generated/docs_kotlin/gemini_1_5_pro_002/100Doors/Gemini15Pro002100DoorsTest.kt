import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class OneHundredDoorsTest {

    @Test
    fun `testOneHundredDoors`() {
        val expected = listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
        val actual = oneHundredDoors()
        assertEquals(expected, actual)
    }

    @Test
    fun `testFirstFewDoors`() {
        val doors = BooleanArray(10, { false })
        for (i in 0..9) {
            for (j in i..9 step (i + 1)) {
                doors[j] = !doors[j]
            }
        }
         val actual = doors
            .mapIndexed { i, b -> i to b }
            .filter { it.second }
            .map { it.first + 1 }
        val expected = listOf(1, 4, 9)
        assertEquals(expected, actual)
    }


    @Test
    fun `testEmptyDoors`() {
        val doors = BooleanArray(0, { false })
        for (i in 0..-1) { //This loop won't execute
            for (j in i..-1 step (i + 1)) { //This loop won't execute
                doors[j] = !doors[j]
            }
        }
        val actual = doors
            .mapIndexed { i, b -> i to b }
            .filter { it.second }
            .map { it.first + 1 }
        val expected = emptyList<Int>()
        assertEquals(expected, actual)


    }

    @Test
    fun `testSingleDoor`() {
        val doors = BooleanArray(1, { false })
        for (i in 0..0) {
            for (j in i..0 step (i + 1)) {
                doors[j] = !doors[j]
            }
        }
         val actual = doors
            .mapIndexed { i, b -> i to b }
            .filter { it.second }
            .map { it.first + 1 }
        val expected = listOf(1)
        assertEquals(expected, actual)
    }

}
