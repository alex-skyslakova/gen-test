import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FuscSequenceTest {

    @Test
    fun testFuscZero() {
        assertArrayEquals(intArrayOf(), fusc(0))
    }

    @Test
    fun testFuscOne() {
        assertArrayEquals(intArrayOf(0), fusc(1))
    }

    @Test
    fun testFuscSmall() {
        assertArrayEquals(intArrayOf(0, 1, 1, 2, 1, 3, 2, 3, 1, 4, 3, 5, 2, 5, 3, 4), fusc(16))
    }

    @Test
    fun testFuscMaxLenEmpty() {
        assertTrue(fuscMaxLen(0).isEmpty())
    }
    
    @Test
    fun testFuscMaxLenOne() {
        assertEquals(listOf(Pair(0,0)), fuscMaxLen(1))
    }

    @Test
    fun testFuscMaxLenSmall() {
        val expected = listOf(Pair(0, 0), Pair(1, 1), Pair(3, 2), Pair(5, 3), Pair(9, 4), Pair(17, 5))
        assertEquals(expected, fuscMaxLen(20))
    }

     @Test
    fun testFuscMaxLenMedium() {
        val expected = listOf(Pair(0, 0), Pair(1, 1), Pair(3, 2), Pair(5, 3), Pair(9, 4), Pair(17, 5), Pair(33, 6), Pair(65,7))
        assertEquals(expected, fuscMaxLen(70))
    }


}
