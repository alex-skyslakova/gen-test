import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class FourBitAdderTest {

    @Test
    fun testXorGate() {
        assertTrue(xorGate(true, false))
        assertTrue(xorGate(false, true))
        assertFalse(xorGate(true, true))
        assertFalse(xorGate(false, false))
    }

    @Test
    fun testHalfAdder() {
        assertEquals(Pair(false, false), halfAdder(false, false))
        assertEquals(Pair(true, false), halfAdder(true, false))
        assertEquals(Pair(true, false), halfAdder(false, true))
        assertEquals(Pair(false, true), halfAdder(true, true))
    }

    @Test
    fun testFullAdder() {
        assertEquals(Pair(false, false), fullAdder(false, false, false))
        assertEquals(Pair(true, false), fullAdder(true, false, false))
        assertEquals(Pair(true, false), fullAdder(false, true, false))
        assertEquals(Pair(false, true), fullAdder(true, true, false))
        assertEquals(Pair(true, false), fullAdder(false, false, true))
        assertEquals(Pair(false, true), fullAdder(true, false, true))
        assertEquals(Pair(false, true), fullAdder(false, true, true))
        assertEquals(Pair(true, true), fullAdder(true, true, true))
    }


    @Test
    fun testFourBitAdder_zeroPlusZero() {
        val a = Nybble(false, false, false, false)
        val b = Nybble(false, false, false, false)
        val expectedSum = Nybble(false, false, false, false)
        val expectedCarry = 0
        val (actualSum, actualCarry) = fourBitAdder(a, b)
        assertEquals(expectedSum.toString(), actualSum.toString())
        assertEquals(expectedCarry, actualCarry)
    }

    @Test
    fun testFourBitAdder_maxPlusOne() {
        val a = Nybble(true, true, true, true)
        val b = Nybble(false, false, false, true)
        val expectedSum = Nybble(false, false, false, false)
        val expectedCarry = 1
        val (actualSum, actualCarry) = fourBitAdder(a, b)
        assertEquals(expectedSum.toString(), actualSum.toString())
        assertEquals(expectedCarry, actualCarry)
    }

    @Test
    fun testFourBitAdder_sevenPlusFive() {
        val a = 7.toNybble() //0111
        val b = 5.toNybble() //0101
        val expectedSum = Nybble(false, true, true, false) // 1100 without carry, 12
        val expectedCarry = 0
        val (actualSum, actualCarry) = fourBitAdder(a, b)
        assertEquals(expectedSum.toString(), actualSum.toString())
        assertEquals(expectedCarry, actualCarry)

    }

     @Test
    fun testFourBitAdder_withCarryOut() {
        val a = 15.toNybble()
        val b = 1.toNybble()
        val (sum, carry) = fourBitAdder(a, b)
        assertEquals(0, sum.toInt()) // Should be 0
        assertEquals(1, carry)       // Carry out should be 1
    }


    @Test
    fun testNybbleToInt() {
        assertEquals(0, Nybble(false, false, false, false).toInt())
        assertEquals(1, Nybble(false, false, false, true).toInt())
        assertEquals(7, Nybble(false, true, true, true).toInt())
        assertEquals(15, Nybble(true, true, true, true).toInt())
    }

    @Test
    fun testIntToNybble() {
        assertEquals("0000", 0.toNybble().toString())
        assertEquals("0001", 1.toNybble().toString())
        assertEquals("0111", 7.toNybble().toString())
        assertEquals("1111", 15.toNybble().toString())
    }

    @Test
    fun testBooleanToInt() {
        assertEquals(0, false.I)
        assertEquals(1, true.I)
    }

    @Test
    fun testIntToBoolean() {
        assertFalse(0.B)
        assertTrue(1.B)
        assertTrue(2.B) // Any non-zero int should be true
        assertFalse((-1).B.not()) // Testing negation
    }
}
