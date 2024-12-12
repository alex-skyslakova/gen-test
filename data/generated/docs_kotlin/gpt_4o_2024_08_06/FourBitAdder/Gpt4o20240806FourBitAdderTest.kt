import kotlin.test.Test
import kotlin.test.assertEquals

class FourBitAdderTest {

    @Test
    fun testNybbleToInt() {
        assertEquals(0, Nybble(false, false, false, false).toInt())
        assertEquals(1, Nybble(false, false, false, true).toInt())
        assertEquals(2, Nybble(false, false, true, false).toInt())
        assertEquals(4, Nybble(false, true, false, false).toInt())
        assertEquals(8, Nybble(true, false, false, false).toInt())
        assertEquals(15, Nybble(true, true, true, true).toInt())
    }

    @Test
    fun testIntToNybble() {
        assertEquals(Nybble(false, false, false, false), 0.toNybble())
        assertEquals(Nybble(false, false, false, true), 1.toNybble())
        assertEquals(Nybble(false, false, true, false), 2.toNybble())
        assertEquals(Nybble(false, true, false, false), 4.toNybble())
        assertEquals(Nybble(true, false, false, false), 8.toNybble())
        assertEquals(Nybble(true, true, true, true), 15.toNybble())
    }

    @Test
    fun testXorGate() {
        assertEquals(false, xorGate(false, false))
        assertEquals(true, xorGate(false, true))
        assertEquals(true, xorGate(true, false))
        assertEquals(false, xorGate(true, true))
    }

    @Test
    fun testHalfAdder() {
        assertEquals(Pair(false, false), halfAdder(false, false))
        assertEquals(Pair(true, false), halfAdder(false, true))
        assertEquals(Pair(true, false), halfAdder(true, false))
        assertEquals(Pair(false, true), halfAdder(true, true))
    }

    @Test
    fun testFullAdder() {
        assertEquals(Pair(false, false), fullAdder(false, false, false))
        assertEquals(Pair(true, false), fullAdder(false, false, true))
        assertEquals(Pair(true, false), fullAdder(false, true, false))
        assertEquals(Pair(false, true), fullAdder(false, true, true))
        assertEquals(Pair(true, false), fullAdder(true, false, false))
        assertEquals(Pair(false, true), fullAdder(true, false, true))
        assertEquals(Pair(false, true), fullAdder(true, true, false))
        assertEquals(Pair(true, true), fullAdder(true, true, true))
    }

    @Test
    fun testFourBitAdder() {
        assertEquals(Pair(Nybble(false, false, false, false), 0), fourBitAdder(0.toNybble(), 0.toNybble()))
        assertEquals(Pair(Nybble(false, false, false, true), 0), fourBitAdder(0.toNybble(), 1.toNybble()))
        assertEquals(Pair(Nybble(false, false, true, false), 0), fourBitAdder(1.toNybble(), 1.toNybble()))
        assertEquals(Pair(Nybble(true, true, true, true), 0), fourBitAdder(7.toNybble(), 8.toNybble()))
        assertEquals(Pair(Nybble(false, false, false, false), 1), fourBitAdder(15.toNybble(), 1.toNybble()))
    }
}
