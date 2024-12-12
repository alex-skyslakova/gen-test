import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

class GloballyReplaceTextInSeveralFilesTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testReplacement() {
        val file1 = File(tempDir, "file1.txt")
        file1.writeText("Goodbye London! This is file1. Goodbye London!")
        val file2 = File(tempDir, "file2.txt")
        file2.writeText("This is file2. Goodbye London!")

        val files = arrayOf(file1.absolutePath, file2.absolutePath)

        main(files)


        assertEquals("Hello New York! This is file1. Hello New York!", file1.readText())
        assertEquals("This is file2. Hello New York!", file2.readText())
    }


    @Test
    fun testNoReplacementNeeded() {
        val file1 = File(tempDir, "file1.txt")
        file1.writeText("This is file1. No London here!")
        val file2 = File(tempDir, "file2.txt")
        file2.writeText("This is file2. Also no London!")

        val files = arrayOf(file1.absolutePath, file2.absolutePath)

        main(files)

        assertEquals("This is file1. No London here!", file1.readText())
        assertEquals("This is file2. Also no London!", file2.readText())
    }

    @Test
    fun testEmptyFile() {
        val file1 = File(tempDir, "file1.txt")
        file1.createNewFile()
        val file2 = File(tempDir, "file2.txt")
        file2.createNewFile()


        val files = arrayOf(file1.absolutePath, file2.absolutePath)

        main(files)

        assertEquals("", file1.readText())
        assertEquals("", file2.readText())
    }


    // Main function from the original code (slightly adapted for testing)
    private fun main(args: Array<String>) {
        for (file in args) {
            val f = File(file)
            var text = f.readText()
            text = text.replace("Goodbye London!", "Hello New York!")
            f.writeText(text)
        }
    }
}
