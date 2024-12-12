import org.junit.Test
import org.junit.Assert.*

class LudicNumbersTest {

    @Test
    fun testFirst25LudicNumbers() {
        val expected = intArrayOf(1, 2, 3, 5, 7, 11, 13, 17, 23, 25, 29, 37, 41, 43, 47, 53, 61, 67, 71, 77, 83, 89, 97, 107, 115)
        val result = ludic(25)
        assertArrayEquals(expected, result)
    }

    @Test
    fun testLudicNumbersLessThanOrEqualTo1000() {
        val result = ludic(2005)
        val count = result.count { it <= 1000 }
        assertEquals(142, count)
    }

    @Test
    fun test2000thTo2005thLudicNumbers() {
        val expected = intArrayOf(21475, 21481, 21487, 21493, 21503, 21511)
        val result = ludic(2005)
        val actual = result.copyOfRange(1999, 2005)
        assertArrayEquals(expected, actual)
    }

    @Test
    fun testLudicTripletsBelow250() {
        val result = ludic(2005)
        val triplets = mutableListOf<Triple<Int, Int, Int>>()
        var k: Int = 0
        var ldc: Int
        var b: Boolean
        for (i in 0..247) {
            ldc = result[i]
            if (ldc >= 244) break
            b = false
            for (j in i + 1..248) {
                if (result[j] == ldc + 2) {
                    b = true
                    k = j
                    break
                } else if (result[j] > ldc + 2) break
            }
            if (!b) continue
            for (j in k + 1..249) {
                if (result[j] == ldc + 6) {
                    triplets.add(Triple(ldc, ldc + 2, ldc + 6))
                    break
                } else if (result[j] > ldc + 6) break
            }
        }
        val expectedTriplets = listOf(
            Triple(1, 3, 7),
            Triple(5, 7, 11),
            Triple(11, 13, 17),
            Triple(23, 25, 29),
            Triple(41, 43, 47),
            Triple(173, 175, 179),
            Triple(221, 223, 227),
            Triple(233, 235, 239)
        )
        assertEquals(expectedTriplets, triplets)
    }

    @Test
    fun testLudicNumbersWithInvalidInput() {
        val result = ludic(0)
        assertArrayEquals(IntArray(0), result)
    }
}
