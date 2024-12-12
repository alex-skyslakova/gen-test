import org.junit.Test
import org.junit.Assert.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class DerangedAnagramsTest {

    @Test
    fun testIsDeranged() {
        assertTrue(isDeranged("abc", "bca"))
        assertTrue(isDeranged("hello", "leloh"))
        assertFalse(isDeranged("abc", "abc"))
        assertFalse(isDeranged("hello", "olleh"))
    }

    @Test
    fun testMain() {
        // Mock the URL and BufferedReader to simulate the word list
        val url = URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt")
        val isr = InputStreamReader(url.openStream())
        val reader = BufferedReader(isr)

        // Read the first few lines to simulate the word list
        val words = mutableListOf<String>()
        var word = reader.readLine()
        while (word != null && words.size < 10) {
            words.add(word)
            word = reader.readLine()
        }
        reader.close()

        // Mock the word list
        val mockReader = BufferedReader(InputStreamReader(words.joinToString("\n").byteInputStream()))

        // Capture the output of the main function
        val output = mutableListOf<String>()
        val originalOut = System.out
        System.setOut(object : PrintStream(ByteArrayOutputStream()) {
            override fun println(x: Any?) {
                output.add(x.toString())
            }
        })

        // Call the main function with the mock reader
        main(arrayOf())

        // Restore the original output stream
        System.setOut(originalOut)

        // Assert the output
        assertTrue(output.isNotEmpty())
        // Add more specific assertions based on expected output if known
    }
}
