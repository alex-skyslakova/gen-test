import kotlin.test.Test
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

    private fun validate(commands: List<String>, minLens: List<Int>, words: List<String>): List<String> {
        if (words.isEmpty()) return emptyList<String>()
        val results = mutableListOf<String>()
        for (word in words) {
            var matchFound = false
            for ((i, command) in commands.withIndex()) {
                if (minLens[i] == 0 || word.length !in minLens[i] .. command.length) continue 
                if (command.startsWith(word, true)) {
                    results.add(command.toUpperCase())
                    matchFound = true
                    break
                }
            }
            if (!matchFound) results.add("*error*")
        }
        return results
    }

    @Test
    fun testValidate() {
        val sentence = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin"
        val words = sentence.trim().split(r)
        val expected = listOf("RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT")
        val results = validate(commands, minLens, words)
        assertEquals(expected, results)
    }

    @Test
    fun testEmptyInput() {
        val words = emptyList<String>()
        val expected = emptyList<String>()
        val results = validate(commands, minLens, words)
        assertEquals(expected, results)
    }

    @Test
    fun testSingleValidWord() {
        val words = listOf("add")
        val expected = listOf("ADD")
        val results = validate(commands, minLens, words)
        assertEquals(expected, results)
    }

    @Test
    fun testSingleInvalidWord() {
        val words = listOf("invalid")
        val expected = listOf("*error*")
        val results = validate(commands, minLens, words)
        assertEquals(expected, results)
    }

    @Test
    fun testMixedCaseValidWord() {
        val words = listOf("aLtEr")
        val expected = listOf("ALTER")
        val results = validate(commands, minLens, words)
        assertEquals(expected, results)
    }

    @Test
    fun testMinimumLengthRequirement() {
        val words = listOf("al")
        val expected = listOf("*error*")
        val results = validate(commands, minLens, words)
        assertEquals(expected, results)
    }
}
