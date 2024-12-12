import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito.*
import java.io.File

class CreateAFileTest {

    private lateinit var fileMock: File
    private lateinit var filePaths: Array<String>
    private lateinit var dirPaths: Array<String>

    @BeforeEach
    fun setUp() {
        fileMock = mock(File::class.java)
        filePaths = arrayOf("output.txt", "c:\\output.txt")
        dirPaths = arrayOf("docs", "c:\\docs")
    }

    @Test
    fun testFileCreationSuccess() {
        for (path in filePaths) {
            `when`(fileMock.createNewFile()).thenReturn(true)
            `when`(fileMock.toString()).thenReturn(path)

            val result = fileMock.createNewFile()
            assertTrue(result)
            verify(fileMock).createNewFile()
            verify(fileMock).toString()
        }
    }

    @Test
    fun testFileCreationFailure() {
        for (path in filePaths) {
            `when`(fileMock.createNewFile()).thenReturn(false)
            `when`(fileMock.toString()).thenReturn(path)

            val result = fileMock.createNewFile()
            assertFalse(result)
            verify(fileMock).createNewFile()
            verify(fileMock).toString()
        }
    }

    @Test
    fun testDirectoryCreationSuccess() {
        for (path in dirPaths) {
            `when`(fileMock.mkdir()).thenReturn(true)
            `when`(fileMock.toString()).thenReturn(path)

            val result = fileMock.mkdir()
            assertTrue(result)
            verify(fileMock).mkdir()
            verify(fileMock).toString()
        }
    }

    @Test
    fun testDirectoryCreationFailure() {
        for (path in dirPaths) {
            `when`(fileMock.mkdir()).thenReturn(false)
            `when`(fileMock.toString()).thenReturn(path)

            val result = fileMock.mkdir()
            assertFalse(result)
            verify(fileMock).mkdir()
            verify(fileMock).toString()
        }
    }
}
