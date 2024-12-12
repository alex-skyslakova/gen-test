import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigDecimal

class KnuthPowerTreeTest {

    @Test
    fun testPathZero() {
        assertEquals(emptyList<Int>(), path(0))
    }

    @Test
    fun testPathOne() {
        assertEquals(listOf(1), path(1))
    }

    @Test
    fun testPathSmallNumbers() {
        assertEquals(listOf(1, 2), path(2))
        assertEquals(listOf(1, 2, 4), path(4))
        assertEquals(listOf(1, 2, 3), path(3))
        assertEquals(listOf(1, 2, 4, 8), path(8))
        assertEquals(listOf(1, 2, 3, 5), path(5))
    }


    @Test
    fun testPathLargerNumber() {
        assertEquals(listOf(1, 2, 4, 8, 16, 17), path(17))
        assertEquals(listOf(1, 2, 3, 5, 10, 20, 40, 41), path(41))

    }

    @Test
    fun testTreePowZero() {
        assertEquals(BigDecimal.ONE, treePow(2.0, 0))
        assertEquals(BigDecimal.ONE, treePow(3.0, 0))
        assertEquals(BigDecimal.ONE, treePow(1.1, 0))

    }

    @Test
    fun testTreePowOne() {
        assertEquals(BigDecimal(2.0), treePow(2.0, 1))
        assertEquals(BigDecimal(3.0), treePow(3.0, 1))
        assertEquals(BigDecimal(1.1), treePow(1.1, 1))
    }


    @Test
    fun testTreePowTwo() {
        assertEquals(BigDecimal(4.0), treePow(2.0, 2))
        assertEquals(BigDecimal(9.0), treePow(3.0, 2))
        assertEquals(BigDecimal("1.21"), treePow(1.1, 2))

    }

    @Test
    fun testTreePowLargerNumbers() {
        assertEquals(BigDecimal(131072.0), treePow(2.0, 17))
        assertEquals(BigDecimal("1162261467").setScale(0, BigDecimal.RoundingMode.HALF_EVEN) , treePow(3.0, 19).setScale(0, BigDecimal.RoundingMode.HALF_EVEN)) // added rounding for comparison
    }

    @Test
    fun testTreePowDecimal() {

        assertEquals(BigDecimal("2.1435888100").setScale(10, BigDecimal.RoundingMode.HALF_EVEN), treePow(1.1, 8).setScale(10, BigDecimal.RoundingMode.HALF_EVEN)) //added rounding to make comparison possible
    }

    // Helper function to reset the mutable state between tests.  Important because the original code uses mutable state which can interfere with test independence.
     private fun resetPowerTreeState(){
        p.clear()
        p[1] = 0
        lvl.clear()
        lvl.add(listOf(1))
    }


    @org.junit.jupiter.api.BeforeEach
    fun setUp() {
        resetPowerTreeState()
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
       resetPowerTreeState()
    }
}


