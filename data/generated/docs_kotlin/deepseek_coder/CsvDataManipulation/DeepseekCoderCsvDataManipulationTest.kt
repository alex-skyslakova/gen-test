import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class CsvDataManipulationTest {

    @Test
    fun testCsvDataManipulation() {
        // Prepare the input file
        val inputFile = File("example.csv")
        inputFile.writeText("C1,C2,C3,C4,C5\n1,5,9,13,17\n2,6,10,14,18\n3,7,11,15,19\n4,8,12,16,20")

        // Run the main function
        main(arrayOf())

        // Read the output file
        val outputFile = File("example2.csv")
        val outputLines = outputFile.readLines()

        // Expected output
        val expectedLines = listOf(
            "C1,C2,C3,C4,C5,SUM",
            "1,5,9,13,17,45",
            "2,6,10,14,18,50",
            "3,7,11,15,19,55",
            "4,8,12,16,20,60"
        )

        // Assert the output matches the expected result
        assertEquals(expectedLines, outputLines)

        // Clean up the files
        inputFile.delete()
        outputFile.delete()
    }
}
