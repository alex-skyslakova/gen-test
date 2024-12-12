import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class IdiomaticallyDetermineAllTheLowercaseAndUppercaseLettersTest {

    @Test
    fun testLowercaseLetters() {
        val expectedLowercase = "abcdefghijklmnopqrstuvwxyz"
        val actualLowercase = buildString {
            for (ch in 'a'..'z') append(ch)
        }
        assertEquals(expectedLowercase, actualLowercase, "The lowercase letters do not match the expected sequence.")
    }

    @Test
    fun testUppercaseLetters() {
        val expectedUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val actualUppercase = buildString {
            for (ch in 'A'..'Z') append(ch)
        }
        assertEquals(expectedUppercase, actualUppercase, "The uppercase letters do not match the expected sequence.")
    }
}
