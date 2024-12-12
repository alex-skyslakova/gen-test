import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AbbreviationTest {

    private val r = Regex("[ ]+")

    private val table =
        "Add ALTer  BAckup Bottom  CAppend Change SCHANGE  CInsert CLAst COMPress COpy " +
                "COUnt COVerlay CURsor DELete CDelete Down DUPlicate Xedit EXPand EXTract Find " +
                "NFind NFINDUp NFUp CFind FINdup FUp FOrward GET Help HEXType Input POWerinput " +
                "Join SPlit SPLTJOIN  LOAD  Locate CLocate  LOWercase UPPercase  LPrefix MACRO " +
                "MErge MODify MOve MSG Next Overlay PARSE PREServe PURge PUT PUTD  Query  QUIT " +
                "READ  RECover REFRESH RENum REPeat  Replace CReplace  RESet  RESTore  RGTLEFT " +
                "RIght LEft  SAVE  SET SHift SI  SORT  SOS  STAck STATus  TOP TRAnsfer Type Up "

    private fun validate(commands: List<String>, minLens: List<Int>, words: List<String>): List<String> {
        if (words.isEmpty()) return emptyList()
        val results = mutableListOf<String>()
        for (word in words) {
            var matchFound = false
            for ((i, command) in commands.withIndex()) {
                if (minLens[i] == 0 || word.length !in minLens[i]..command.length) continue
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
    fun testExampleCase() {
        val commands = table.trimEnd().split(r)
        val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }
        val sentence = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin"
        val words = sentence.trim().split(r)
        val expected = listOf("RIGHT", "REPEAT", "*error*", "PUT", "MOVE", "RESTORE", "*error*", "*error*", "*error*", "POWERINPUT")
        assertEquals(expected, validate(commands, minLens, words))
    }

    @Test
    fun testEmptyString() {
        val commands = table.trimEnd().split(r)
        val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }
        val sentence = ""
        val words = sentence.trim().split(r)
        val expected = emptyList<String>()
        assertEquals(expected, validate(commands, minLens, words))
    }


    @Test
    fun testAllValidAbbreviations() {
        val commands = table.trimEnd().split(r)
        val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }
        val sentence = commands.joinToString(" ") { it.substring(0, it.count { c -> c.isUpperCase() }) }
        val words = sentence.trim().split(r)
        val expected = commands.map { it.toUpperCase() }
        assertEquals(expected, validate(commands, minLens, words))

    }


    @Test
    fun testInvalidCharacters() {
        val commands = table.trimEnd().split(r)
        val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }
        val sentence = "123 !@#$%^"
        val words = sentence.trim().split(r)
        val expected = listOf("*error*", "*error*", "*error*")
        assertEquals(expected, validate(commands, minLens, words))
    }


    @Test
    fun testTooShortAndTooLongAbbreviations() {
        val commands = table.trimEnd().split(r)
        val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }
        val sentence = "A ALTeRS"
        val words = sentence.trim().split(r)
        val expected = listOf("*error*", "*error*")
        assertEquals(expected, validate(commands, minLens, words))

    }


    @Test
    fun testMixedCase() {
        val commands = table.trimEnd().split(r)
        val minLens = MutableList(commands.size) { commands[it].count { c -> c.isUpperCase() } }
        val sentence = "aDd  aLtEr bAcKup"
        val words = sentence.trim().split(r)
        val expected = listOf("ADD", "ALTER", "BACKUP")
        assertEquals(expected, validate(commands, minLens, words))
    }




}
