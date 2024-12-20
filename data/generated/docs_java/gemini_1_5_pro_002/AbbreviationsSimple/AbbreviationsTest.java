import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    private CommandList commands = new CommandList(commandTable);


    @Test
    void testProvidedExample() {
        String input = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin";
        String expectedOutput = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT";
        assertEquals(expectedOutput, Abbreviations.test(commands, input));
    }

    @Test
    void testValidAbbreviations() {
        assertEquals("ADD", Abbreviations.test(commands, "add"));
        assertEquals("ALTER", Abbreviations.test(commands, "ALT"));
        assertEquals("ALTER", Abbreviations.test(commands, "aLt"));
        assertEquals("ALTER", Abbreviations.test(commands, "ALTE"));
        assertEquals("ALTER", Abbreviations.test(commands, "ALTER"));
        assertEquals("OVERLAY", Abbreviations.test(commands, "o"));
        assertEquals("OVERLAY", Abbreviations.test(commands, "ov"));
        assertEquals("OVERLAY", Abbreviations.test(commands, "oVe"));
        assertEquals("OVERLAY", Abbreviations.test(commands, "over"));
        assertEquals("OVERLAY", Abbreviations.test(commands, "overL"));
        assertEquals("OVERLAY", Abbreviations.test(commands, "overla"));

    }

    @Test
    void testInvalidAbbreviations() {
        assertEquals("*error*", Abbreviations.test(commands, "AL"));
        assertEquals("*error*", Abbreviations.test(commands, "ALF"));
        assertEquals("*error*", Abbreviations.test(commands, "ALTERS"));
        assertEquals("*error*", Abbreviations.test(commands, "TER"));
        assertEquals("*error*", Abbreviations.test(commands, "A")); // For commands with minLength 1, single letter abbreviation should still match the whole word
        assertEquals("*error*", Abbreviations.test(commands, "cop"));


    }

    @Test
    void testNoAbbreviationAllowed() {
        assertEquals("GET", Abbreviations.test(commands, "get"));
        assertEquals("MACRO", Abbreviations.test(commands, "macro"));
        assertEquals("*error*", Abbreviations.test(commands, "macr"));
        assertEquals("*error*", Abbreviations.test(commands, "g"));


    }



    @Test
    void testEmptyString() {
        assertEquals("", Abbreviations.test(commands, ""));
        assertEquals("", Abbreviations.test(commands, null));


    }



    @Test
    void testMixedCaseInput() {
        assertEquals("ADD", Abbreviations.test(commands, "AdD"));
        assertEquals("ALTER", Abbreviations.test(commands, "AlTeR"));


    }

    @Test
        void testWithNumbersAndSymbols() {
            assertEquals("*error* *error* *error*", Abbreviations.test(commands, "123 ... ///"));
        }

     @Test
        void testLongWords() {
         assertEquals("*error*", Abbreviations.test(commands,"thisIsAVeryLongWord"));

        }


     //Inner classes from Abbreviations.java needed for testing
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
            for (Command command : commands) {
                if (command.match(word))
                    return command;
            }
            return null;
        }
        private List<Command> commands = new ArrayList<>();
    }

    import java.util.*;
}
