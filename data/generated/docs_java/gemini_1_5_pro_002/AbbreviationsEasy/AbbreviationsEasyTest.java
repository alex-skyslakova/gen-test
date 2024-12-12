import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class AbbreviationsEasyTest {

    private Map<String, Integer> createCmdTable() {
        String commandTable = "  Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy\n" +
                " COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find\n" +
                " NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput\n" +
                " Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO\n" +
                " MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT\n" +
                " READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT\n" +
                " RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus TOP TRAnsfer Type Up";
        String[] cmdTableArr = commandTable.split("\\s+");
        Map<String, Integer> cmd_table = new HashMap<>();

        for (String word : cmdTableArr) {
            cmd_table.put(word, countCaps(word));
        }
        return cmd_table;
    }

    private String validateCommand(String userInput, Map<String, Integer> cmd_table) {
        if (userInput == null || userInput.trim().isEmpty()) {
            return "";
        }

        String[] user_input = userInput.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String s : user_input) {
            boolean match = false;
            for (String cmd : cmd_table.keySet()) {
                if (s.length() >= cmd_table.get(cmd) && s.length() <= cmd.length()) {
                    String temp = cmd.toUpperCase();
                    if (temp.startsWith(s.toUpperCase())) {
                        result.append(temp).append(" ");
                        match = true;
                        break; 
                    }
                }
            }
            if (!match) {
                result.append("*error* ");
            }
        }
        return result.toString().trim();
    }


    private int countCaps(String word) {
        int numCaps = 0;
        for (int i = 0; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                numCaps++;
            }
        }
        return numCaps;
    }


    @Test
    void testValidAbbreviations() {
        Map<String, Integer> cmd_table = createCmdTable();
        assertEquals("RIGHT", validateCommand("riG", cmd_table));
        assertEquals("REPEAT", validateCommand("rePEAT", cmd_table));
        assertEquals("PUT", validateCommand("put", cmd_table));
        assertEquals("MOVE", validateCommand("mo", cmd_table));
        assertEquals("RESTORE", validateCommand("rest", cmd_table));
        assertEquals("POWERINPUT", validateCommand("poweRin", cmd_table));
        assertEquals("OVERLAY", validateCommand("overla", cmd_table));


    }

    @Test
    void testInvalidAbbreviations() {
        Map<String, Integer> cmd_table = createCmdTable();
        assertEquals("*error*", validateCommand("copies", cmd_table));
        assertEquals("*error*", validateCommand("types", cmd_table));
        assertEquals("*error*", validateCommand("fup.", cmd_table));
        assertEquals("*error*", validateCommand("6", cmd_table));
        assertEquals("*error*", validateCommand("a", cmd_table)); // Too short for any command

    }

    @Test
    void testEmptyString() {
        Map<String, Integer> cmd_table = createCmdTable();
        assertEquals("", validateCommand("", cmd_table));
        assertEquals("", validateCommand(null, cmd_table));
        assertEquals("", validateCommand("   ", cmd_table));
    }


    @Test
    void testCombinedInput() {
        Map<String, Integer> cmd_table = createCmdTable();
        assertEquals("RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error*", validateCommand("riG rePEAT copies put mo rest types fup.", cmd_table));
    }




}
