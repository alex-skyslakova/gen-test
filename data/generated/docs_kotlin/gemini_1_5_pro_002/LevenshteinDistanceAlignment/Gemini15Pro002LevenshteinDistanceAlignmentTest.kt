import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class LevenshteinDistanceAlignmentTest {

    @Test
    fun testPlacePalace() {
        val result = levenshteinAlign("place", "palace")
        assertArrayEquals(arrayOf("p-lace", "palace"), result)
    }

    @Test
    fun testRosettacodeRaisethysword() {
        val result = levenshteinAlign("rosettacode", "raisethysword")
        assertArrayEquals(arrayOf("rosettacode", "r-aiset-hysword"), result)
    }

    @Test
    fun testEmptyString() {
        val result = levenshteinAlign("", "")
        assertArrayEquals(arrayOf("", ""), result)
    }

    @Test
    fun testOneEmptyString() {
        val result = levenshteinAlign("hello", "")
        assertArrayEquals(arrayOf("hello", "-----"), result)
    }

    @Test
    fun testOtherEmptyString() {
        val result = levenshteinAlign("", "world")
        assertArrayEquals(arrayOf("-----", "world"), result)
    }


    @Test
    fun testIdenticalStrings() {
        val result = levenshteinAlign("same", "same")
        assertArrayEquals(arrayOf("same", "same"), result)
    }

    @Test
    fun testCaseSensitivity() {
        val result = levenshteinAlign("CaSe", "case")
        assertArrayEquals(arrayOf("case", "case"), result)
    }

    @Test
    fun testNumbers() {
        val result = levenshteinAlign("1234", "124")
        assertArrayEquals(arrayOf("1234", "12-4"), result)
    }

    @Test
    fun testSymbols() {
        val result = levenshteinAlign("!@#\$", "!@#")
        assertArrayEquals(arrayOf("!@#\$", "!@#-"), result)
    }

    @Test
    fun testMixedCaseAndSymbols(){
        val result = levenshteinAlign("HeLlO123!", "hELLo12!")
        assertArrayEquals(arrayOf("hello123!", "hello12--!"), result)
    }



}
