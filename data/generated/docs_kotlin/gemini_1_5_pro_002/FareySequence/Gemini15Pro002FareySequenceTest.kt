import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FareySequenceTest {

    @Test
    fun testFarey1() {
        assertEquals(listOf("0/1", "1/1"), farey(1))
    }

    @Test
    fun testFarey2() {
        assertEquals(listOf("0/1", "1/2", "1/1"), farey(2))
    }

    @Test
    fun testFarey3() {
        assertEquals(listOf("0/1", "1/3", "1/2", "2/3", "1/1"), farey(3))
    }

    @Test
    fun testFarey4() {
        assertEquals(listOf("0/1", "1/4", "1/3", "1/2", "2/3", "3/4", "1/1"), farey(4))
    }

    @Test
    fun testFarey5() {
        assertEquals(listOf("0/1", "1/5", "1/4", "1/3", "2/5", "1/2", "3/5", "2/3", "3/4", "4/5", "1/1"), farey(5))
    }

    @Test
    fun testFarey11() {
        assertEquals(listOf("0/1", "1/11", "1/10", "1/9", "1/8", "1/7", "1/6", "1/5", "2/11", "1/4", "2/7", "3/11", "1/3", "4/11", "2/5", "3/7", "5/11", "1/2", "6/11", "3/5", "4/7", "7/11", "2/3", "8/11", "3/4", "5/7", "7/10", "4/5", "5/8", "2/11/11", "3/5", "5/7", "7/9", "4/5", "5/6", "6/7", "7/8", "8/9", "9/10", "10/11", "1/1"), farey(11))

    }


    @Test
    fun testFareySize100() {
        assertEquals(3045, farey(100).size)
    }

    @Test
    fun testFareySize200() {
        assertEquals(12181, farey(200).size)

    }
    @Test
    fun testFareySize300() {
        assertEquals(27365, farey(300).size)
    }
    @Test
    fun testFareySize400() {
        assertEquals(48585, farey(400).size)
    }
    @Test
    fun testFareySize500() {
        assertEquals(75853, farey(500).size)
    }
    @Test
    fun testFareySize600() {
        assertEquals(109133, farey(600).size)
    }
    @Test
    fun testFareySize700() {
        assertEquals(148481, farey(700).size)
    }
    @Test
    fun testFareySize800() {
        assertEquals(193841, farey(800).size)
    }
    @Test
    fun testFareySize900() {
        assertEquals(245245, farey(900).size)
    }
    @Test
    fun testFareySize1000() {
        assertEquals(302641, farey(1000).size)
    }
}
