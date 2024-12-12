import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class LetterFrequencyTest {

    @Test
    fun testEmptyFile() {
        val file = File("empty.txt")
        file.writeText("")
        val result = countLetterFrequency(file)
        assertEquals(emptyMap<Char, Int>(), result)
    }

    @Test
    fun testFileWithNoLetters() {
        val file = File("no_letters.txt")
        file.writeText("1234567890!@#$%^&*()")
        val result = countLetterFrequency(file)
        assertEquals(emptyMap<Char, Int>(), result)
    }

    @Test
    fun testFileWithOnlyLetters() {
        val file = File("only_letters.txt")
        file.writeText("abcABC")
        val result = countLetterFrequency(file)
        assertEquals(mapOf('a' to 2, 'b' to 2, 'c' to 2), result)
    }

    @Test
    fun testFileWithMixedContent() {
        val file = File("mixed_content.txt")
        file.writeText("Hello, World! 123")
        val result = countLetterFrequency(file)
        assertEquals(mapOf('h' to 1, 'e' to 1, 'l' to 3, 'o' to 2, 'w' to 1, 'r' to 1, 'd' to 1), result)
    }

    @Test
    fun testFileWithAllLetters() {
        val file = File("all_letters.txt")
        file.writeText("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
        val result = countLetterFrequency(file)
        assertEquals(('a'..'z').associateWith { 2 }, result)
    }

    private fun countLetterFrequency(file: File): Map<Char, Int> {
        val text = file.readText().toLowerCase()
        return text.filter { it in 'a'..'z' }.groupBy { it }.mapValues { it.value.size }
    }
}
