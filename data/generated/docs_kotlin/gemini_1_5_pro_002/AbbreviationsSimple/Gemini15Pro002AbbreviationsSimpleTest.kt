import org.junit.jupiter.api.Test
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AbbreviationTest {

    private val table = "" +
            "add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3 " +
            "compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate " +
            "3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2 " +
            "forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load " +
            "locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2 " +
            "msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3 " +
            "refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left " +
            "2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1 "

    private data class Command(val name: String, val minLen: Int)

    private fun parse(commandList: String): List<Command> {
        // ... (same as in original code)
    }

    private fun get(commands: List<Command>, word: String): String? {
        // ... (same as in original code)
    }


    @Test
    fun testValidAbbreviations() {
        val commands = parse(table)
        assertEquals("ALTER", get(commands, "ALT"))
        assertEquals("ALTER", get(commands, "aLt"))
        assertEquals("ALTER", get(commands, "ALTE"))
        assertEquals("ALTER", get(commands, "ALTER"))
        assertEquals("OVERLAY", get(commands, "o"))
        assertEquals("OVERLAY", get(commands, "ov"))
        assertEquals("OVERLAY", get(commands, "oVe"))
        assertEquals("OVERLAY", get(commands, "over"))
        assertEquals("OVERLAY", get(commands, "overL"))
        assertEquals("OVERLAY", get(commands, "overla"))
        assertEquals("RIGHT", get(commands,"riG"))
        assertEquals("REPEAT", get(commands, "rePEAT"))
        assertEquals("PUT", get(commands, "put"))
        assertEquals("MOVE", get(commands, "mo"))
        assertEquals("RESTORE", get(commands, "rest"))
        assertEquals("POWERINPUT", get(commands, "poweRin"))


    }

    @Test
    fun testInvalidAbbreviations() {
        val commands = parse(table)
        assertEquals(null, get(commands, "AL"))
        assertEquals(null, get(commands, "ALF"))
        assertEquals(null, get(commands, "ALTERS"))
        assertEquals(null, get(commands, "TER"))
        assertEquals(null, get(commands, "A"))
        assertEquals(null, get(commands, "copies"))
        assertEquals(null, get(commands, "types"))
        assertEquals(null, get(commands, "fup."))
        assertEquals(null, get(commands, "6"))
    }


    @Test
    fun testEmptyString() {
        val commands = parse(table)
        assertNull(get(commands, ""))
        assertNull(get(commands, "  "))  // Test with spaces
    }



    @Test
    fun testExampleTestCase() {
        val commands = parse(table)
        val sentence = "riG   rePEAT copies  put mo   rest    types   fup.    6       poweRin"
        val words = sentence.trim().split(" ")

        val expected = "RIGHT REPEAT *error* PUT MOVE RESTORE *error* *error* *error* POWERINPUT"
        val actual = words.map { word -> get(commands, word) ?: "*error*" }.joinToString(" ")

        assertEquals(expected, actual)
    }



    @Test
    fun testNoAbbreviationAllowed() {
        val commands = parse(table)
        assertEquals("GET", get(commands, "get"))
        assertEquals(null, get(commands, "g")) // No abbreviation allowed
        assertEquals("MACRO", get(commands, "macro"))
        assertEquals(null, get(commands, "mac")) // No abbreviation allowed

    }


}
