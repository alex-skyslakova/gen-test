import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbbreviationsEasyTest {

    @Test
    public void testValidAbbreviations() {
        String[] userInput = {"riG", "rePEAT", "copies", "put", "mo", "rest", "types", "fup.", "6", "poweRin"};
        String[] expectedOutput = {"RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT"};

        for (int i = 0; i < userInput.length; i++) {
            String result = validateAbbreviation(userInput[i]);
            assertEquals(expectedOutput[i], result);
        }
    }

    @Test
    public void testInvalidAbbreviations() {
        String[] userInput = {"AL", "ALF", "ALTERS", "TER", "A"};
        String[] expectedOutput = {"*error*", "*error*", "*error*", "*error*", "*error*"};

        for (int i = 0; i < userInput.length; i++) {
            String result = validateAbbreviation(userInput[i]);
            assertEquals(expectedOutput[i], result);
        }
    }

    @Test
    public void testMixedCaseAbbreviations() {
        String[] userInput = {"o", "ov", "oVe", "over", "overL", "overla"};
        String[] expectedOutput = {"OVERLAY", "OVERLAY", "OVERLAY", "OVERLAY", "OVERLAY", "OVERLAY"};

        for (int i = 0; i < userInput.length; i++) {
            String result = validateAbbreviation(userInput[i]);
            assertEquals(expectedOutput[i], result);
        }
    }

    @Test
    public void testBlankInput() {
        String result = validateAbbreviation("");
        assertEquals("", result);
    }

    @Test
    public void testNullInput() {
        String result = validateAbbreviation(null);
        assertEquals("", result);
    }

    private String validateAbbreviation(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String[] cmdTableArr = AbbreviationsEasy.COMMAND_TABLE.split("\\s+");
        for (String cmd : cmdTableArr) {
            int minLength = AbbreviationsEasy.countCaps(cmd);
            if (input.length() >= minLength && input.length() <= cmd.length()) {
                if (cmd.toUpperCase().startsWith(input.toUpperCase())) {
                    return cmd.toUpperCase();
                }
            }
        }
        return "*error*";
    }
}
