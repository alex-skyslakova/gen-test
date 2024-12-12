import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FuscSequenceTest {

    @Test
    fun testFuscBaseCases() {
        // Test base cases
        assertArrayEquals(intArrayOf(), fusc(0))
        assertArrayEquals(intArrayOf(0), fusc(1))
    }

    @Test
    fun testFuscFirst61Numbers() {
        // Test the first 61 fusc numbers
        val expected = intArrayOf(
            0, 1, 1, 2, 1, 3, 2, 3, 1, 4, 3, 5, 2, 5, 3, 4, 1, 5, 4, 7, 3, 8, 5, 7, 2, 7, 5, 8, 3, 7, 4, 5, 1, 6, 5, 9, 4, 11, 7, 10, 3, 11, 8, 13, 5, 12, 7, 9, 2, 9, 7, 12, 5, 13, 8, 11, 3, 10, 7, 11, 4, 9, 5, 6
        )
        assertArrayEquals(expected, fusc(61))
    }

    @Test
    fun testFuscMaxLen() {
        // Test the fuscMaxLen function
        val expected = listOf(
            Pair(0, 0),
            Pair(1, 1),
            Pair(2, 2),
            Pair(3, 3),
            Pair(4, 4),
            Pair(5, 5),
            Pair(6, 6),
            Pair(7, 7),
            Pair(8, 8),
            Pair(9, 9),
            Pair(10, 10),
            Pair(11, 11),
            Pair(12, 12),
            Pair(13, 13),
            Pair(14, 14),
            Pair(15, 15),
            Pair(16, 16),
            Pair(17, 17),
            Pair(18, 18),
            Pair(19, 19),
            Pair(20, 20),
            Pair(21, 21),
            Pair(22, 22),
            Pair(23, 23),
            Pair(24, 24),
            Pair(25, 25),
            Pair(26, 26),
            Pair(27, 27),
            Pair(28, 28),
            Pair(29, 29),
            Pair(30, 30),
            Pair(31, 31),
            Pair(32, 32),
            Pair(33, 33),
            Pair(34, 34),
            Pair(35, 35),
            Pair(36, 36),
            Pair(37, 37),
            Pair(38, 38),
            Pair(39, 39),
            Pair(40, 40),
            Pair(41, 41),
            Pair(42, 42),
            Pair(43, 43),
            Pair(44, 44),
            Pair(45, 45),
            Pair(46, 46),
            Pair(47, 47),
            Pair(48, 48),
            Pair(49, 49),
            Pair(50, 50),
            Pair(51, 51),
            Pair(52, 52),
            Pair(53, 53),
            Pair(54, 54),
            Pair(55, 55),
            Pair(56, 56),
            Pair(57, 57),
            Pair(58, 58),
            Pair(59, 59),
            Pair(60, 60)
        )
        assertEquals(expected, fuscMaxLen(61))
    }

    @Test
    fun testFuscMaxLenLargeInput() {
        // Test the fuscMaxLen function with a large input
        val res = fuscMaxLen(20_000_000)
        // Check the first few results manually
        assertEquals(0, res[0].first)
        assertEquals(0, res[0].second)
        assertEquals(1, res[1].first)
        assertEquals(1, res[1].second)
        assertEquals(2, res[2].first)
        assertEquals(2, res[2].second)
        // Add more assertions as needed
    }
}
