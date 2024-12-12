import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class AbbreviationsTest {

    private static String commandTable =
        "add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3 " +
        "compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate " +
        "3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2 " +
        "forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load " +
        "locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2 " +
        "msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3 " +
        "refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left " +
        "2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1";

    @Test
    public void testValidAbbreviations() {
        CommandList commands = new CommandList(commandTable);
        assertEquals("RIGHT", commands.findCommand("riG").cmd);
        assertEquals("REPEAT", commands.findCommand("rePEAT").cmd);
        assertEquals("PUT", commands.findCommand("put").cmd);
        assertEquals("MOVE", commands.findCommand("mo").cmd);
        assertEquals("RESTORE", commands.findCommand("rest").cmd);
        assertEquals("POWERINPUT", commands.findCommand("poweRin").cmd);
    }

    @Test
    public void testInvalidAbbreviations() {
        CommandList commands = new CommandList(commandTable);
        assertNull(commands.findCommand("copies"));
        assertNull(commands.findCommand("types"));
        assertNull(commands.findCommand("fup."));
        assertNull(commands.findCommand("6"));
    }

    @Test
    public void testMixedCaseAbbreviations() {
        CommandList commands = new CommandList(commandTable);
        assertEquals("RIGHT", commands.findCommand("RiG").cmd);
        assertEquals("REPEAT", commands.findCommand("rEpEaT").cmd);
        assertEquals("PUT", commands.findCommand("PuT").cmd);
        assertEquals("MOVE", commands.findCommand("Mo").cmd);
        assertEquals("RESTORE", commands.findCommand("ReSt").cmd);
        assertEquals("POWERINPUT", commands.findCommand("PoWeRiN").cmd);
    }

    @Test
    public void testExactMatch() {
        CommandList commands = new CommandList(commandTable);
        assertEquals("ADD", commands.findCommand("add").cmd);
        assertEquals("ALTER", commands.findCommand("alter").cmd);
        assertEquals("BACKUP", commands.findCommand("backup").cmd);
        assertEquals("BOTTOM", commands.findCommand("bottom").cmd);
        assertEquals("CAPPEND", commands.findCommand("Cappend").cmd);
    }

    @Test
    public void testMinLengthAbbreviations() {
        CommandList commands = new CommandList(commandTable);
        assertEquals("ALTER", commands.findCommand("alt").cmd);
        assertEquals("ALTER", commands.findCommand("alte").cmd);
        assertEquals("ALTER", commands.findCommand("alter").cmd);
        assertNull(commands.findCommand("al"));
        assertNull(commands.findCommand("alf"));
        assertNull(commands.findCommand("alters"));
    }

    @Test
    public void testNoAbbreviationAllowed() {
        CommandList commands = new CommandList(commandTable);
        assertNull(commands.findCommand("get"));
        assertNull(commands.findCommand("help"));
        assertNull(commands.findCommand("quit"));
    }

    @Test
    public void testBlankInput() {
        CommandList commands = new CommandList(commandTable);
        assertNull(commands.findCommand(""));
        assertNull(commands.findCommand(null));
    }

    @Test
    public void testFullExample() {
        CommandList commands = new CommandList(commandTable);
        String input = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin";
        String expectedOutput = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT";
        assertEquals(expectedOutput, test(commands, input));
    }

    private static String test(CommandList commands, String input) {
        StringBuilder output = new StringBuilder();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (output.length() > 0)
                output.append(' ');
            Command cmd = commands.findCommand(word);
            if (cmd != null)
                output.append(cmd.cmd);
            else
                output.append("*error*");
        }
        return output.toString();
    }

    private static class Command {
        private Command(String cmd, int minLength) {
             this.cmd = cmd;
             this.minLength = minLength;
        }
        private boolean match(String str) {
            int olen = str.length();
            return olen >= minLength && olen <= cmd.length()
                && cmd.regionMatches(true, 0, str, 0, olen);
        }
        private String cmd;
        private int minLength;
    }

    private static Integer parseInteger(String word) {
        try {
            return Integer.valueOf(word);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private static class CommandList {
        private CommandList(String table) {
            Scanner scanner = new Scanner(table);
            List<String> words = new ArrayList<>();
            while (scanner.hasNext()) {
                String word = scanner.next();
                words.add(word.toUpperCase());
            }
            for (int i = 0, n = words.size(); i < n; ++i) {
                String word = words.get(i);
                // if there's an integer following this word, it specifies the minimum
                // length for the command, otherwise the minimum length is the length
                // of the command string
                int len = word.length();
                if (i + 1 < n) {
                    Integer number = parseInteger(words.get(i + 1));
                    if (number != null) {
                        len = number.intValue();
                        ++i;
                    }
                }
                commands.add(new Command(word, len));
            }
        }
        private Command findCommand(String word) {
            if (word == null) return null;
            for (Command command : commands) {
                if (command.match(word))
                    return command;
            }
            return null;
        }
        private List<Command> commands = new ArrayList<>();
    }
}
