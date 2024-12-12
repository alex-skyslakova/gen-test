import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.net.URL
import kotlin.test.assertEquals

class HttpKtTest {

    private val originalOut = System.out
    private val outContent = ByteArrayOutputStream()

    @Before
    fun setUp() {
        System.setOut(PrintStream(outContent))
        mockkStatic(URL::class)
    }

    @After
    fun tearDown() {
        System.setOut(originalOut)
        unmockkAll()
    }

    @Test
    fun `test URL content is printed to console`() {
        val mockContent = "line1\nline2\nline3"
        val mockInputStream = ByteArrayInputStream(mockContent.toByteArray())

        every { URL("http://www.puzzlers.org/pub/wordlists/unixdict.txt").openStream() } returns mockInputStream

        main(arrayOf())

        assertEquals("line1\nline2\nline3\n", outContent.toString())
    }
}
