import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

class CreateAnHtmlTableTest {

    @Test
    fun `test HTML structure`() {
        val output = captureOutput { main(arrayOf()) }
        assertTrue(output.contains("<html>"), "Output should contain <html> tag")
        assertTrue(output.contains("</html>"), "Output should contain </html> tag")
        assertTrue(output.contains("<head>"), "Output should contain <head> tag")
        assertTrue(output.contains("</head>"), "Output should contain </head> tag")
        assertTrue(output.contains("<body>"), "Output should contain <body> tag")
        assertTrue(output.contains("</body>"), "Output should contain </body> tag")
    }

    @Test
    fun `test table structure`() {
        val output = captureOutput { main(arrayOf()) }
        assertTrue(output.contains("<table"), "Output should contain <table> tag")
        assertTrue(output.contains("</table>"), "Output should contain </table> tag")
        assertTrue(output.contains("<thead>"), "Output should contain <thead> tag")
        assertTrue(output.contains("</thead>"), "Output should contain </thead> tag")
        assertTrue(output.contains("<tbody>"), "Output should contain <tbody> tag")
        assertTrue(output.contains("</tbody>"), "Output should contain </tbody> tag")
    }

    @Test
    fun `test table headers`() {
        val output = captureOutput { main(arrayOf()) }
        assertTrue(output.contains("<th></th>"), "Output should contain an empty header for row numbers")
        assertTrue(output.contains("<th>X</th>"), "Output should contain <th>X</th>")
        assertTrue(output.contains("<th>Y</th>"), "Output should contain <th>Y</th>")
        assertTrue(output.contains("<th>Z</th>"), "Output should contain <th>Z</th>")
    }

    @Test
    fun `test table rows and data`() {
        val output = captureOutput { main(arrayOf()) }
        val pattern = Pattern.compile("<tr><td>\\d+</td><td>\\d{1,4}</td><td>\\d{1,4}</td><td>\\d{1,4}</td></tr>")
        val matcher = pattern.matcher(output)
        var rowCount = 0
        while (matcher.find()) {
            rowCount++
        }
        assertTrue(rowCount == 4, "There should be 4 rows of data in the table")
    }

    private fun captureOutput(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))
        try {
            block()
        } finally {
            System.setOut(originalOut)
        }
        return outputStream.toString()
    }
}
