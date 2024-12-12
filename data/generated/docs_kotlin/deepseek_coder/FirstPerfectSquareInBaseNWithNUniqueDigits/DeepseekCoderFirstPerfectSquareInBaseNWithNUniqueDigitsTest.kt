import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FirstPerfectSquareInBaseNWithNUniqueDigitsTest {

    @BeforeEach
    fun setUp() {
        // Reset global variables before each test
        base = 0
        bmo = 0
        blim = 0
        ic = 0
        st0 = 0
        bllim = null
        threshold = null
        hs.clear()
        o.clear()
        limits = null
        ms = null
    }

    @Test
    fun testIndexOf() {
        assertEquals(0, indexOf('0'))
        assertEquals(10, indexOf('A'))
        assertEquals(63, indexOf('|'))
        assertEquals(-1, indexOf('z')) // 'z' is not in the ALPHABET
    }

    @Test
    fun testToStr() {
        base = 10
        assertEquals("123", toStr(BigInteger.valueOf(123)))
        base = 16
        assertEquals("7B", toStr(BigInteger.valueOf(123)))
    }

    @Test
    fun testAllInQS() {
        base = 10
        ic = 5
        o.addAll(listOf(0, 1, 2, 3, 4).map { it.toByte() })
        assertTrue(allInQS(BigInteger.valueOf(12345)))
        assertFalse(allInQS(BigInteger.valueOf(123456)))
    }

    @Test
    fun testAllInS() {
        base = 10
        o.addAll(listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).map { it.toByte() })
        assertTrue(allInS(BigInteger.valueOf(1234567890)))
        assertFalse(allInS(BigInteger.valueOf(123456789)))
    }

    @Test
    fun testAllInQ() {
        base = 10
        assertTrue(allInQ(BigInteger.valueOf(1234567890)))
        assertFalse(allInQ(BigInteger.valueOf(123456789)))
    }

    @Test
    fun testAllIn() {
        base = 10
        assertTrue(allIn(BigInteger.valueOf(1234567890)))
        assertFalse(allIn(BigInteger.valueOf(123456789)))
    }

    @Test
    fun testTo10() {
        base = 10
        assertEquals(BigInteger.valueOf(123), to10("123"))
        base = 16
        assertEquals(BigInteger.valueOf(255), to10("FF"))
    }

    @Test
    fun testFixup() {
        base = 10
        assertEquals("1023456789", fixup(0))
        base = 16
        assertEquals("1023456789ABCDEF", fixup(0))
    }

    @Test
    fun testCheck() {
        base = 10
        bmo = 9
        blim = 0
        ic = 1
        ms = "1023456789"
        limits = mutableListOf(BigInteger.valueOf(1000000000))
        threshold = limits!![0]
        bllim = to10(ms!!.substring(0, blim + 1))
        check(BigInteger.valueOf(1234567890))
        assertEquals(0, blim)
        assertEquals(1, ic)
    }

    @Test
    fun testDoOne() {
        base = 10
        doOne()
        // Assert the output or side effects of doOne() if possible
    }

    @Test
    fun testFormat() {
        val duration = Duration.ofMillis(123456)
        assertEquals("02:03.456", format(duration))
    }
}
