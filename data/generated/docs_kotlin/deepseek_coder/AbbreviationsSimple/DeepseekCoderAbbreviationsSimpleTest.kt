import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.util.Locale

class CommandAbbreviationTest {

    private val table = "" +
            "add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3 " +
            "compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate " +
            "3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2 " +
            "forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load " +
            "locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2 " +
            "msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3 " +
            "refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left " +
            "2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1 "

    private val commands = parse(table)

    @Test
    fun testValidAbbreviations() {
        val testCases = mapOf(
            "riG" to "RIGHT",
            "rePEAT" to "REPEAT",
            "copies" to "*error*",
            "put" to "PUT",
            "mo" to "MOVE",
            "rest" to "RESTORE",
            "types" to "*error*",
            "fup." to "*error*",
            "6" to "*error*",
            "poweRin" to "POWERINPUT"
        )

        for ((input, expected) in testCases) {
            val result = get(commands, input) ?: "*error*"
            assertEquals(expected, result)
        }
    }

    @Test
    fun testInvalidAbbreviations() {
        val testCases = listOf(
            "AL",
            "ALF",
            "ALTERS",
            "TER",
            "A",
            "ov",
            "oVe",
            "over",
            "overL",
            "overla",
            "ov",
            "oVe",
            "over",
            "overL",
            "overla",
            "ov",
            "oVe",
            "over",
            "overL",
            "overla"
        )

        for (input in testCases) {
            val result = get(commands, input) ?: "*error*"
            assertEquals("*error*", result)
        }
    }

    @Test
    fun testBlankInput() {
        val result = get(commands, "") ?: "*error*"
        assertEquals("*error*", result)
    }

    @Test
    fun testNullInput() {
        val result = get(commands, null) ?: "*error*"
        assertEquals("*error*", result)
    }

    @Test
    fun testMixedCaseInput() {
        val testCases = mapOf(
            "riG" to "RIGHT",
            "rePEAT" to "REPEAT",
            "copies" to "*error*",
            "put" to "PUT",
            "mo" to "MOVE",
            "rest" to "RESTORE",
            "types" to "*error*",
            "fup." to "*error*",
            "6" to "*error*",
            "poweRin" to "POWERINPUT"
        )

        for ((input, expected) in testCases) {
            val result = get(commands, input) ?: "*error*"
            assertEquals(expected, result)
        }
    }

    @Test
    fun testFullWords() {
        val testCases = mapOf(
            "add" to "ADD",
            "alter" to "ALTER",
            "backup" to "BACKUP",
            "bottom" to "BOTTOM",
            "Cappend" to "CAPPEND",
            "change" to "CHANGE",
            "Schange" to "SCHANGE",
            "Cinsert" to "CINSERT",
            "Clast" to "CLAST",
            "compress" to "COMPRESS",
            "copy" to "COPY",
            "count" to "COUNT",
            "Coverlay" to "COVERLAY",
            "cursor" to "CURSOR",
            "delete" to "DELETE",
            "Cdelete" to "CDELETE",
            "down" to "DOWN",
            "duplicate" to "DUPLICATE",
            "xEdit" to "XEDIT",
            "expand" to "EXPAND",
            "extract" to "EXTRACT",
            "find" to "FIND",
            "Nfind" to "NFIND",
            "Nfindup" to "NFINDUP",
            "NfUP" to "NFUP",
            "Cfind" to "CFIND",
            "findUP" to "FINDUP",
            "fUP" to "FUP",
            "forward" to "FORWARD",
            "get" to "GET",
            "help" to "HELP",
            "hexType" to "HEXTYPE",
            "input" to "INPUT",
            "powerInput" to "POWERINPUT",
            "join" to "JOIN",
            "split" to "SPLIT",
            "spltJOIN" to "SPLTJOIN",
            "load" to "LOAD",
            "locate" to "LOCATE",
            "Clocate" to "CLOCATE",
            "lowerCase" to "LOWERCASE",
            "upperCase" to "UPPERCASE",
            "Lprefix" to "LPREFIX",
            "macro" to "MACRO",
            "merge" to "MERGE",
            "modify" to "MODIFY",
            "move" to "MOVE",
            "msg" to "MSG",
            "next" to "NEXT",
            "overlay" to "OVERLAY",
            "parse" to "PARSE",
            "preserve" to "PRESERVE",
            "purge" to "PURGE",
            "put" to "PUT",
            "putD" to "PUTD",
            "query" to "QUERY",
            "quit" to "QUIT",
            "read" to "READ",
            "recover" to "RECOVER",
            "refresh" to "REFRESH",
            "renum" to "RENUM",
            "repeat" to "REPEAT",
            "replace" to "REPLACE",
            "Creplace" to "CREPLACE",
            "reset" to "RESET",
            "restore" to "RESTORE",
            "rgtLEFT" to "RGTLEFT",
            "right" to "RIGHT",
            "left" to "LEFT",
            "save" to "SAVE",
            "set" to "SET",
            "shift" to "SHIFT",
            "si" to "SI",
            "sort" to "SORT",
            "sos" to "SOS",
            "stack" to "STACK",
            "status" to "STATUS",
            "top" to "TOP",
            "transfer" to "TRANSFER",
            "type" to "TYPE",
            "up" to "UP"
        )

        for ((input, expected) in testCases) {
            val result = get(commands, input) ?: "*error*"
            assertEquals(expected, result)
        }
    }

    @Test
    fun testNonLatinAlphabet() {
        val testCases = listOf(
            "add1",
            "alter3",
            "backup2",
            "bottom1",
            "Cappend2",
            "change1",
            "Schange",
            "Cinsert2",
            "Clast3",
            "compress4",
            "copy2",
            "count3",
            "Coverlay3",
            "cursor3",
            "delete3",
            "Cdelete2",
            "down1",
            "duplicate3",
            "xEdit1",
            "expand3",
            "extract3",
            "find1",
            "Nfind2",
            "Nfindup6",
            "NfUP3",
            "Cfind2",
            "findUP3",
            "fUP2",
            "forward2",
            "get",
            "help1",
            "hexType4",
            "input1",
            "powerInput3",
            "join1",
            "split2",
            "spltJOIN",
            "load",
            "locate1",
            "Clocate2",
            "lowerCase3",
            "upperCase3",
            "Lprefix2",
            "macro",
            "merge2",
            "modify3",
            "move2",
            "msg",
            "next1",
            "overlay1",
            "parse",
            "preserve4",
            "purge3",
            "put",
            "putD",
            "query1",
            "quit",
            "read",
            "recover3",
            "refresh",
            "renum3",
            "repeat3",
            "replace1",
            "Creplace2",
            "reset3",
            "restore4",
            "rgtLEFT",
            "right2",
            "left2",
            "save",
            "set",
            "shift2",
            "si",
            "sort",
            "sos",
            "stack3",
            "status4",
            "top",
            "transfer3",
            "type1",
            "up1"
        )

        for (input in testCases) {
            val result = get(commands, input) ?: "*error*"
            assertEquals("*error*", result)
        }
    }
}
