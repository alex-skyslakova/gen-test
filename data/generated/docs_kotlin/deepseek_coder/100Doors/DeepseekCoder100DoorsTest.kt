import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class OneHundredDoorsTest {

    @Test
    fun testOneHundredDoors() {
        val expectedOpenDoors = listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)
        val actualOpenDoors = oneHundredDoors()

        assertEquals(expectedOpenDoors, actualOpenDoors)
    }

    @Test
    fun testOneHundredDoors_AllClosedInitially() {
        val doors = BooleanArray(100, { false })
        assertFalse(doors.any { it })
    }

    @Test
    fun testOneHundredDoors_FirstPass() {
        val doors = BooleanArray(100, { false })
        for (i in 0..99) {
            doors[i] = !doors[i]
        }
        assertTrue(doors.all { it })
    }

    @Test
    fun testOneHundredDoors_SecondPass() {
        val doors = BooleanArray(100, { true })
        for (i in 1..99 step 2) {
            doors[i] = !doors[i]
        }
        for (i in 0..99) {
            if (i % 2 == 0) {
                assertTrue(doors[i])
            } else {
                assertFalse(doors[i])
            }
        }
    }

    @Test
    fun testOneHundredDoors_ThirdPass() {
        val doors = BooleanArray(100, { true })
        for (i in 2..99 step 3) {
            doors[i] = !doors[i]
        }
        for (i in 0..99) {
            if (i % 3 == 0) {
                assertFalse(doors[i])
            } else {
                assertTrue(doors[i])
            }
        }
    }

    @Test
    fun testOneHundredDoors_FinalState() {
        val doors = BooleanArray(100, { false })
        for (i in 0..99) {
            for (j in i..99 step (i + 1)) {
                doors[j] = !doors[j]
            }
        }
        for (i in 0..99) {
            if (i + 1 in listOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)) {
                assertTrue(doors[i])
            } else {
                assertFalse(doors[i])
            }
        }
    }
}
