import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitcoinAddressValidatorTest {

    @Test
    void testValidAddress() {
        assertTrue(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertTrue(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK9"));
    }

    @Test
    void testInvalidAddressChecksum() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62X"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9"));
    }

    @Test
    void testInvalidAddressInvalidCharacter() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1ANNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i")); // 'N' instead of 'G'
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i")); // Space
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!")); // '!'
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I")); // 'I'
    }


    @Test
    void testInvalidAddressLength() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("BZbvjr")); // Too short
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("i55j")); // Too short
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iz")); // Too long
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz")); // Too long
    }

    @Test
    void testDecodeBase58InvalidInput() {
        assertNull(BitcoinAddressValidator.decodeBase58To25Bytes("!nvalid")); // Contains invalid character '!'

    }


}
