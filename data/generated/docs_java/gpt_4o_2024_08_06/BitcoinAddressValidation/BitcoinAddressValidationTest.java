import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BitcoinAddressValidatorTest {

    @Test
    void testValidBitcoinAddress() {
        assertTrue(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertTrue(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK9"));
    }

    @Test
    void testInvalidBitcoinAddressChecksum() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62X"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9"));
    }

    @Test
    void testInvalidBitcoinAddressCharacters() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1ANNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I"));
    }

    @Test
    void testInvalidBitcoinAddressLength() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("BZbvjr"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("i55j"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iz"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz"));
    }
}
