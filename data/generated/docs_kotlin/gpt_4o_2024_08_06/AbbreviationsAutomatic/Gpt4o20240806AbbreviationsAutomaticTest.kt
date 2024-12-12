import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class AbbreviationTest {

    @Test
    fun testMinimumAbbreviationLength() {
        val testCases = listOf(
            "Sunday Monday Tuesday Wednesday Thursday Friday Saturday" to 2,
            "Sondag Maandag Dinsdag Woensdag Donderdag Vrydag Saterdag" to 2,
            "E_djelë E_hënë E_martë E_mërkurë E_enjte E_premte E_shtunë" to 5,
            "Ehud Segno Maksegno Erob Hamus Arbe Kedame" to 2,
            "Al_Ahad Al_Ithinin Al_Tholatha'a Al_Arbia'a Al_Kamis Al_Gomia'a Al_Sabit" to 4
        )

        for ((line, expected) in testCases) {
            val result = findMinimumAbbreviationLength(line)
            assertEquals(expected, result, "Failed for line: $line")
        }
    }

    @Test
    fun testEmptyLine() {
        val result = findMinimumAbbreviationLength("")
        assertEquals(0, result, "Failed for empty line")
    }

    private fun findMinimumAbbreviationLength(line: String): Int {
        if (line.trim().isEmpty()) return 0

        val r = Regex("[ ]+")
        val days = line.trim().split(r)
        if (days.size != 7) throw RuntimeException("There aren't 7 days in line")

        if (days.distinct().size < 7) {
            return Int.MAX_VALUE // implies some days have the same name
        }

        var len = 1
        while (true) {
            if (days.map { it.take(len) }.distinct().size == 7) {
                return len
            }
            len++
        }
    }
}
