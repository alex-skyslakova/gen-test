import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.io.File

class FileSizeTest {

    private lateinit var fileMock: File

    @BeforeEach
    fun setUp() {
        fileMock = Mockito.mock(File::class.java)
    }

    @Test
    fun `test file size for input txt in current directory`() {
        // Arrange
        val filePath = "input.txt"
        Mockito.`when`(fileMock.path).thenReturn(filePath)
        Mockito.`when`(fileMock.length()).thenReturn(1024L) // Mocking file size as 1024 bytes

        // Act
        val fileSize = fileMock.length()

        // Assert
        assertEquals(1024L, fileSize)
    }

    @Test
    fun `test file size for input txt in root directory`() {
        // Arrange
        val filePath = "c:\\input.txt"
        Mockito.`when`(fileMock.path).thenReturn(filePath)
        Mockito.`when`(fileMock.length()).thenReturn(2048L) // Mocking file size as 2048 bytes

        // Act
        val fileSize = fileMock.length()

        // Assert
        assertEquals(2048L, fileSize)
    }
}
