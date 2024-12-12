import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.regex.Pattern

@Test
fun testHtmlTableGeneration() {
    val outContent = ByteArrayOutputStream()
    val originalOut = System.out
    System.setOut(PrintStream(outContent))

    main(arrayOf())

    System.setOut(originalOut)

    val output = outContent.toString()

    assertTrue(output.contains("<html>"))
    assertTrue(output.contains("<head>"))
    assertTrue(output.contains("<style>"))
    assertTrue(output.contains("table, th, td  { border: 1px solid black; }"))
    assertTrue(output.contains("th, td { text-align: right; }"))
    assertTrue(output.contains("</style>"))
    assertTrue(output.contains("</head>"))
    assertTrue(output.contains("<body>"))
    assertTrue(output.contains("<table style=\"width:60%\">"))
    assertTrue(output.contains("<thead>"))
    assertTrue(output.contains("<tr><th></th><th>X</th><th>Y</th><th>Z</th></tr>"))
    assertTrue(output.contains("</thead>"))
    assertTrue(output.contains("<tbody>"))

    val rowPattern = Pattern.compile("<tr><td>(\\d+)</td><td>(\\d+)</td><td>(\\d+)</td><td>(\\d+)</td></tr>")
    val matcher = rowPattern.matcher(output)

    var rowCount = 0
    while (matcher.find()) {
        rowCount++
        val rowNum = matcher.group(1).toInt()
        val x = matcher.group(2).toInt()
        val y = matcher.group(3).toInt()
        val z = matcher.group(4).toInt()

        assertTrue(x in 0..9999)
        assertTrue(y in 0..9999)
        assertTrue(z in 0..9999)
    }

    assertEquals(4, rowCount)


    assertTrue(output.contains("</tbody>"))
    assertTrue(output.contains("</table>"))
    assertTrue(output.contains("</body>"))
    assertTrue(output.contains("</html>"))
}

