import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class MultiplicativeDigitalRootTest {

    @Test
    fun testNegativeInput() {
        assertThrows<IllegalArgumentException> { multDigitalRoot(-1) }
    }

    @Test
    fun testZero() {
        val (mdr, mp) = multDigitalRoot(0)
        assertEquals(0, mdr)
        assertEquals(1, mp)
    }


    @Test
    fun testSingleDigit() {
        val (mdr, mp) = multDigitalRoot(5)
        assertEquals(5, mdr)
        assertEquals(1, mp)
    }

    @Test
    fun testExampleCases() {
        val testCases = listOf(
            123321 to Pair(0, 3),
            7739 to Pair(0, 2),
            893 to Pair(2, 2),
            899998 to Pair(0, 2)
        )

        for ((input, expected) in testCases) {
            val (actualMdr, actualMp) = multDigitalRoot(input)
            assertEquals(expected.first, actualMdr, "MDR for $input")
            assertEquals(expected.second, actualMp, "MP for $input")
        }
    }

    @Test
    fun testMultiplePersistence() {
        val (mdr, mp) = multDigitalRoot(39)
        assertEquals(7, mdr)
        assertEquals(3, mp) // 39 -> 27 -> 14 -> 4
    }

    @Test
    fun testLargeNumber() {
        val (mdr, mp) = multDigitalRoot(277777788888899)
        assertEquals(0, mdr)
        assertEquals(3, mp)
    }
     @Test
    fun testNumberWithZero() {
        val (mdr, mp) = multDigitalRoot(102)
        assertEquals(0, mdr)
        assertEquals(2, mp)
    }


}
