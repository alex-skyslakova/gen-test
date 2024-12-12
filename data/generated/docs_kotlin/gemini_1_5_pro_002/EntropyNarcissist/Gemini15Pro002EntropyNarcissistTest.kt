import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class EntropyNarcissistTest {

    @Test
    fun testLog2() {
        assertEquals(0.0, log2(1.0))
        assertEquals(1.0, log2(2.0))
        assertEquals(2.0, log2(4.0))
        assertEquals(3.0, log2(8.0))
        assertEquals(Double.NEGATIVE_INFINITY, log2(0.0))
    }

    @Test
    fun testShannonEmpty() {
        assertEquals(0.0, shannon(""))
    }

    @Test
    fun testShannonSingleChar() {
        assertEquals(0.0, shannon("a"))
        assertEquals(0.0, shannon("b"))
        assertEquals(0.0, shannon("!"))
    }


    @Test
    fun testShannonTwoCharsEqual() {
        assertEquals(0.0, shannon("aa"))
        assertEquals(0.0, shannon("bb"))
    }

    @Test
    fun testShannonTwoCharsDifferent() {
        assertEquals(1.0, shannon("ab"))
        assertEquals(1.0, shannon("ba"))
    }
    
    @Test
    fun testShannonFourCharsEqual() {
         assertEquals(0.0, shannon("aaaa"))
    }

    @Test
    fun testShannonFourCharsTwoPairs() {
         assertEquals(1.0, shannon("aabb"))
         assertEquals(1.0, shannon("abab"))
         assertEquals(1.0, shannon("abba"))
    }

     @Test
    fun testShannonFourCharsAllDifferent() {
         assertEquals(2.0, shannon("abcd"))
    }

    @Test
    fun testShannonExample() {
        val text = "This program computes and shows its own entropy."
        val expectedEntropy = 3.251629167387834 // Pre-calculated value
        assertEquals(expectedEntropy, shannon(text), 0.000000000000001) 
    }

}

