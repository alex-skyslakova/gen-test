import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.ByteArrayInputStream

class AbbreviationTest {

    private val r = Regex("[ ]+")

    private fun processLine(line: String): String? {
        if (line.trim().isEmpty()) {
            return null
        }
        val days = line.trim().split(r)
        if (days.size != 7) throw RuntimeException("There aren't 7 days in line")
        if (days.distinct().size < 7) { // implies some days have the same name
            return " ∞  $line"
        }
        var len = 1
        while (true) {
            if (days.map { it.take(len) }.distinct().size == 7) {
                return "${"%2d".format(len)}  $line"
            }
            len++
        }
    }


    @Test
    fun testEmptyLine() {
        assertNull(processLine(""))
        assertNull(processLine("   "))
    }

    @Test
    fun testFirstFiveLines() {
        val input = """
        Sunday Monday Tuesday Wednesday Thursday Friday Saturday
        Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
        E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë
        Ehud Segno Maksegno Erob Hamus Arbe Kedame
        Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit
        """.trimIndent()

        val expected = """
            3  Sunday Monday Tuesday Wednesday Thursday Friday Saturday
            3  Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag
            2  E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë
            2  Ehud Segno Maksegno Erob Hamus Arbe Kedame
            3  Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit
        """.trimIndent()

        val actual = input.lineSequence().map { processLine(it) }.filterNotNull().joinToString("\n")
       assertEquals(expected, actual)
    }

    @Test
    fun testDuplicateDays() {
        val input = "Same Same Same Same Same Same Same"
        assertEquals(" ∞  $input", processLine(input))
    }

    @Test
    fun testInvalidNumberOfDays(){
        val input = "One Two Three Four Five Six"
        assertThrows(RuntimeException::class.java) { processLine(input)}
    }

    @Test
    fun testLongAbbreviations(){
        val input = "Sunday1 Sunday2 Sunday3 Sunday4 Sunday5 Sunday6 Sunday7"
        assertEquals(" 7  $input", processLine(input))
    }

}


