import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class ByteStringTest {

    @Test
    fun testLength() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(3, bs.length)
    }

    @Test
    fun testIsEmpty() {
        val bs = ByteString(byteArrayOf())
        assertTrue(bs.isEmpty())
        val bs2 = ByteString(byteArrayOf(1))
        assertFalse(bs2.isEmpty())
    }

    @Test
    fun testPlusByteString() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = ByteString(byteArrayOf(68, 69, 70))
        val bs3 = bs + bs2
        assertEquals(ByteString(byteArrayOf(65, 66, 67, 68, 69, 70)), bs3)
    }

    @Test
    fun testPlusByte() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        val bs2 = bs + 68.toByte()
        assertEquals(ByteString(byteArrayOf(65, 66, 67, 68)), bs2)
    }

    @Test
    fun testGet() {
        val bs = ByteString(byteArrayOf(65, 66, 67))
        assertEquals(65.toByte(), bs[0])
        assertEquals(66.toByte(), bs[1])
        assertEquals(67.toByte(), bs[2])
        assertThrows<IllegalArgumentException> { bs[3] }
        assertThrows<IllegalArgumentException> { bs[-1] }

    }


    @Test
    fun testToByteArray() {
        val ba = byteArrayOf(65, 66, 67)
        val bs = ByteString(ba)
        assertTrue(ba.contentEquals(bs.toByteArray()))
    }

    @Test
    fun testCopy() {
        val ba = byteArrayOf(65, 66, 67)
        val bs = ByteString(ba)
        val bsCopy = bs.copy()
        assertEquals(bs, bsCopy)
        assertNotEquals(ba, bsCopy.toByteArray()) // Ensure it's a deep copy
    }


    @Test
    fun testCompareTo() {
        val bs1 = "ABC".toByteString()
        val bs2 = "ABD".toByteString()
        val bs3 = "ABB".toByteString()
        assertTrue(bs1 < bs2)
        assertTrue(bs2 > bs1)
        assertTrue(bs1 > bs3)
        assertTrue(bs3 < bs1)
        assertEquals(0, bs1.compareTo(bs1.copy()))
    }

    @Test
    fun testEquals() {
        val bs1 = "ABC".toByteString()
        val bs2 = "ABC".toByteString()
        val bs3 = "ABD".toByteString()
        assertEquals(bs1, bs2)
        assertNotEquals(bs1, bs3)
        assertNotEquals(bs1, null)
        assertNotEquals(bs1,"ABC")

    }



    @Test
    fun testSubstring() {
        val bs = "ABCDEF".toByteString()
        assertEquals("BCDEF".toByteString(), bs.substring(1))
        assertEquals("CDE".toByteString(), bs.substring(2, 5))
    }

    @Test
    fun testReplaceByte() {
        val bs = "ABCDEF".toByteString()
        assertEquals("ABGDEF".toByteString(), bs.replace(67.toByte(), 71.toByte()))
    }

    @Test
    fun testReplaceByteString() {
        val bs = "ABCDEF".toByteString()
        assertEquals("ABGHF".toByteString(), bs.replace("CD".bs, "GH".bs))

    }


    @Test
    fun testToString() {
        val bs = ByteString(byteArrayOf(65, 66, 67, -128, -127 )) // Test extended ASCII
        assertEquals("ABC\u0080\u0081", bs.toString())
    }

    @Test
    fun testToByteString() {
        val str = "ABC\u0080\u0081"
        val bs = str.toByteString()
        assertEquals(ByteString(byteArrayOf(65, 66, 67, -128, -127 )), bs)
        assertEquals("?est".toByteString(), "þest".toByteString()) // Testing the '?' replacement
    }

    @Test
    fun testBsProperty(){
        assertEquals("ABC".toByteString(), "ABC".bs)
    }


}
