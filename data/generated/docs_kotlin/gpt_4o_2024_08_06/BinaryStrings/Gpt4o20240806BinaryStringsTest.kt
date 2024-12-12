import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ByteStringTest {

    @Test
    fun testLength() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(3, bs.length)
    }

    @Test
    fun testIsEmpty() {
        val emptyBs = ByteString(byteArrayOf())
        val nonEmptyBs = ByteString(byteArrayOf(65))
        assertTrue(emptyBs.isEmpty())
        assertFalse(nonEmptyBs.isEmpty())
    }

    @Test
    fun testPlusByteString() {
        val bs1 = ByteString(byteArrayOf(65, 66))
        val bs2 = ByteString(byteArrayOf(67, 68))
        val result = bs1 + bs2
        assertEquals(ByteString(byteArrayOf(65, 66, 67, 68)), result)
    }

    @Test
    fun testPlusByte() {
        val bs = ByteString(byteArrayOf(65, 66))
        val result = bs + 67.toByte()
        assertEquals(ByteString(byteArrayOf(65, 66, 67)), result)
    }

    @Test
    fun testGet() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(65.toByte(), bs[0])
        assertEquals(66.toByte(), bs[1])
        assertEquals(67.toByte(), bs[2])
    }

    @Test
    fun testToByteArray() {
        val bytes = byteArrayOf(65, 66, 67)
        val bs = ByteString(bytes)
        assertArrayEquals(bytes, bs.toByteArray())
    }

    @Test
    fun testCopy() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        val copy = bs.copy()
        assertEquals(bs, copy)
        assertNotSame(bs, copy)
    }

    @Test
    fun testCompareTo() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(65, 66, 68))
        assertTrue(bs1 < bs2)
        assertTrue(bs2 > bs1)
        assertEquals(0, bs1.compareTo(bs1.copy()))
    }

    @Test
    fun testEquals() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(65, 66, 67))
        val bs3 = ByteString(byteArrayOf(65, 66, 68))
        assertEquals(bs1, bs2)
        assertNotEquals(bs1, bs3)
    }

    @Test
    fun testHashCode() {
        val bs1 = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(bs1.hashCode(), bs2.hashCode())
    }

    @Test
    fun testSubstringSingleIndex() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 68))
        val sub = bs.substring(2)
        assertEquals(ByteString(byteArrayOf(67, 68)), sub)
    }

    @Test
    fun testSubstringRange() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 68))
        val sub = bs.substring(1, 3)
        assertEquals(ByteString(byteArrayOf(66, 67)), sub)
    }

    @Test
    fun testReplaceByte() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 68))
        val replaced = bs.replace(66.toByte(), 70.toByte())
        assertEquals(ByteString(byteArrayOf(65, 70, 67, 68)), replaced)
    }

    @Test
    fun testReplaceByteString() {
        val bs = ByteString(byteArrayOf(65, 66, 67, 68))
        val oldValue = ByteString(byteArrayOf(66, 67))
        val newValue = ByteString(byteArrayOf(70, 71))
        val replaced = bs.replace(oldValue, newValue)
        assertEquals(ByteString(byteArrayOf(65, 70, 71, 68)), replaced)
    }

    @Test
    fun testToString() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals("ABC", bs.toString())
    }

    @Test
    fun testStringToByteString() {
        val str = "ABC"
        val bs = str.toByteString()
        assertEquals(ByteString(byteArrayOf(65, 66, 67)), bs)
    }

    @Test
    fun testStringBsProperty() {
        val str = "ABC"
        val bs = str.bs
        assertEquals(ByteString(byteArrayOf(65, 66, 67)), bs)
    }
}
