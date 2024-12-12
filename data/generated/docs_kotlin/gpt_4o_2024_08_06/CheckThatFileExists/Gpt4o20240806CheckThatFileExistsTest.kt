import java.io.File
import kotlin.test.*

class CheckThatFileExistsTest {

    @Test
    fun testFileExistsInCurrentDirectory() {
        val file = File("input.txt")
        assertTrue(file.exists() && !file.isDirectory, "input.txt should exist in the current directory and be a file")
    }

    @Test
    fun testDirectoryExistsInCurrentDirectory() {
        val dir = File("docs")
        assertTrue(dir.exists() && dir.isDirectory, "docs should exist in the current directory and be a directory")
    }

    @Test
    fun testFileExistsInRootDirectory() {
        val file = File("/input.txt")
        assertTrue(file.exists() && !file.isDirectory, "/input.txt should exist in the root directory and be a file")
    }

    @Test
    fun testDirectoryExistsInRootDirectory() {
        val dir = File("/docs")
        assertTrue(dir.exists() && dir.isDirectory, "/docs should exist in the root directory and be a directory")
    }

    @Test
    fun testZeroLengthFileExists() {
        val file = File("zero_length.txt")
        assertTrue(file.exists() && !file.isDirectory, "zero_length.txt should exist and be a file")
        assertEquals(0, file.length(), "zero_length.txt should be zero-length")
    }

    @Test
    fun testUnusualFilenameExists() {
        val file = File("`Abdu'l-Bahá.txt")
        assertTrue(file.exists() && !file.isDirectory, "`Abdu'l-Bahá.txt should exist and be a file")
    }
}
