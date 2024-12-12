import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ByteStringTest {

    @Test
    fun testCreationAndLength() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(3, bs.length)
    }

    @Test
    fun testIsEmpty() {
        val bsEmpty = ByteString(byteArrayOf())
        val bsNotEmpty = ByteString(byteArrayOf(65, 66, 67))
        assertTrue(bsEmpty.isEmpty())
        assertFalse(bsNotEmpty.isEmpty())
    }

    @Test
    fun testPlusByteString() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(68, 69, 70))
        val result = bs1 + bs2
        assertArrayEquals(byteArrayOf(65, 66, 67, 68, 69, 70), result.toByteArray())
    }

    @Test
    fun testPlusByte() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        val result = bs + 68
        assertArrayEquals(byteArrayOf(65, 66, 67, 68), result.toByteArray())
    }

    @Test
    fun testGet() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(66, bs[1])
    }

    @Test
    fun testCopy() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        val copy = bs.copy()
        assertArrayEquals(bs.toByteArray(), copy.toByteArray())
        assertNotSame(bs, copy)
    }

    @Test
    fun testCompareTo() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(65, 66, 67))
        val bs3 = ByteString(byteArrayOf(65, 66, 68))
        assertEquals(0, bs1.compareTo(bs2))
        assertTrue(bs1 < bs3)
        assertTrue(bs3 > bs1)
    }

    @Test
    fun testEquals() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(65, 66, 67))
        val bs3 = ByteString(byteArrayOf(65, 66, 68))
        assertTrue(bs1 == bs2)
        assertFalse(bs1 == bs3)
    }

    @Test
    fun testHashCode() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(bs1.hashCode(), bs2.hashCode())
    }

    @Test
    fun testSubstring() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 68, 69, 70))
        val sub1 = bs.substring(1)
        val sub2 = bs.substring(2, 5)
        assertArrayEquals(byteArrayOf(66, 67, 68, 69, 70), sub1.toByteArray())
        assertArrayEquals(byteArrayOf(67, 68, 69), sub2.toByteArray())
    }

    @Test
    fun testReplaceByte() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 66, 68))
        val result = bs.replace(66, 71)
        assertArrayEquals(byteArrayOf(65, 71, 67, 71, 68), result.toByteArray())
    }

    @Test
    fun testReplaceByteString() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 66, 68))
        val result = bs.replace("BC".bs, "XY".bs)
        assertArrayEquals(byteArrayOf(65, 88, 89, 66, 68), result.toByteArray())
    }

    @Test
    fun testToString() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals("ABC", bs.toString())
    }

    @Test
    fun testToByteString() {
        val str = "ABC"
        val bs = str.toByteString()
        assertArrayEquals(byteArrayOf(65, 66, 67), bs.toByteArray())
    }

    @Test
    fun testBsProperty() {
        val str = "ABC"
        val bs = str.bs
        assertArrayEquals(byteArrayOf(65, 66, 67), bs.toByteArray())
    }
}
