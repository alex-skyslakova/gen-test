import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.File
import kotlin.io.path.Path
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import java.nio.file.Files


class CreateAFileTest {

    private val testFilePaths = arrayOf("output.txt", "test_output.txt") // Modified to avoid conflicts with C:\
    private val testDirPaths = arrayOf("docs", "test_docs") // Modified to avoid conflicts with C:\


    @BeforeEach
    fun setup() {
        // Delete test files and directories if they exist before each test.
        for (path in testFilePaths) {
            val f = File(path)
            f.delete()
        }
        for (path in testDirPaths) {
            val d = File(path)
            if(d.exists()) {
              d.deleteRecursively()
            }
        }

        // Ensure test root directory exists (for Windows compatibility)
        File("c:\\test_root").mkdir()

       // Create files and directories used for negative tests.
       File("c:\\test_root\\test_output.txt").createNewFile()
       File("c:\\test_root\\test_docs").mkdir()

    }

    @AfterEach
    fun cleanup() {
         // Delete test files and directories after each test.
         for (path in testFilePaths) {
             val f = File(path)
             f.delete()
         }
         for (path in testDirPaths) {
             val d = File(path)
             if(d.exists()) {
               d.deleteRecursively()
             }
         }


         File("c:\\test_root\\test_output.txt").delete()
         File("c:\\test_root\\test_docs").deleteRecursively()

         File("c:\\test_root").deleteRecursively()

    }



    @Test
    fun testCreateFileSuccess() {

        for (path in testFilePaths) {
            val f = File(path)
            assertTrue(f.createNewFile(), "File creation failed for $path")
            assertTrue(f.exists(), "File does not exist after creation: $path")
            assertTrue(f.length() == 0L, "File size is not 0 after creation: $path")
        }


         // Testing on a path which requires admin rights, so modified to a test path
         val testRootFile = File("c:\\test_root\\output.txt")
         assertTrue(testRootFile.createNewFile(), "File creation failed for test root")
         assertTrue(testRootFile.exists(), "File does not exist after creation: test root")
         assertTrue(testRootFile.length() == 0L, "File size is not 0 after creation: test root")
    }



    @Test
    fun testCreateFileAlreadyExists() {
        // Test the case where the file already exists

        for (path in testFilePaths) {
            val f = File(path)
            f.createNewFile() // create the file first

            // Creating the file when it already exists will return false
            assertFalse(f.createNewFile(), "createNewFile() should return false when file exists: $path")
        }

        assertFalse(File("c:\\test_root\\test_output.txt").createNewFile(), "createNewFile() should return false when file exists: test_output.txt")

    }




    @Test
    fun testCreateDirSuccess() {


         for (path in testDirPaths) {
            val d = File(path)
            assertTrue(d.mkdir(), "Directory creation failed for $path")
            assertTrue(d.exists(), "Directory does not exist after creation: $path")
            assertTrue(d.isDirectory(), "Is not a directory after creation: $path")
        }


        // Modified test for root to use test_root folder
        val testRootDir = File("c:\\test_root\\docs")
        assertTrue(testRootDir.mkdir(), "Directory creation failed for test root")
        assertTrue(testRootDir.exists(), "Directory does not exist after creation: test root")
        assertTrue(testRootDir.isDirectory(), "Is not a directory after creation: test root")


    }


    @Test
    fun testCreateDirAlreadyExists() {

        for (path in testDirPaths) {
            val d = File(path)
            d.mkdir()
            assertFalse(d.mkdir(), "mkdir() should return false when directory exists: $path")
        }


        assertFalse(File("c:\\test_root\\test_docs").mkdir(), "mkdir() should return false when directory exists: test_docs")



    }


}
