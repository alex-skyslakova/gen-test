import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFastaFileTest {

    @Test
    void testSingleEntry() throws FileNotFoundException {
        String input = ">Rosetta_Example_1\nTHERECANBENOSPACE";
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\n";
        test(input, expectedOutput);
    }

    @Test
    void testMultipleEntries() throws FileNotFoundException {
        String input = ">Rosetta_Example_1\nTHERECANBENOSPACE\n>Rosetta_Example_2\nTHERECANBESEVERAL\nLINESBUTTHEYALLMUST\nBECONCATENATED";
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n";
        test(input, expectedOutput);
    }

    @Test
    void testEmptyLines() throws FileNotFoundException {
        String input = ">Rosetta_Example_1\n\nTHERECANBENOSPACE\n>Rosetta_Example_2\n\nTHERECANBESEVERAL\n\nLINESBUTTHEYALLMUST\nBECONCATENATED\n";
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED\n";

        test(input, expectedOutput);
    }



    @Test
    void testLeadingAndTrailingSpaces() throws FileNotFoundException {
        String input = ">Rosetta_Example_1\n THERECANBENOSPACE \n>Rosetta_Example_2\n THERECANBESEVERAL \n LINESBUTTHEYALLMUST \n BECONCATENATED ";
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\nRosetta_Example_2: THERECANBESEVERALLLINESBUTTHEYALLMUSTBECONCATENATED\n";
        test(input, expectedOutput);

    }


    @Test
    void testNoDataAfterHeader() throws FileNotFoundException {
         String input = ">Rosetta_Example_1\n>Rosetta_Example_2";
         String expectedOutput = "Rosetta_Example_1: \nRosetta_Example_2: \n";
         test(input, expectedOutput);
    }

    @Test
    void testEmptyInput() throws FileNotFoundException {
        String input = "";
        String expectedOutput = "\n";
        test(input, expectedOutput);
    }

    private void test(String input, String expectedOutput) throws FileNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        ReadFastaFile.main(new String[0]);

        assertEquals(expectedOutput, out.toString(StandardCharsets.UTF_8));

        // Reset System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

    }
}
