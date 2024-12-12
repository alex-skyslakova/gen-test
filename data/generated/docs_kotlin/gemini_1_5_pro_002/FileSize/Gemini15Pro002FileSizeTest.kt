import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileSizeTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testFileSize() {
        // Create a test file in the temporary directory
        val tempFile = File(tempDir, "input.txt")
        val testContent = "This is a test file."
        tempFile.writeText(testContent)

        // Test file size in current directory (temp directory)
        assertEquals(testContent.length.toLong(), File("input.txt").length())


        // Test file size in a different directory (assuming C drive exists; adjust if not)
        val anotherDir = File("c:/") 
        if (anotherDir.exists() && anotherDir.isDirectory) {  // Safely check if directory exists
            val anotherFile = File("c:/input.txt")

            if(!anotherFile.exists()) {
                anotherFile.createNewFile()
                anotherFile.writeText(testContent) // Write same content for consistent test

                assertEquals(testContent.length.toLong(), anotherFile.length())
                anotherFile.delete() // Cleanup
            } else {
                val originalSize = anotherFile.length() // Get existing size to restore later
                anotherFile.writeText(testContent)

                assertEquals(testContent.length.toLong(), anotherFile.length())
                
                // Restore original content for minimal test impact on the real system
                if (originalSize == 0L) {
                    anotherFile.delete() // Restore to no file if it was originally empty
                } else {
                    val originalContent = ByteArray(originalSize.toInt())
                    val inputStream = anotherFile.inputStream()
                    inputStream.read(originalContent) 
                    inputStream.close()
                    anotherFile.writeBytes(originalContent) // Restore original content
                }
            }

        } else {
            assertTrue(false, "Could not find C: drive for testing. Adapt test for your environment.") // or simply println a warning and skip if C drive testing isn't vital
        }


    }


}
