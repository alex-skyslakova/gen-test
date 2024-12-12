import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.*
import kotlin.test.assertEquals
import org.mockito.Mockito.*
import java.net.URLConnection


class HttpTest {

    @Test
    fun testMain() {
        val mockUrl = mock(URL::class.java)
        val mockURLConnection = mock(URLConnection::class.java)
        `when`(mockUrl.openConnection()).thenReturn(mockURLConnection)

        val testData = "line1\nline2\nline3"
        val inputStream = ByteArrayInputStream(testData.toByteArray())
        `when`(mockURLConnection.getInputStream()).thenReturn(inputStream)


        val originalURL = URL::class.java.getDeclaredField("url")
        originalURL.isAccessible = true
        originalURL.set(mockUrl, "http://test.com")



        val expectedOutput = testData.lines().joinToString(System.lineSeparator())

        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))


        val mainMethod = ::main
        val args = arrayOf<String>()

        val kClass = Class.forName("HttpKt").kotlin
        val mainFun = kClass.members.find { it.name == "main" }


        mainFun?.call(mockUrl, args )

        assertEquals(expectedOutput,  outContent.toString())


        System.setOut(System.out)


    }





}
