import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import java.io.PrintWriter
import kotlin.test.assertEquals

class FastaFormatTest {

    private val originalOut = System.out
    private val baos = ByteArrayOutputStream()
    private val ps = PrintStream(baos)

    @BeforeEach
    fun setUp() {
        System.setOut(ps)
    }

    @AfterEach
    fun tearDown() {
        System.setOut(originalOut)
        File("input.fasta").delete()
    }

    @Test
    fun testSingleSequence() {
        val input = ">Rosetta_Example_1\nTHERECANBENOSPACE"
        File("input.fasta").writeText(input)
        main(arrayOf())
        assertEquals("Rosetta_Example_1: THERECANBENOSPACE\n", baos.toString())
    }

    @Test
    fun testMultipleSequences() {
        val input = ">Rosetta_Example_1\nTHERECANBENOSPACE\n>Rosetta_Example_2\nTHERECANBESEVERAL\nLINESBUTTHEYALLMUST\nBECONCATENATED"
        File("input.fasta").writeText(input)
        main(arrayOf())
        assertEquals("Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n", baos.toString())
    }

    @Test
    fun testNoStartingGreaterThan() {
        val input = "THERECANBENOSPACE"
        File("input.fasta").writeText(input)
        main(arrayOf())
        assertEquals("Error : File does not begin with '>'\n", baos.toString())
    }


    @Test
    fun testSequenceWithSpaces() {
        val input = ">Rosetta_Example_1\nTHERE CAN BE NO SPACE"
        File("input.fasta").writeText(input)
        main(arrayOf())
        assertEquals("Rosetta_Example_1: \nError : Sequence contains space(s)\n", baos.toString())
    }

    @Test
    fun testSequenceWithTabs() {
        val input = ">Rosetta_Example_1\nTHERE\tCAN\tBE\tNO\tTABS"
        File("input.fasta").writeText(input)
        main(arrayOf())
        assertEquals("Rosetta_Example_1: \nError : Sequence contains space(s)\n", baos.toString())
    }


    @Test
    fun testEmptyFile() {
        File("input.fasta").writeText("")
        main(arrayOf())
        assertEquals("", baos.toString())
    }

    @Test
    fun testOnlyGreaterThan() {
        File("input.fasta").writeText(">")
        main(arrayOf())
        assertEquals(": \n", baos.toString())
    }

    @Test
    fun testGreaterThanAndEmptyLine() {
        File("input.fasta").writeText(">\n")
        main(arrayOf())
        assertEquals(": \n", baos.toString())
    }

        @Test
    fun testMultipleEmptyLinesBetweenSequences() {
        val input = ">Seq1\nACGT\n\n>Seq2\nCAGT"
        File("input.fasta").writeText(input)
        main(arrayOf())
        assertEquals("Seq1: ACGT\nSeq2: CAGT\n", baos.toString())
    }

}
