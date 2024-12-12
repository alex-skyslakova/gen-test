import org.junit.Test
import kotlin.test.assertEquals

class DetermineAllTheLowercaseAndUppercaseLettersTest {

    @Test
    fun testLowercaseLetters() {
        val lowercaseLetters = (CharRange('a', 'z')).joinToString("")
        assertEquals("abcdefghijklmnopqrstuvwxyz", lowercaseLetters)
    }

    @Test
    fun testUppercaseLetters() {
        val uppercaseLetters = (CharRange('A', 'Z')).joinToString("")
        assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", uppercaseLetters)
    }
}
