import org.junit.Test
import kotlin.test.assertEquals

class AbbreviationsEasyTest {

    private val r = Regex("[ ]+")

    private val table = 
        "Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy " +
        "COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find " +
        "NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput " +
        "Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO " + 
        "MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT " +
        "READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT " +
        "RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up "

    private val commands = table.trimEnd().split(r)
    private val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }

    @Test
    fun testEmptyInput() {
        val words = listOf<String>()
        val expected = listOf<String>()
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testValidAbbreviations() {
        val words = listOf("riG", "rePEAT", "copies", "put", "mo", "rest", "types", "fup.", "6", "poweRin")
        val expected = listOf("RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testInvalidAbbreviations() {
        val words = listOf("AL", "ALF", "ALTERS", "TER", "A")
        val expected = listOf("*error*", "*error*", "*error*", "*error*", "*error*")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testMixedCaseAbbreviations() {
        val words = listOf("ALT", "aLt", "ALTE", "ALTER")
        val expected = listOf("ALTER", "ALTER", "ALTER", "ALTER")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testNoLowercaseAbbreviations() {
        val words = listOf("o", "ov", "oVe", "over", "overL", "overla")
        val expected = listOf("OVERLAY", "OVERLAY", "OVERLAY", "OVERLAY", "OVERLAY", "OVERLAY")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testExactMatch() {
        val words = listOf("Add", "COpy", "QUIT")
        val expected = listOf("ADD", "COPY", "QUIT")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testTooLongAbbreviation() {
        val words = listOf("ALTERS", "OVERLAYS")
        val expected = listOf("*error*", "*error*")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testSingleCharacterAbbreviation() {
        val words = listOf("A", "B", "C")
        val expected = listOf("*error*", "*error*", "*error*")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testMixedValidAndInvalid() {
        val words = listOf("riG", "AL", "rePEAT", "TER", "copies", "A")
        val expected = listOf("RIGHT", "*error*", "REPEAT", "*error*", "*error*", "*error*")
        assertEquals(expected, validate(commands, minLens, words))
    }
}
