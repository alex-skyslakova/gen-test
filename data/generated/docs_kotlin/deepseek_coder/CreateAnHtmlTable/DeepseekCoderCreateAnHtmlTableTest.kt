import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CreateAnHtmlTableTest {

    @Test
    fun testHtmlTableStructure() {
        // Redirect standard output to capture the generated HTML
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        // Run the main function to generate the HTML table
        main(arrayOf())

        // Get the generated HTML content
        val htmlContent = outContent.toString()

        // Assert that the HTML structure is correct
        assertTrue(htmlContent.contains("<html>"))
        assertTrue(htmlContent.contains("</html>"))
        assertTrue(htmlContent.contains("<head>"))
        assertTrue(htmlContent.contains("</head>"))
        assertTrue(htmlContent.contains("<body>"))
        assertTrue(htmlContent.contains("</body>"))
        assertTrue(htmlContent.contains("<table"))
        assertTrue(htmlContent.contains("</table>"))
        assertTrue(htmlContent.contains("<thead>"))
        assertTrue(htmlContent.contains("</thead>"))
        assertTrue(htmlContent.contains("<tbody>"))
        assertTrue(htmlContent.contains("</tbody>"))

        // Assert that the table headers are correct
        assertTrue(htmlContent.contains("<th></th>"))
        assertTrue(htmlContent.contains("<th>X</th>"))
        assertTrue(htmlContent.contains("<th>Y</th>"))
        assertTrue(htmlContent.contains("<th>Z</th>"))

        // Assert that the table rows are correct
        val rows = htmlContent.split("<tr>")
        assertEquals(5, rows.size) // 1 for header + 4 for rows

        // Assert that each row contains 4 <td> elements
        for (i in 2..5) { // Start from 2 to skip the header row
            val row = rows[i]
            val tdCount = row.split("<td>").size - 1
            assertEquals(4, tdCount)
        }

        // Assert that the first column contains sequential row numbers
        for (i in 1..4) {
            val rowNumber = i.toString()
            assertTrue(htmlContent.contains("<td>$rowNumber</td>"))
        }

        // Assert that the "X", "Y", and "Z" columns contain 4-digit integers
        for (i in 1..4) {
            val row = rows[i + 1] // Skip the header row
            val cells = row.split("<td>")
            for (j in 2..4) { // Skip the first column (row number)
                val cellContent = cells[j].split("</td>")[0]
                val number = cellContent.toIntOrNull()
                assertNotNull(number)
                assertTrue(number!! in 0..9999)
            }
        }
    }
}
