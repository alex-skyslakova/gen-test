import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FareySequenceTest {

    @Test
    fun testFareySequenceOrder1() {
        val expected = listOf("0/1", "1/1")
        assertEquals(expected, farey(1))
    }

    @Test
    fun testFareySequenceOrder2() {
        val expected = listOf("0/1", "1/2", "1/1")
        assertEquals(expected, farey(2))
    }

    @Test
    fun testFareySequenceOrder3() {
        val expected = listOf("0/1", "1/3", "1/2", "2/3", "1/1")
        assertEquals(expected, farey(3))
    }

    @Test
    fun testFareySequenceOrder4() {
        val expected = listOf("0/1", "1/4", "1/3", "1/2", "2/3", "3/4", "1/1")
        assertEquals(expected, farey(4))
    }

    @Test
    fun testFareySequenceOrder5() {
        val expected = listOf("0/1", "1/5", "1/4", "1/3", "2/5", "1/2", "3/5", "2/3", "3/4", "4/5", "1/1")
        assertEquals(expected, farey(5))
    }

    @Test
    fun testFareySequenceOrder6() {
        val expected = listOf("0/1", "1/6", "1/5", "1/4", "1/3", "2/5", "1/2", "3/5", "2/3", "3/4", "4/5", "5/6", "1/1")
        assertEquals(expected, farey(6))
    }

    @Test
    fun testFareySequenceOrder7() {
        val expected = listOf("0/1", "1/7", "1/6", "1/5", "1/4", "2/7", "1/3", "2/5", "3/7", "1/2", "4/7", "3/5", "2/3", "5/7", "3/4", "4/5", "5/6", "6/7", "1/1")
        assertEquals(expected, farey(7))
    }

    @Test
    fun testFareySequenceOrder8() {
        val expected = listOf("0/1", "1/8", "1/7", "1/6", "1/5", "1/4", "2/7", "1/3", "3/8", "2/5", "3/7", "1/2", "4/7", "3/5", "5/8", "2/3", "5/7", "3/4", "4/5", "5/6", "6/7", "7/8", "1/1")
        assertEquals(expected, farey(8))
    }

    @Test
    fun testFareySequenceOrder9() {
        val expected = listOf("0/1", "1/9", "1/8", "1/7", "1/6", "1/5", "2/9", "1/4", "2/7", "1/3", "3/8", "2/5", "3/7", "4/9", "1/2", "5/9", "4/7", "3/5", "5/8", "2/3", "5/7", "3/4", "4/5", "5/6", "6/7", "7/8", "8/9", "1/1")
        assertEquals(expected, farey(9))
    }

    @Test
    fun testFareySequenceOrder10() {
        val expected = listOf("0/1", "1/10", "1/9", "1/8", "1/7", "1/6", "1/5", "2/9", "1/4", "3/10", "2/7", "1/3", "3/8", "2/5", "3/7", "4/9", "5/10", "1/2", "6/10", "5/9", "4/7", "3/5", "5/8", "2/3", "5/7", "3/4", "4/5", "5/6", "6/7", "7/8", "8/9", "9/10", "1/1")
        assertEquals(expected, farey(10))
    }

    @Test
    fun testFareySequenceOrder11() {
        val expected = listOf("0/1", "1/11", "1/10", "1/9", "1/8", "1/7", "1/6", "1/5", "2/9", "1/4", "3/11", "2/7", "1/3", "3/8", "2/5", "3/7", "4/9", "5/11", "1/2", "6/11", "5/9", "4/7", "3/5", "5/8", "2/3", "5/7", "3/4", "4/5", "5/6", "6/7", "7/8", "8/9", "9/10", "10/11", "1/1")
        assertEquals(expected, farey(11))
    }

    @Test
    fun testFareySequenceLength100() {
        assertEquals(3043, farey(100).size)
    }

    @Test
    fun testFareySequenceLength200() {
        assertEquals(12201, farey(200).size)
    }

    @Test
    fun testFareySequenceLength300() {
        assertEquals(27493, farey(300).size)
    }

    @Test
    fun testFareySequenceLength400() {
        assertEquals(48721, farey(400).size)
    }

    @Test
    fun testFareySequenceLength500() {
        assertEquals(75901, farey(500).size)
    }

    @Test
    fun testFareySequenceLength600() {
        assertEquals(109541, farey(600).size)
    }

    @Test
    fun testFareySequenceLength700() {
        assertEquals(149249, farey(700).size)
    }

    @Test
    fun testFareySequenceLength800() {
        assertEquals(195833, farey(800).size)
    }

    @Test
    fun testFareySequenceLength900() {
        assertEquals(250001, farey(900).size)
    }

    @Test
    fun testFareySequenceLength1000() {
        assertEquals(312601, farey(1000).size)
    }
}
