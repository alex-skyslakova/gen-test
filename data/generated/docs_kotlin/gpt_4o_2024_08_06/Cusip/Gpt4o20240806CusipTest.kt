import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class CusipTest {

    @Test
    fun testValidCusips() {
        // Valid CUSIPs
        assertTrue(isCusip("037833100"), "Apple Incorporated CUSIP should be valid")
        assertTrue(isCusip("17275R102"), "Cisco Systems CUSIP should be valid")
        assertTrue(isCusip("38259P508"), "Google Incorporated CUSIP should be valid")
        assertTrue(isCusip("594918104"), "Microsoft Corporation CUSIP should be valid")
        assertTrue(isCusip("68389X105"), "Oracle Corporation CUSIP should be valid")
    }

    @Test
    fun testInvalidCusips() {
        // Invalid CUSIPs
        assertFalse(isCusip("68389X106"), "Oracle Corporation CUSIP should be invalid")
    }

    @Test
    fun testInvalidLengthCusip() {
        // CUSIPs with incorrect length
        assertFalse(isCusip("12345678"), "CUSIP with 8 characters should be invalid")
        assertFalse(isCusip("1234567890"), "CUSIP with 10 characters should be invalid")
    }

    @Test
    fun testInvalidCharactersCusip() {
        // CUSIPs with invalid characters
        assertFalse(isCusip("12345678!"), "CUSIP with invalid character should be invalid")
        assertFalse(isCusip("ABCDEFGH1"), "CUSIP with all letters should be invalid")
    }
}
