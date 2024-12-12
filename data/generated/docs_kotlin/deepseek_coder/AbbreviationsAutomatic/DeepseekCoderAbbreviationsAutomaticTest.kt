import org.junit.Test
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AbbreviationsAutomaticTest {

    @Test
    fun testMinimumAbbreviationLength() {
        val lines = listOf(
            "Sunday Monday Tuesday Wednesday Thursday Friday Saturday",
            "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag",
            "E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë",
            "Ehud Segno Maksegno Erob Hamus Arbe Kedame",
            "Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit"
        )

        val expectedResults = listOf(
            " 3  Sunday Monday Tuesday Wednesday Thursday Friday Saturday",
            " 3  Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag",
            " 4  E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë",
            " 4  Ehud Segno Maksegno Erob Hamus Arbe Kedame",
            " 4  Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit"
        )

        for ((line, expected) in lines.zip(expectedResults)) {
            val result = findMinimumAbbreviationLength(line)
            assertEquals(expected, result)
        }
    }

    @Test
    fun testBlankLine() {
        val blankLine = ""
        val result = findMinimumAbbreviationLength(blankLine)
        assertEquals("", result)
    }

    @Test
    fun testNotSevenDays() {
        val invalidLine = "Sunday Monday Tuesday Wednesday Thursday Friday"
        assertFailsWith<RuntimeException> {
            findMinimumAbbreviationLength(invalidLine)
        }
    }

    @Test
    fun testDuplicateDays() {
        val duplicateDaysLine = "Sunday Sunday Tuesday Wednesday Thursday Friday Saturday"
        val result = findMinimumAbbreviationLength(duplicateDaysLine)
        assertEquals(" ∞  $duplicateDaysLine", result)
    }

    private fun findMinimumAbbreviationLength(line: String): String {
        if (line.trim().isEmpty()) return ""

        val days = line.trim().split(Regex("[ ]+"))
        if (days.size != 7) throw RuntimeException("There aren't 7 days in the line")

        if (days.distinct().size < 7) {
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
}
