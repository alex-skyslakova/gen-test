import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import kotlin.test.assertEquals

class CsvDataManipulationTest {

    @TempDir
    lateinit var tempDir: File

    @Test
    fun testCsvManipulation() {
        val inputFile = File(tempDir, "example.csv")
        inputFile.writeText("C1,C2,C3,C4,C5\n1,5,9,13,17\n2,6,10,14,18\n3,7,11,15,19\n4,8,12,16,20")

        // Call the main function with a dummy args array.  We are testing file operations, not command line args.
        main(arrayOf())

        val outputFile = File(tempDir, "example2.csv")
        val expectedOutput = "C1,C2,C3,C4,C5,SUM\n1,5,9,13,17,55\n2,6,10,14,18,50\n3,7,11,15,19,55\n4,8,12,16,20,56"
        assertEquals(expectedOutput, outputFile.readText())
    }


    @Test
    fun testCsvManipulationEmptyFile() {
        val inputFile = File(tempDir, "example.csv")
        inputFile.writeText("") // Empty file

         main(arrayOf())


        val outputFile = File(tempDir, "example2.csv")
        // Expecting an empty output file because there are no rows to process
        assertEquals("", outputFile.readText())
    }

    @Test
    fun testCsvManipulationSingleHeaderRow() {
        val inputFile = File(tempDir, "example.csv")
        inputFile.writeText("C1,C2,C3,C4,C5") // Only header row


        main(arrayOf())

        val outputFile = File(tempDir, "example2.csv")
        val expectedOutput = "C1,C2,C3,C4,C5,SUM"
        assertEquals(expectedOutput, outputFile.readText())
    }



    @Test
    fun testCsvManipulationNonNumericData() {
        val inputFile = File(tempDir, "example.csv")
        inputFile.writeText("C1,C2,C3,C4,C5\n1,5,A,13,17\n2,6,10,14,18")


        main(arrayOf())

        val outputFile = File(tempDir, "example2.csv")

        //  We expect NumberFormatException to be caught and the program to continue. The affected row will have incomplete SUM calculation.
        val expectedOutput = "C1,C2,C3,C4,C5,SUM\n1,5,A,13,17,36\n2,6,10,14,18,50" // 36 because 'A' gets treated as 0 in the sum.

        assertEquals(expectedOutput, outputFile.readText())

    }

}
