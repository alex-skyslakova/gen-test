import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbbreviationsTest {

    @Test
    void testValidAbbreviations() {
        CommandList commands = new CommandList(Abbreviations.commandTable);
        
        assertEquals("RIGHT", commands.findCommand("riG").cmd);
        assertEquals("REPEAT", commands.findCommand("rePEAT").cmd);
        assertEquals("PUT", commands.findCommand("put").cmd);
        assertEquals("MOVE", commands.findCommand("mo").cmd);
        assertEquals("RESTORE", commands.findCommand("rest").cmd);
        assertEquals("POWERINPUT", commands.findCommand("poweRin").cmd);
    }

    @Test
    void testInvalidAbbreviations() {
        CommandList commands = new CommandList(Abbreviations.commandTable);
        
        assertNull(commands.findCommand("copies"));
        assertNull(commands.findCommand("types"));
        assertNull(commands.findCommand("fup."));
        assertNull(commands.findCommand("6"));
    }

    @Test
    void testEmptyOrNullInput() {
        CommandList commands = new CommandList(Abbreviations.commandTable);
        
        assertEquals("", Abbreviations.test(commands, ""));
        assertEquals("", Abbreviations.test(commands, null));
    }

    @Test
    void testMixedInput() {
        CommandList commands = new CommandList(Abbreviations.commandTable);
        String input = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin";
        String expectedOutput = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT";
        
        assertEquals(expectedOutput, Abbreviations.test(commands, input));
    }
}
