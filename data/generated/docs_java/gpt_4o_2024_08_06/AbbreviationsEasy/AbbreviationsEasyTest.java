import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbbreviationsEasyTest {

    @Test
    public void testValidAbbreviations() {
        assertEquals("ALTER", validateCommand("alt"));
        assertEquals("ALTER", validateCommand("ALTer"));
        assertEquals("MOVE", validateCommand("moVe"));
        assertEquals("POWERINPUT", validateCommand("poweRin"));
    }

    @Test
    public void testInvalidAbbreviations() {
        assertEquals("*error*", validateCommand("AL"));
        assertEquals("*error*", validateCommand("ALF"));
        assertEquals("*error*", validateCommand("ALTERS"));
        assertEquals("*error*", validateCommand("TER"));
        assertEquals("*error*", validateCommand("A"));
        assertEquals("*error*", validateCommand("copies"));
        assertEquals("*error*", validateCommand("types"));
        assertEquals("*error*", validateCommand("fup."));
        assertEquals("*error*", validateCommand("6"));
    }

    @Test
    public void testBlankOrNullInput() {
        assertEquals("", validateCommand(""));
        assertEquals("", validateCommand(null));
    }

    @Test
    public void testMixedCaseInput() {
        assertEquals("RIGHT", validateCommand("riG"));
        assertEquals("REPEAT", validateCommand("rePEAT"));
        assertEquals("PUT", validateCommand("put"));
        assertEquals("MOVE", validateCommand("mo"));
        assertEquals("RESTORE", validateCommand("rest"));
    }

    private String validateCommand(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String[] cmdTableArr = ("  Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy\n" +
                " COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find\n" +
                " NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput\n" +
                " Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO\n" +
                " MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT\n" +
                " READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT\n" +
                " RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus TOP TRAnsfer Type Up").split("\\s+");

        for (String cmd : cmdTableArr) {
            int minLength = countCaps(cmd);
            if (input.length() >= minLength && input.length() <= cmd.length()) {
                String temp = cmd.toUpperCase();
                if (temp.startsWith(input.toUpperCase())) {
                    return temp;
                }
            }
        }
        return "*error*";
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
}
