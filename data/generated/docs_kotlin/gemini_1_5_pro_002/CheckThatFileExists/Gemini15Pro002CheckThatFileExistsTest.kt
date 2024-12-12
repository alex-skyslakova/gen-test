import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Paths
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class FileExistenceTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testCurrentDirectory() {
        // Create dummy files and directory in the temporary directory
        File(tempDir, "input.txt").createNewFile()
        File(tempDir, "docs").mkdir()
        File(tempDir, "zero_length.txt").createNewFile()
        File(tempDir, "`Abdu'l-Bahá.txt").createNewFile()



        assertTrue(File(tempDir, "input.txt").exists() && !File(tempDir, "input.txt").isDirectory)
        assertTrue(File(tempDir, "docs").exists() && File(tempDir, "docs").isDirectory)
        assertTrue(File(tempDir, "zero_length.txt").exists() && !File(tempDir,"zero_length.txt").isDirectory)
        assertTrue(File(tempDir, "`Abdu'l-Bahá.txt").exists() && !File(tempDir,"`Abdu'l-Bahá.txt").isDirectory)

        //Clean Up
        File(tempDir, "input.txt").delete()
        File(tempDir, "docs").delete()
         File(tempDir, "zero_length.txt").delete()
        File(tempDir, "`Abdu'l-Bahá.txt").delete()

    }


    @Test
    fun testRootDirectory() {
         val rootPath = Paths.get("").toAbsolutePath().root.toString().replace("\\", "/")

        // Assuming root access for this test - adjust for your testing environment if necessary
        // These assertions check for the existence of "input.txt" and "docs" at the root.
        // You may want to modify or skip this test if you don't have the necessary permissions.


        // For testing purposes, create the files if they don't exist and delete after
        val rootInputFile = File(rootPath, "input.txt")
        val rootDocsDir = File(rootPath, "docs")
        val rootZeroLengthFile = File(rootPath, "zero_length.txt")
        val rootUnusualFileName = File(rootPath, "`Abdu'l-Bahá.txt")



        if(!rootInputFile.exists()){
            rootInputFile.createNewFile()
        }

        if(!rootDocsDir.exists()){
            rootDocsDir.mkdir()
        }

         if(!rootZeroLengthFile.exists()){
            rootZeroLengthFile.createNewFile()
        }

          if(!rootUnusualFileName.exists()){
            rootUnusualFileName.createNewFile()
        }




        //Assertions
      assertTrue(rootInputFile.exists() && !rootInputFile.isDirectory)
        assertTrue(rootDocsDir.exists() && rootDocsDir.isDirectory)
        assertTrue(rootZeroLengthFile.exists() && !rootZeroLengthFile.isDirectory)
        assertTrue(rootUnusualFileName.exists() && !rootUnusualFileName.isDirectory)




        //Clean up: Comment out or remove if you don't want these files deleted.
       rootInputFile.delete()
        rootDocsDir.delete()
        rootZeroLengthFile.delete()
        rootUnusualFileName.delete()

     assertFalse(rootInputFile.exists())
        assertFalse(rootDocsDir.exists())


    }
}




