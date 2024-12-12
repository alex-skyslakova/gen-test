import org.junit.jupiter.api.Test;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class ReadFastaFileTest {

    @Test
    public void testSingleLineSequence() throws IOException {
        String fastaContent = ">Rosetta_Example_1\nTHERECANBENOSPACE\n";
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\n";
        assertEquals(expectedOutput, runFastaReader(fastaContent));
    }

    @Test
    public void testMultiLineSequence() throws IOException {
        String fastaContent = ">Rosetta_Example_2\nTHERECANBESEVERAL\nLINESBUTTHEYALLMUST\nBECONCATENATED\n";
        String expectedOutput = "Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n";
        assertEquals(expectedOutput, runFastaReader(fastaContent));
    }

    @Test
    public void testMultipleSequences() throws IOException {
        String fastaContent = ">Rosetta_Example_1\nTHERECANBENOSPACE\n>Rosetta_Example_2\nTHERECANBESEVERAL\nLINESBUTTHEYALLMUST\nBECONCATENATED\n";
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n";
        assertEquals(expectedOutput, runFastaReader(fastaContent));
    }

    @Test
    public void testEmptyFile() throws IOException {
        String fastaContent = "";
        String expectedOutput = "\n";
        assertEquals(expectedOutput, runFastaReader(fastaContent));
    }

    @Test
    public void testFileWithOnlyHeader() throws IOException {
        String fastaContent = ">Rosetta_Example_3\n";
        String expectedOutput = "Rosetta_Example_3: \n";
        assertEquals(expectedOutput, runFastaReader(fastaContent));
    }

    private String runFastaReader(String fastaContent) throws IOException {
        File tempFile = File.createTempFile("test", ".fasta");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(fastaContent);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            ReadFastaFile.main(new String[]{tempFile.getAbsolutePath()});
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }
}
