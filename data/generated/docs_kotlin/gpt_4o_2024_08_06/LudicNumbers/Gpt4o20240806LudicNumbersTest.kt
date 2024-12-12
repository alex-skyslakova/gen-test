import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class LudicNumbersTest {

    @Test
    fun testFirst25LudicNumbers() {
        val expected = intArrayOf(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 25, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83)
        val actual = ludic(25)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun testLudicNumbersLessThanOrEqualTo1000() {
        val lu = ludic(2005)
        val count = lu.count { it <= 1000 }
        assertEquals(239, count)
    }

    @Test
    fun test2000thTo2005thLudicNumbers() {
        val expected = intArrayOf(14203, 14207, 14219, 14221, 14227, 14231)
        val lu = ludic(2005)
        val actual = lu.sliceArray(1999..2004)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun testLudicTripletsBelow250() {
        val expectedTriplets = listOf(
            Triple(1, 3, 7),
            Triple(19, 21, 25),
            Triple(37, 39, 43),
            Triple(61, 63, 67),
            Triple(97, 99, 103),
            Triple(127, 129, 133),
            Triple(151, 153, 157),
            Triple(189, 191, 195),
            Triple(223, 225, 229)
        )
        val lu = ludic(250)
        val actualTriplets = mutableListOf<Triple<Int, Int, Int>>()
        for (i in 0 until lu.size) {
            val x = lu[i]
            if (x + 6 >= 250) break
            if (lu.contains(x + 2) && lu.contains(x + 6)) {
                actualTriplets.add(Triple(x, x + 2, x + 6))
            }
        }
        assertEquals(expectedTriplets, actualTriplets)
    }
}
