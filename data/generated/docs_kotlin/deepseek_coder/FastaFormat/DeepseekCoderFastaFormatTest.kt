import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals

class FastaFormatTest {

    @Test
    fun testValidFastaFile() {
        val input = """
            >Rosetta_Example_1
            THERECANBENOSPACE
            >Rosetta_Example_2
            THERECANBESEVERAL
            LINESBUTTHEYALLMUST
            BECONCATENATED
        """.trimIndent()

        val expectedOutput = """
            Rosetta_Example_1: THERECANBENOSPACE
            Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED
        """.trimIndent()

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val tempFile = Files.createTempFile("test", ".fasta").toFile()
        tempFile.writeText(input)

        main(arrayOf(tempFile.absolutePath))

        assertEquals(expectedOutput, outputStream.toString().trim())

        tempFile.delete()
    }

    @Test
    fun testFileDoesNotBeginWithGreaterThan() {
        val input = """
            THERECANBENOSPACE
            >Rosetta_Example_1
            THERECANBENOSPACE
        """.trimIndent()

        val expectedOutput = "Error : File does not begin with '>'"

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val tempFile = Files.createTempFile("test", ".fasta").toFile()
        tempFile.writeText(input)

        main(arrayOf(tempFile.absolutePath))

        assertEquals(expectedOutput, outputStream.toString().trim())

        tempFile.delete()
    }

    @Test
    fun testSequenceContainsSpaces() {
        val input = """
            >Rosetta_Example_1
            THERE CANBENOSPACE
        """.trimIndent()

        val expectedOutput = "Error : Sequence contains space(s)"

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val tempFile = Files.createTempFile("test", ".fasta").toFile()
        tempFile.writeText(input)

        main(arrayOf(tempFile.absolutePath))

        assertEquals(expectedOutput, outputStream.toString().trim())

        tempFile.delete()
    }

    @Test
    fun testMultipleSequencesWithSpaces() {
        val input = """
            >Rosetta_Example_1
            THERECANBENOSPACE
            >Rosetta_Example_2
            THERE CANBESEVERAL
        """.trimIndent()

        val expectedOutput = """
            Rosetta_Example_1: THERECANBENOSPACE
            Error : Sequence contains space(s)
        """.trimIndent()

        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        val tempFile = Files.createTempFile("test", ".fasta").toFile()
        tempFile.writeText(input)

        main(arrayOf(tempFile.absolutePath))

        assertEquals(expectedOutput, outputStream.toString().trim())

        tempFile.delete()
    }
}
