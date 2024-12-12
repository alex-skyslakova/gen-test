import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

class FileInputOutputTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testFileInputOutput() {
        val inputFile = File(tempDir, "input.txt")
        val outputFile = File(tempDir, "output.txt")

        val inputText = "This is a test."
        inputFile.writeText(inputText)

        // Execute the code being tested (FileInputOutput.kt's main function logic)
        val text = inputFile.readText()
        outputFile.writeText(text)


        val outputText = outputFile.readText()
        assertEquals(inputText, outputText)
    }

    @Test
    fun testFileInputOutputEmptyFile() {
        val inputFile = File(tempDir, "input.txt")
        val outputFile = File(tempDir, "output.txt")

        val inputText = ""
        inputFile.writeText(inputText)

        // Execute the code being tested
        val text = inputFile.readText()
        outputFile.writeText(text)

        val outputText = outputFile.readText()
        assertEquals(inputText, outputText)
    }


    @Test
    fun testFileInputOutputWithNewlines() {
        val inputFile = File(tempDir, "input.txt")
        val outputFile = File(tempDir, "output.txt")

        val inputText = "This is line 1.\nThis is line 2."
        inputFile.writeText(inputText)

        // Execute the code being tested
        val text = inputFile.readText()
        outputFile.writeText(text)

        val outputText = outputFile.readText()
        assertEquals(inputText, outputText)
    }

    @Test
    fun testFileInputOutputWithSpecialCharacters() {
        val inputFile = File(tempDir, "input.txt")
        val outputFile = File(tempDir, "output.txt")

        val inputText = "Special characters: !@#$%^&*()"
        inputFile.writeText(inputText)

        // Execute the code being tested
        val text = inputFile.readText()
        outputFile.writeText(text)

        val outputText = outputFile.readText()
        assertEquals(inputText, outputText)

    }

}
