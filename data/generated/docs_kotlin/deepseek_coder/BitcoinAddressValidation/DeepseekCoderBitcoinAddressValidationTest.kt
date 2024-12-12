import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BitcoinAddressValidationTest {

    @Test
    fun testValidBitcoinAddress() {
        val validAddress = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"
        assertTrue(Bitcoin.validateAddress(validAddress))
    }

    @Test
    fun testInvalidBitcoinAddress() {
        val invalidAddress = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j"
        assertFalse(Bitcoin.validateAddress(invalidAddress))
    }

    @Test
    fun testShortBitcoinAddress() {
        val shortAddress = "1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK"
        assertFalse(Bitcoin.validateAddress(shortAddress))
    }

    @Test
    fun testLongBitcoinAddress() {
        val longAddress = "1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK99"
        assertFalse(Bitcoin.validateAddress(longAddress))
    }

    @Test
    fun testInvalidCharacterBitcoinAddress() {
        val invalidCharacterAddress = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!"
        assertFalse(Bitcoin.validateAddress(invalidCharacterAddress))
    }

    @Test
    fun testInvalidChecksumBitcoinAddress() {
        val invalidChecksumAddress = "1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9"
        assertFalse(Bitcoin.validateAddress(invalidChecksumAddress))
    }

    @Test
    fun testInvalidVersionBitcoinAddress() {
        val invalidVersionAddress = "1ANNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"
        assertFalse(Bitcoin.validateAddress(invalidVersionAddress))
    }

    @Test
    fun testInvalidBase58CharacterBitcoinAddress() {
        val invalidBase58CharacterAddress = "1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"
        assertFalse(Bitcoin.validateAddress(invalidBase58CharacterAddress))
    }

    @Test
    fun testShortBase58BitcoinAddress() {
        val shortBase58Address = "BZbvjr"
        assertFalse(Bitcoin.validateAddress(shortBase58Address))
    }

    @Test
    fun testVeryShortBase58BitcoinAddress() {
        val veryShortBase58Address = "i55j"
        assertFalse(Bitcoin.validateAddress(veryShortBase58Address))
    }

    @Test
    fun testInvalidLengthBitcoinAddress() {
        val invalidLengthAddress = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz"
        assertFalse(Bitcoin.validateAddress(invalidLengthAddress))
    }

    @Test
    fun testInvalidCharacterUppercaseIBitcoinAddress() {
        val invalidCharacterUppercaseIAddress = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I"
        assertFalse(Bitcoin.validateAddress(invalidCharacterUppercaseIAddress))
    }
}
