import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.security.MessageDigest

class BitcoinTest {

    private fun ByteArray.contentEquals(other: ByteArray): Boolean {
        if (this.size != other.size) return false
        return (0 until this.size).none { this[it] != other[it] }
    }

    private fun decodeBase58(input: String): ByteArray? {
        val output = ByteArray(25)
        for (c in input) {
            var p = Bitcoin.ALPHABET.indexOf(c)
            if (p == -1) return null
            for (j in 24 downTo 1) {
                p += 58 * (output[j].toInt() and 0xff)
                output[j] = (p % 256).toByte()
                p = p shr 8
            }
            if (p != 0) return null
        }
        return output
    }

    private fun sha256(data: ByteArray, start: Int, len: Int, recursion: Int): ByteArray {
        if (recursion == 0) return data
        val md = MessageDigest.getInstance("SHA-256")
        md.update(data.sliceArray(start until start + len))
        return sha256(md.digest(), 0, 32, recursion - 1)
    }


    @Test
    fun testValidAddress() {
        assertTrue(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"))
        assertTrue(Bitcoin.validateAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK9"))
    }

    @Test
    fun testInvalidAddress() {
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j"))
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62X"))
        assertFalse(Bitcoin.validateAddress("1ANNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"))
        assertFalse(Bitcoin.validateAddress("1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"))
        assertFalse(Bitcoin.validateAddress("BZbvjr"))
        assertFalse(Bitcoin.validateAddress("i55j"))
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!"))
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iz"))
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz"))
        assertFalse(Bitcoin.validateAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9"))
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I"))
    }


    @Test
    fun testDecodeBase58InvalidCharacters(){
        assertNull(decodeBase58("0"))
        assertNull(decodeBase58("O"))
        assertNull(decodeBase58("I"))
        assertNull(decodeBase58("l"))

    }

    @Test
    fun testAddressLength(){
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW6")) // Too short
        assertFalse(Bitcoin.validateAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iiiiiiiiii")) // Too long
    }


}
