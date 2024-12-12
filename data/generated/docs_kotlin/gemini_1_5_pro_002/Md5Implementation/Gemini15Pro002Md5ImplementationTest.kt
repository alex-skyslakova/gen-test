import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.*

class MD5Test {

    @Test
    fun testEmptyString() {
        val input = ""
        val expected = "d41d8cd98f00b204e9800998ecf8427e"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }

    @Test
    fun testSingleCharacter() {
        val input = "a"
        val expected = "0cc175b9c0f1b6a831c399e269772661"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }

    @Test
    fun testAbc() {
        val input = "abc"
        val expected = "900150983cd24fb0d6963f7d28e17f72"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }

    @Test
    fun testMessageDigest() {
        val input = "message digest"
        val expected = "f96b697d7cb7938d525a2f31aaf161d0"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }

    @Test
    fun testLowercaseAlphabet() {
        val input = "abcdefghijklmnopqrstuvwxyz"
        val expected = "c3fcd3d76192e4007dfb496cca67e13b"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }

    @Test
    fun testAlphaNumeric() {
        val input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val expected = "d174ab98d277d9f5a5611c2c9f419d9f"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }

    @Test
    fun testLongNumericString() {
        val input = "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
        val expected = "57edf4a22be3c955ac49da2e2107b67a"
        val result = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expected, result)
    }



}
