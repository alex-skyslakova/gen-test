import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

class FirstPerfectSquareInBaseNWithNUniqueDigitsTest {

    @Test
    fun testIndexOf() {
        assertEquals(0, indexOf('0'))
        assertEquals(1, indexOf('1'))
        assertEquals(10, indexOf('A'))
        assertEquals(16, indexOf('G'))
        assertEquals(-1, indexOf('$'))
    }

    @Test
    fun testToStr() {
        base = 10
        assertEquals("123", toStr(BigInteger.valueOf(123)))
        base = 2
        assertEquals("1111011", toStr(BigInteger.valueOf(123)))
        base = 16
        assertEquals("7B", toStr(BigInteger.valueOf(123)))
    }


    @Test
    fun testTo10() {
        base = 10
        assertEquals(BigInteger.valueOf(123), to10("123"))
        base = 2
        assertEquals(BigInteger.valueOf(123), to10("1111011"))
        base = 16
        assertEquals(BigInteger.valueOf(123), to10("7B"))
    }

    @Test
    fun testFixup() {
        base = 10
        assertEquals("1023456789", fixup(0))
        assertEquals("11023456789", fixup(1))
        base = 2
        assertEquals("101", fixup(0))
    }

    @Test
    fun testAllIn() {
        base = 10
        assertTrue(allIn(BigInteger.valueOf(1023456789)))
        assertFalse(allIn(BigInteger.valueOf(1023456788)))
        base = 2
        assertTrue(allIn(BigInteger.valueOf(3)))  // 11 in base 2
        assertFalse(allIn(BigInteger.valueOf(2))) // 10 in base 2
    }


    @Test
    fun testAllInQ() {
        base = 10
        assertTrue(allInQ(BigInteger.valueOf(1023456789)))
        assertFalse(allInQ(BigInteger.valueOf(1023456788)))
        base = 2
        assertTrue(allInQ(BigInteger.valueOf(3)))  // 11 in base 2
        assertFalse(allInQ(BigInteger.valueOf(2))) // 10 in base 2

    }

        @Test
    fun testAllInS() {
        base = 10
        ms = "1023456789"
        blim = 0
        bllim = BigInteger.ZERO
        assertTrue(allInS(BigInteger.valueOf(1023456789)))
        assertFalse(allInS(BigInteger.valueOf(1023456788)))
    }

    @Test
    fun testAllInQS() {
        base = 10
        ms = "1023456789"
        blim = 0
        ic = 1
        bllim = BigInteger.ZERO
        o.clear()
        o.add(1)
        o.add(0)
        assertTrue(allInQS(BigInteger.valueOf(1023456789)))
        assertFalse(allInQS(BigInteger.valueOf(1023456788)))
    }


    // Note: doOne and main are integration tests and not suitable for unit testing. They involve system output and complex interactions that are difficult to isolate and verify.
}



