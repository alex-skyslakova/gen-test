import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AbbreviationsSimpleTest {

    private val commandTable = "" +
            "add 1  alter 3  backup 2  bottom 1  Cappend 2  change 1  Schange  Cinsert 2  Clast 3 " +
            "compress 4 copy 2 count 3 Coverlay 3 cursor 3  delete 3 Cdelete 2  down 1  duplicate " +
            "3 xEdit 1 expand 3 extract 3  find 1 Nfind 2 Nfindup 6 NfUP 3 Cfind 2 findUP 3 fUP 2 " +
            "forward 2  get  help 1 hexType 4  input 1 powerInput 3  join 1 split 2 spltJOIN load " +
            "locate 1 Clocate 2 lowerCase 3 upperCase 3 Lprefix 2  macro  merge 2 modify 3 move 2 " +
            "msg  next 1 overlay 1 parse preserve 4 purge 3 put putD query 1 quit  read recover 3 " +
            "refresh renum 3 repeat 3 replace 1 Creplace 2 reset 3 restore 4 rgtLEFT right 2 left " +
            "2  save  set  shift 2  si  sort  sos  stack 3 status 4 top  transfer 3  type 1  up 1 "

    private val commands = parse(commandTable)

    @Test
    fun testValidAbbreviations() {
        assertEquals("RIGHT", get(commands, "riG"))
        assertEquals("REPEAT", get(commands, "rePEAT"))
        assertEquals("PUT", get(commands, "put"))
        assertEquals("MOVE", get(commands, "mo"))
        assertEquals("RESTORE", get(commands, "rest"))
        assertEquals("POWERINPUT", get(commands, "poweRin"))
    }

    @Test
    fun testInvalidAbbreviations() {
        assertEquals("*error*", get(commands, "copies"))
        assertEquals("*error*", get(commands, "types"))
        assertEquals("*error*", get(commands, "fup."))
        assertEquals("*error*", get(commands, "6"))
    }

    @Test
    fun testBlankOrNullInput() {
        assertEquals("", get(commands, ""))
        assertEquals("", get(commands, null))
    }

    private fun parse(commandList: String): List<Command> {
        val commands = mutableListOf<Command>()
        val fields = commandList.trim().split(" ")
        var i = 0
        while (i < fields.size) {
            val name = fields[i++]
            var minLen = name.length
            if (i < fields.size) {
                val num = fields[i].toIntOrNull()
                if (num != null && num in 1..minLen) {
                    minLen = num
                    i++
                }
            }
            commands.add(Command(name, minLen))
        }
        return commands
    }

    private fun get(commands: List<Command>, word: String?): String? {
        if (word.isNullOrBlank()) return ""
        for ((name, minLen) in commands) {
            if (word.length in minLen..name.length && name.startsWith(word, true)) {
                return name.toUpperCase(Locale.ROOT)
            }
        }
        return "*error*"
    }

    private data class Command(val name: String, val minLen: Int)
}
