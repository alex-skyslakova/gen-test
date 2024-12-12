import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class CsvDataManipulationTest {

    private val inputFileName = "example.csv"
    private val outputFileName = "example2.csv"

    @BeforeEach
    fun setUp() {
        // Create a sample CSV file for testing
        val csvContent = """
            C1,C2,C3,C4,C5
            1,5,9,13,17
            2,6,10,14,18
            3,7,11,15,19
            4,8,12,16,20
        """.trimIndent()
        File(inputFileName).writeText(csvContent)
    }

    @Test
    fun testCsvDataManipulation() {
        // Run the main function to manipulate the CSV
        main(arrayOf())

        // Read the output file
        val expectedOutput = """
            C1,C2,C3,C4,C5,SUM
            1,5,9,13,17,45
            2,6,10,14,18,50
            3,7,11,15,19,55
            4,8,12,16,20,60
        """.trimIndent()
        val actualOutput = File(outputFileName).readText().trim()

        // Assert that the output matches the expected result
        assertEquals(expectedOutput, actualOutput)
    }
}
