import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BitcoinAddressValidationTest {

    @Test
    fun `valid address`() {
        val address = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"
        assertTrue(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with incorrect checksum`() {
        val address = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `valid address with different valid checksum`() {
        val address = "1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK9"
        assertTrue(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with changed character`() {
        val address = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62X"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with invalid character`() {
        val address = "1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with too short length`() {
        val address = "BZbvjr"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with too short length and invalid characters`() {
        val address = "i55j"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with special character`() {
        val address = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with too long length`() {
        val address = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz"
        assertFalse(Bitcoin.validateAddress(address))
    }

    @Test
    fun `invalid address with similar looking character`() {
        val address = "1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I"
        assertFalse(Bitcoin.validateAddress(address))
    }
}
