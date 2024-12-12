import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class MultDigitalRootTest {

    @Test
    fun testMultDigitalRoot() {
        // Test cases for MDR and MP
        val testCases = mapOf(
            123321 to Pair(8, 3),
            7739 to Pair(8, 3),
            893 to Pair(2, 4),
            899998 to Pair(0, 2)
        )

        for ((input, expected) in testCases) {
            val (mdr, mp) = multDigitalRoot(input)
            assertEquals(expected.first, mdr, "MDR for $input")
            assertEquals(expected.second, mp, "MP for $input")
        }
    }

    @Test
    fun testNegativeInput() {
        // Test negative input handling
        assertThrows<IllegalArgumentException> {
            multDigitalRoot(-1)
        }
    }

    @Test
    fun testSingleDigitInput() {
        // Test single digit input
        val testCases = mapOf(
            0 to Pair(0, 0),
            1 to Pair(1, 0),
            9 to Pair(9, 0)
        )

        for ((input, expected) in testCases) {
            val (mdr, mp) = multDigitalRoot(input)
            assertEquals(expected.first, mdr, "MDR for $input")
            assertEquals(expected.second, mp, "MP for $input")
        }
    }

    @Test
    fun testMultiDigitInput() {
        // Test multi-digit input
        val testCases = mapOf(
            10 to Pair(0, 1),
            25 to Pair(0, 1),
            30 to Pair(0, 1),
            111 to Pair(1, 1),
            1111 to Pair(1, 2),
            11111 to Pair(1, 2),
            26 to Pair(2, 2),
            34 to Pair(4, 2),
            113 to Pair(3, 2),
            131 to Pair(3, 2),
            22 to Pair(4, 1),
            27 to Pair(4, 2),
            39 to Pair(4, 2),
            35 to Pair(5, 1),
            51 to Pair(5, 1),
            53 to Pair(5, 1),
            16 to Pair(6, 1),
            23 to Pair(6, 1),
            28 to Pair(6, 1),
            32 to Pair(6, 1),
            17 to Pair(7, 1),
            71 to Pair(7, 1),
            117 to Pair(7, 2),
            171 to Pair(7, 2),
            18 to Pair(8, 1),
            24 to Pair(8, 1),
            29 to Pair(8, 1),
            36 to Pair(8, 1),
            19 to Pair(9, 1),
            33 to Pair(9, 1),
            91 to Pair(9, 1),
            119 to Pair(9, 2)
        )

        for ((input, expected) in testCases) {
            val (mdr, mp) = multDigitalRoot(input)
            assertEquals(expected.first, mdr, "MDR for $input")
            assertEquals(expected.second, mp, "MP for $input")
        }
    }
}
