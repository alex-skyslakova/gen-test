import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LudicNumbersTest {

    @Test
    fun testLudicFirst25() {
        val lu = ludic(25)
        val expected = intArrayOf(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89)
        assertArrayEquals(expected, lu)
    }

    @Test
    fun testLudicN1() {
        val lu = ludic(1)
        val expected = intArrayOf(1)
        assertArrayEquals(expected, lu)
    }
    
    @Test
    fun testLudicN0() {
        val lu = ludic(0)
        val expected = intArrayOf()
        assertArrayEquals(expected, lu)
    }


    @Test
    fun testLudicCountLessThan1000() {
        val lu = ludic(2000) // Generate enough ludic numbers
        val count = lu.count { it <= 1000 }
        assertEquals(142, count)
    }

    @Test
    fun testLudic2000To2005() {
        val lu = ludic(2005)
        val expected = intArrayOf(27641, 27647, 27653, 27659, 27683, 27689)
        val actual = intArrayOf(lu[1999], lu[2000], lu[2001], lu[2002], lu[2003], lu[2004])
        assertArrayEquals(expected, actual)
    }


}
