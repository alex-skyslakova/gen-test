import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ABCBlockCheckerTest {

    private val blocks = arrayOf(
        "BO", "XK", "DQ", "CP", "NA", "GT", "RE", "TG", "QD", "FS",
        "JW", "HU", "VI", "AN", "OB", "ER", "FS", "LY", "PC", "ZM"
    )

    @Test
    fun testCanMakeWord_EmptyString() {
        assertTrue(blocks.canMakeWord(""))
    }

    @Test
    fun testCanMakeWord_SingleLetter() {
        assertTrue(blocks.canMakeWord("A"))
    }

    @Test
    fun testCanMakeWord_BARK() {
        assertTrue(blocks.canMakeWord("BARK"))
    }

    @Test
    fun testCanMakeWord_BOOK() {
        assertFalse(blocks.canMakeWord("BOOK"))
    }

    @Test
    fun testCanMakeWord_TREAT() {
        assertTrue(blocks.canMakeWord("TREAT"))
    }

    @Test
    fun testCanMakeWord_COMMON() {
        assertFalse(blocks.canMakeWord("COMMON"))
    }

    @Test
    fun testCanMakeWord_SQUAD() {
        assertTrue(blocks.canMakeWord("SQUAD"))
    }

    @Test
    fun testCanMakeWord_CONFUSE() {
        assertTrue(blocks.canMakeWord("CONFUSE"))
    }

    @Test
    fun testCanMakeWord_CaseInsensitivity() {
        assertTrue(blocks.canMakeWord("bark"))
        assertTrue(blocks.canMakeWord("Book"))
        assertTrue(blocks.canMakeWord("tReAt"))
        assertFalse(blocks.canMakeWord("common"))
        assertTrue(blocks.canMakeWord("sQUaD"))
        assertTrue(blocks.canMakeWord("cOnFuSe"))
    }

    @Test
    fun testCanMakeWord_InvalidWord() {
        assertFalse(blocks.canMakeWord("ZZZ"))
        assertFalse(blocks.canMakeWord("XYZ"))
    }

    @Test
    fun testCanMakeWord_LongWord() {
        assertFalse(blocks.canMakeWord("SUPERCALIFRAGILISTICEXPIALIDOCIOUS"))
    }

    @Test
    fun testCanMakeWord_AllLetters() {
        assertTrue(blocks.canMakeWord("ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
    }
}
