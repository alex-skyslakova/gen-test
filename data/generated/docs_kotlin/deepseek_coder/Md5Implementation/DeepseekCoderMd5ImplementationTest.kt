import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class MD5Test {

    @Test
    fun testEmptyString() {
        val input = ""
        val expectedHash = "d41d8cd98f00b204e9800998ecf8427e"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }

    @Test
    fun testSingleCharacter() {
        val input = "a"
        val expectedHash = "0cc175b9c0f1b6a831c399e269772661"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }

    @Test
    fun testThreeCharacters() {
        val input = "abc"
        val expectedHash = "900150983cd24fb0d6963f7d28e17f72"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }

    @Test
    fun testMessageDigest() {
        val input = "message digest"
        val expectedHash = "f96b697d7cb7938d525a2f31aaf161d0"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }

    @Test
    fun testAlphabet() {
        val input = "abcdefghijklmnopqrstuvwxyz"
        val expectedHash = "c3fcd3d76192e4007dfb496cca67e13b"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }

    @Test
    fun testAlphabetAndNumbers() {
        val input = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val expectedHash = "d174ab98d277d9f5a5611c2c9f419d9f"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }

    @Test
    fun testLongString() {
        val input = "12345678901234567890123456789012345678901234567890123456789012345678901234567890"
        val expectedHash = "57edf4a22be3c955ac49da2e2107b67a"
        val actualHash = MD5.compute(input.toByteArray()).toHexString()
        assertEquals(expectedHash, actualHash)
    }
}
