import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class CollapseTest {

    @Test
    fun testCollapseEmptyString() {
        assertEquals("", collapse(""))
    }

    @Test
    fun testCollapseNoRepeats() {
        assertEquals("aardvark", collapse("aardvark"))
    }

    @Test
    fun testCollapseSingleRepeats() {
        assertEquals("The beter the 4-whel drive, the further you'l be from help when ya get stuck!", collapse("The better the 4-wheel drive, the further you'll be from help when ya get stuck!"))
    }

    @Test
    fun testCollapseMultipleRepeats() {
        assertEquals("headmistreship", collapse("headmistressship"))
    }

    @Test
    fun testCollapseLeadingRepeats() {
        assertEquals(".178", collapse("..1111111111111111111111111111111111111111111111111111111111111117777888"))
    }
    
    @Test
    fun testCollapseTrailingRepeats() {
        assertEquals("A", collapse("AAAA"))

    }

    @Test
    fun testCollapseMixedRepeats() {
        assertEquals("\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln ", collapse("\"If I were two-faced, would I be wearing this one?\" --- Abraham Lincoln "))

    }

    @Test
    fun testCollapseWhitespaceRepeats() {
        assertEquals(" ---  Harry S Truman  ", collapse("                                                   ---  Harry S Truman  "))
    }


    private fun collapse(s: String): String {
        // The function being tested is placed here for testing purposes.
        val cs = StringBuilder()
        var last: Char = 0.toChar()
        for (c in s) {
            if (c != last) {
                cs.append(c)
                last = c
            }
        }
        return cs.toString()
    }
}
