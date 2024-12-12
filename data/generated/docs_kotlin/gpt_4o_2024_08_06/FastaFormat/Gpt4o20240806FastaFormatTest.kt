import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File
import java.io.PrintWriter

class FastaFormatTest {

    @Test
    fun testValidFastaFileSingleLine() {
        val inputFile = File("test_input.fasta")
        inputFile.writeText(">Rosetta_Example_1\nTHERECANBENOSPACE")

        val expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\n"
        assertEquals(expectedOutput, runFastaFormat(inputFile))
    }

    @Test
    fun testValidFastaFileMultipleLines() {
        val inputFile = File("test_input.fasta")
        inputFile.writeText(">Rosetta_Example_2\nTHERECANBESEVERAL\nLINESBUTTHEYALLMUST\nBECONCATENATED")

        val expectedOutput = "Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n"
        assertEquals(expectedOutput, runFastaFormat(inputFile))
    }

    @Test
    fun testFileWithSpacesInSequence() {
        val inputFile = File("test_input.fasta")
        inputFile.writeText(">Rosetta_Example_3\nTHERE CAN BE SPACES")

        val expectedOutput = "Rosetta_Example_3: \nError : Sequence contains space(s)\n"
        assertEquals(expectedOutput, runFastaFormat(inputFile))
    }

    @Test
    fun testFileNotStartingWithGreaterThan() {
        val inputFile = File("test_input.fasta")
        inputFile.writeText("Rosetta_Example_4\nTHERECANBENOSPACE")

        val expectedOutput = "Error : File does not begin with '>'\n"
        assertEquals(expectedOutput, runFastaFormat(inputFile))
    }

    @Test
    fun testEmptyFile() {
        val inputFile = File("test_input.fasta")
        inputFile.writeText("")

        val expectedOutput = "Error : File does not begin with '>'\n"
        assertEquals(expectedOutput, runFastaFormat(inputFile))
    }

    private fun runFastaFormat(inputFile: File): String {
        val output = StringBuilder()
        val originalOut = System.out
        try {
            System.setOut(PrintWriter(output))
            val sc = Scanner(inputFile)
            var first = true
            while (sc.hasNextLine()) {
                val line = sc.nextLine()
                if (line.isNotEmpty() && line[0] == '>') {
                    if (!first) println()
                    print("${line.substring(1)}: ")
                    if (first) first = false
                } else if (first) {
                    println("Error : File does not begin with '>'")
                    break
                } else if (checkNoSpaces(line))
                    print(line)
                else {
                    println("\nError : Sequence contains space(s)")
                    break
                }
            }
            sc.close()
        } finally {
            System.setOut(originalOut)
        }
        return output.toString()
    }
}
