import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FileSizeTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun `test file size in current working directory`() {
        // Arrange
        val fileName = "input.txt"
        val fileContent = "Test content"
        val expectedSize = fileContent.toByteArray().size.toLong()

        val file = File(fileName)
        file.writeText(fileContent)

        // Act
        val actualSize = File(fileName).length()

        // Assert
        assertEquals(expectedSize, actualSize, "File size should match the content size")
    }

    @Test
    fun `test file size in file system root`() {
        // Arrange
        val fileName = "c:\\input.txt"
        val fileContent = "Test content"
        val expectedSize = fileContent.toByteArray().size.toLong()

        val file = File(fileName)
        file.writeText(fileContent)

        // Act
        val actualSize = File(fileName).length()

        // Assert
        assertEquals(expectedSize, actualSize, "File size should match the content size")
    }

    @Test
    fun `test non-existent file in current working directory`() {
        // Arrange
        val fileName = "non_existent_file.txt"

        // Act & Assert
        assertFailsWith<FileNotFoundException> {
            File(fileName).length()
        }
    }

    @Test
    fun `test non-existent file in file system root`() {
        // Arrange
        val fileName = "c:\\non_existent_file.txt"

        // Act & Assert
        assertFailsWith<FileNotFoundException> {
            File(fileName).length()
        }
    }

    @Test
    fun `test empty file in current working directory`() {
        // Arrange
        val fileName = "empty_file.txt"
        val expectedSize = 0L

        val file = File(fileName)
        file.createNewFile()

        // Act
        val actualSize = File(fileName).length()

        // Assert
        assertEquals(expectedSize, actualSize, "Empty file should have size 0")
    }

    @Test
    fun `test empty file in file system root`() {
        // Arrange
        val fileName = "c:\\empty_file.txt"
        val expectedSize = 0L

        val file = File(fileName)
        file.createNewFile()

        // Act
        val actualSize = File(fileName).length()

        // Assert
        assertEquals(expectedSize, actualSize, "Empty file should have size 0")
    }
}
