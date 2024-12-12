import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EntropyNarcissistTest {

    @Test
    fun testShannonEntropyEmptyString() {
        val entropy = shannon("")
        assertEquals(0.0, entropy, "Entropy of an empty string should be 0.0")
    }

    @Test
    fun testShannonEntropySingleCharacter() {
        val entropy = shannon("a")
        assertEquals(0.0, entropy, "Entropy of a single character string should be 0.0")
    }

    @Test
    fun testShannonEntropyIdenticalCharacters() {
        val entropy = shannon("aaaa")
        assertEquals(0.0, entropy, "Entropy of a string with identical characters should be 0.0")
    }

    @Test
    fun testShannonEntropyTwoDifferentCharacters() {
        val entropy = shannon("ab")
        assertEquals(1.0, entropy, "Entropy of a string with two different characters should be 1.0")
    }

    @Test
    fun testShannonEntropyMultipleCharacters() {
        val entropy = shannon("abcabc")
        assertEquals(1.584962500721156, entropy, 1e-9, "Entropy of 'abcabc' should be approximately 1.584962500721156")
    }

    @Test
    fun testShannonEntropyComplexString() {
        val entropy = shannon("hello world")
        assertEquals(3.180832987, entropy, 1e-9, "Entropy of 'hello world' should be approximately 3.180832987")
    }

    @Test
    fun testShannonEntropyNumericString() {
        val entropy = shannon("1234567890")
        assertEquals(3.321928094887362, entropy, 1e-9, "Entropy of '1234567890' should be approximately 3.321928094887362")
    }

    @Test
    fun testShannonEntropySpecialCharacters() {
        val entropy = shannon("!@#$%^&*()")
        assertEquals(3.321928094887362, entropy, 1e-9, "Entropy of '!@#$%^&*()' should be approximately 3.321928094887362")
    }
}
