import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DigitalRootTest {

    @Test
    fun testMultDigitalRoot() {
        // Test cases for given numbers
        assertEquals(Pair(8, 3), multDigitalRoot(123321))
        assertEquals(Pair(3, 4), multDigitalRoot(7739))
        assertEquals(Pair(8, 2), multDigitalRoot(893))
        assertEquals(Pair(2, 3), multDigitalRoot(899998))
    }

    @Test
    fun testNegativeNumber() {
        // Test case for negative number
        assertThrows<IllegalArgumentException> {
            multDigitalRoot(-1)
        }
    }

    @Test
    fun testMDRTable() {
        // Expected MDR table for first 50 numbers
        val expectedMDRTable = mapOf(
            0 to listOf(0, 10, 20, 25, 30),
            1 to listOf(1, 11, 111, 1111, 11111),
            2 to listOf(2, 12, 21, 26, 34),
            3 to listOf(3, 13, 31, 113, 131),
            4 to listOf(4, 14, 22, 27, 39),
            5 to listOf(5, 15, 35, 51, 53),
            6 to listOf(6, 16, 23, 28, 32),
            7 to listOf(7, 17, 71, 117, 171),
            8 to listOf(8, 18, 24, 29, 36),
            9 to listOf(9, 19, 33, 91, 119)
        )

        val actualMDRTable = mutableMapOf<Int, MutableList<Int>>()
        var n = 0
        var count = 0
        do {
            val (mdr, _) = multDigitalRoot(n)
            if (actualMDRTable.getOrDefault(mdr, mutableListOf()).size < 5) {
                actualMDRTable.computeIfAbsent(mdr) { mutableListOf() }.add(n)
                count++
            }
            n++
        } while (count < 50)

        assertEquals(expectedMDRTable, actualMDRTable)
    }
}
