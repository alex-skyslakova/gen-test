import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger

class FirstPerfectSquareInBaseNWithNUniqueDigitsTest {

    @Test
    fun testIndexOf() {
        assertEquals(0, indexOf('0'))
        assertEquals(9, indexOf('9'))
        assertEquals(10, indexOf('A'))
        assertEquals(35, indexOf('Z'))
        assertEquals(61, indexOf('z'))
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
        bllim = BigInteger.ZERO
        o.clear()
        assertTrue(allInQS(BigInteger.valueOf(1234567890)))
        assertFalse(allInQS(BigInteger.valueOf(123456789)))
    }

    @Test
    fun testAllInS() {
        base = 10
        bllim = BigInteger.ZERO
        o.clear()
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
        assertEquals(BigInteger.valueOf(291), to10("123"))
    }

    @Test
    fun testFixup() {
        base = 10
        assertEquals("1023456789", fixup(0))
        assertEquals("1023456789", fixup(1))
    }

    @Test
    fun testCheck() {
        base = 10
        ms = "1023456789"
        blim = 9
        threshold = BigInteger.valueOf(10000000000)
        limits = mutableListOf(BigInteger.valueOf(10000000000))
        check(BigInteger.valueOf(10000000001))
        assertEquals(8, blim)
    }

    @Test
    fun testDoOne() {
        base = 10
        doOne()
        // This test is more of an integration test to ensure no exceptions are thrown
        // and the function completes. Specific assertions would require known expected outputs.
    }

    @Test
    fun testMain() {
        // This test ensures that the main function runs without exceptions.
        // Specific assertions would require capturing the output and verifying it.
        main()
    }
}
