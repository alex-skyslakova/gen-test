import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CusipTest {

    @Test
    void testValidCusips() {
        assertTrue(Cusip.isCusip("037833100"), "Apple Incorporated CUSIP should be valid");
        assertTrue(Cusip.isCusip("17275R102"), "Cisco Systems CUSIP should be valid");
        assertTrue(Cusip.isCusip("38259P508"), "Google Incorporated CUSIP should be valid");
        assertTrue(Cusip.isCusip("594918104"), "Microsoft Corporation CUSIP should be valid");
        assertTrue(Cusip.isCusip("68389X105"), "Oracle Corporation CUSIP should be valid");
    }

    @Test
    void testInvalidCusips() {
        assertFalse(Cusip.isCusip("68389X106"), "Oracle Corporation CUSIP should be invalid");
        assertFalse(Cusip.isCusip("EXTRACRD8"), "EXTRACRD8 should be invalid");
        assertFalse(Cusip.isCusip("EXTRACRD9"), "EXTRACRD9 should be invalid");
        assertFalse(Cusip.isCusip("BADCUSIP!"), "BADCUSIP! should be invalid");
        assertFalse(Cusip.isCusip("683&9X106"), "683&9X106 should be invalid");
        assertFalse(Cusip.isCusip("68389x105"), "68389x105 should be invalid (lowercase letter)");
        assertFalse(Cusip.isCusip("683$9X106"), "683$9X106 should be invalid");
        assertFalse(Cusip.isCusip("68389}105"), "68389}105 should be invalid");
        assertFalse(Cusip.isCusip("87264ABE4"), "87264ABE4 should be invalid");
    }

    @Test
    void testCusipLength() {
        assertFalse(Cusip.isCusip("12345678"), "CUSIP with less than 9 characters should be invalid");
        assertFalse(Cusip.isCusip("1234567890"), "CUSIP with more than 9 characters should be invalid");
    }

    @Test
    void testCusipWithSpecialCharacters() {
        assertFalse(Cusip.isCusip("12345678*"), "CUSIP with special character at the end should be invalid");
        assertFalse(Cusip.isCusip("1234567@0"), "CUSIP with special character in the middle should be invalid");
        assertFalse(Cusip.isCusip("123456#00"), "CUSIP with special character at the beginning should be invalid");
    }
}
