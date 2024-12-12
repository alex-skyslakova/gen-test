import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BitcoinAddressValidatorTest {

    @Test
    public void testValidBitcoinAddress() {
        assertTrue(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertTrue(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nK9"));
    }

    @Test
    public void testInvalidBitcoinAddress() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62X"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1ANNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1A Na15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("BZbvjr"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("i55j"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62!"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62iz"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62izz"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9"));
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I"));
    }

    @Test
    public void testInvalidLength() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62")); // 1 character short
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62i12345")); // 5 characters long
    }

    @Test
    public void testInvalidCharacters() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW620")); // '0' is invalid
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62O")); // 'O' is invalid
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62I")); // 'I' is invalid
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62l")); // 'l' is invalid
    }

    @Test
    public void testInvalidChecksum() {
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1AGNa15ZQXAZUgFiqJ2i7Z2DPU2J6hW62j")); // Changed last character
        assertFalse(BitcoinAddressValidator.validateBitcoinAddress("1Q1pE5vPGEEMqRcVRMbtBK842Y6Pzo6nJ9")); // Changed last character
    }
}
