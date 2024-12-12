import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ABCBlockCheckerTest {

    private val blocks = arrayOf(
        "BO", "XK", "DQ", "CP", "NA", "GT", "RE", "TG", "QD", "FS",
        "JW", "HU", "VI", "AN", "OB", "ER", "FS", "LY", "PC", "ZM"
    )

    private fun Array<String>.canMakeWord(word: String): Boolean {
        if (word.isEmpty())
            return true

        val c = word.first().toUpperCase()
        var i = 0
        forEach { b ->
            if (b.first().toUpperCase() == c || b[1].toUpperCase() == c) {
                swap(0, i)
                if (drop(1).toTypedArray().canMakeWord(word.substring(1)))
                    return true
                swap(0, i)
            }
            i++
        }

        return false
    }

    private fun Array<String>.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }

    @Test
    fun testCanMakeWord() {
        assertTrue(blocks.canMakeWord("A"), "Expected to be able to make the word 'A'")
        assertTrue(blocks.canMakeWord("BARK"), "Expected to be able to make the word 'BARK'")
        assertFalse(blocks.canMakeWord("BOOK"), "Expected not to be able to make the word 'BOOK'")
        assertTrue(blocks.canMakeWord("TREAT"), "Expected to be able to make the word 'TREAT'")
        assertFalse(blocks.canMakeWord("COMMON"), "Expected not to be able to make the word 'COMMON'")
        assertTrue(blocks.canMakeWord("SQUAD"), "Expected to be able to make the word 'SQUAD'")
        assertTrue(blocks.canMakeWord("CONFUSE"), "Expected to be able to make the word 'CONFUSE'")
    }
}
