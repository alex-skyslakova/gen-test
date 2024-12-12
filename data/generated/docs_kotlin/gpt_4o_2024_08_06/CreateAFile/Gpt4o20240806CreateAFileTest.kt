import org.junit.jupiter.api.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CreateAFileTest {

    private val currentDirFilePath = "output.txt"
    private val currentDirDirPath = "docs"
    private val rootDirFilePath = "c:\\output.txt"
    private val rootDirDirPath = "c:\\docs"

    @BeforeEach
    fun setup() {
        // Clean up before each test
        File(currentDirFilePath).delete()
        File(currentDirDirPath).delete()
        File(rootDirFilePath).delete()
        File(rootDirDirPath).delete()
    }

    @Test
    fun `test file creation in current directory`() {
        val file = File(currentDirFilePath)
        Assertions.assertFalse(file.exists(), "File should not exist before creation")

        file.createNewFile()
        Assertions.assertTrue(file.exists(), "File should exist after creation")
        Assertions.assertEquals(0, file.length(), "File should be of size 0 bytes")
    }

    @Test
    fun `test directory creation in current directory`() {
        val dir = File(currentDirDirPath)
        Assertions.assertFalse(dir.exists(), "Directory should not exist before creation")

        dir.mkdir()
        Assertions.assertTrue(dir.exists(), "Directory should exist after creation")
        Assertions.assertTrue(dir.isDirectory, "Path should be a directory")
    }

    @Test
    fun `test file creation in root directory`() {
        // This test requires administrative privileges on Windows
        val file = File(rootDirFilePath)
        Assertions.assertFalse(file.exists(), "File should not exist before creation")

        file.createNewFile()
        Assertions.assertTrue(file.exists(), "File should exist after creation")
        Assertions.assertEquals(0, file.length(), "File should be of size 0 bytes")
    }

    @Test
    fun `test directory creation in root directory`() {
        // This test requires administrative privileges on Windows
        val dir = File(rootDirDirPath)
        Assertions.assertFalse(dir.exists(), "Directory should not exist before creation")

        dir.mkdir()
        Assertions.assertTrue(dir.exists(), "Directory should exist after creation")
        Assertions.assertTrue(dir.isDirectory, "Path should be a directory")
    }

    @AfterEach
    fun cleanup() {
        // Clean up after each test
        File(currentDirFilePath).delete()
        File(currentDirDirPath).delete()
        File(rootDirFilePath).delete()
        File(rootDirDirPath).delete()
    }
}
