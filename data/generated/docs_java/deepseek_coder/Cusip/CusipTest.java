import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CusipTest {

    @Test
    public void testValidCusip() {
        assertTrue(Cusip.isCusip("037833100")); // Apple Incorporated
        assertTrue(Cusip.isCusip("17275R102")); // Cisco Systems
        assertTrue(Cusip.isCusip("38259P508")); // Google Incorporated
        assertTrue(Cusip.isCusip("594918104")); // Microsoft Corporation
        assertTrue(Cusip.isCusip("68389X105")); // Oracle Corporation (correct)
    }

    @Test
    public void testInvalidCusip() {
        assertFalse(Cusip.isCusip("68389X106")); // Oracle Corporation (incorrect)
        assertFalse(Cusip.isCusip("EXTRACRD8")); // Extra characters
        assertFalse(Cusip.isCusip("EXTRACRD9")); // Extra characters
        assertFalse(Cusip.isCusip("BADCUSIP!")); // Invalid character
        assertFalse(Cusip.isCusip("683&9X106")); // Invalid character
        assertFalse(Cusip.isCusip("68389x105")); // Lowercase letter
        assertFalse(Cusip.isCusip("683$9X106")); // Invalid character
        assertFalse(Cusip.isCusip("68389}105")); // Invalid character
        assertFalse(Cusip.isCusip("87264ABE4")); // Incorrect length
    }

    @Test
    public void testCusipLength() {
        assertFalse(Cusip.isCusip("12345678")); // Too short
        assertFalse(Cusip.isCusip("1234567890")); // Too long
    }

    @Test
    public void testCusipWithSpecialCharacters() {
        assertTrue(Cusip.isCusip("68389X105")); // Valid with uppercase letter
        assertFalse(Cusip.isCusip("68389x105")); // Invalid with lowercase letter
        assertTrue(Cusip.isCusip("68389#105")); // Valid with special character '#'
        assertTrue(Cusip.isCusip("68389@105")); // Valid with special character '@'
        assertTrue(Cusip.isCusip("68389*105")); // Valid with special character '*'
    }

    @Test
    public void testCusipWithDigits() {
        assertTrue(Cusip.isCusip("037833100")); // Valid with digits
        assertFalse(Cusip.isCusip("037833101")); // Invalid check digit
    }

    @Test
    public void testCusipWithLetters() {
        assertTrue(Cusip.isCusip("17275R102")); // Valid with letters
        assertFalse(Cusip.isCusip("17275R103")); // Invalid check digit
    }

    @Test
    public void testCusipWithMixedCharacters() {
        assertTrue(Cusip.isCusip("38259P508")); // Valid with mixed characters
        assertFalse(Cusip.isCusip("38259P509")); // Invalid check digit
    }
}
