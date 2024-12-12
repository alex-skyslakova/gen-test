import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CusipTest {

    @Test
    void testValidCusips() {
        List<String> validCusips = List.of("037833100", "17275R102", "38259P508", "594918104", "68389X105", "87264ABE4");
        for (String cusip : validCusips) {
            assertTrue(Cusip.isCusip(cusip), cusip + " should be valid");
        }
    }

    @Test
    void testInvalidCusips() {
        List<String> invalidCusips = List.of("68389X106", "EXTRACRD8", "EXTRACRD9", "BADCUSIP!", "683&9X106", "68389x105", "683$9X106", "68389}105");
        for (String cusip : invalidCusips) {
            assertFalse(Cusip.isCusip(cusip), cusip + " should be invalid");
        }
    }

    @Test
    void testInvalidLength() {
        assertFalse(Cusip.isCusip("12345678"));
        assertFalse(Cusip.isCusip("1234567890"));
    }

    @Test
    void testSpecialCharacters() {
        assertTrue(Cusip.isCusip("123*@#456")); // Valid combination of special characters
        assertFalse(Cusip.isCusip("123%^&456")); // Invalid special character
    }


    @Test
    void testLowerCaseLetters(){
        assertFalse(Cusip.isCusip("68389x105"));
    }

}
