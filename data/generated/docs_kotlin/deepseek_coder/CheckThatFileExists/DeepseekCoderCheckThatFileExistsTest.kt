import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class CheckThatFileExistsTest {

    @Test
    fun testFileAndDirectoryExistenceInCurrentDirectory() {
        // Arrange
        val currentDir = System.getProperty("user.dir")
        val file = File("$currentDir/input.txt")
        val dir = File("$currentDir/docs")

        // Act & Assert
        assertTrue(file.exists() && !file.isDirectory, "input.txt should exist in the current directory")
        assertTrue(dir.exists() && dir.isDirectory, "docs directory should exist in the current directory")
    }

    @Test
    fun testFileAndDirectoryExistenceInRootDirectory() {
        // Arrange
        val rootDir = File("/")
        val file = File(rootDir, "input.txt")
        val dir = File(rootDir, "docs")

        // Act & Assert
        assertTrue(file.exists() && !file.isDirectory, "input.txt should exist in the root directory")
        assertTrue(dir.exists() && dir.isDirectory, "docs directory should exist in the root directory")
    }

    @Test
    fun testZeroLengthFileExistence() {
        // Arrange
        val currentDir = System.getProperty("user.dir")
        val zeroLengthFile = File("$currentDir/zero_length.txt")

        // Act & Assert
        assertTrue(zeroLengthFile.exists() && !zeroLengthFile.isDirectory, "zero_length.txt should exist in the current directory")
    }

    @Test
    fun testUnusualFilenameExistence() {
        // Arrange
        val currentDir = System.getProperty("user.dir")
        val unusualFile = File("$currentDir/`Abdu'l-Bahá.txt")

        // Act & Assert
        assertTrue(unusualFile.exists() && !unusualFile.isDirectory, "`Abdu'l-Bahá.txt should exist in the current directory")
    }
}
