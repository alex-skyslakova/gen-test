import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class OneHundredDoorsTest {

    @Test
    fun testOneHundredDoors() {
        val expectedOpenDoors = listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
        val result = oneHundredDoors()
        assertEquals(expectedOpenDoors, result, "The open doors should be those with perfect square numbers.")
    }
}
