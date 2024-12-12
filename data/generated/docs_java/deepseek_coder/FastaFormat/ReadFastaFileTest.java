import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFastaFileTest {

    @Test
    public void testReadFastaFile() throws FileNotFoundException {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a temporary test file
        File tempFile = File.createTempFile("test", ".fasta");
        try (PrintStream ps = new PrintStream(tempFile)) {
            ps.println(">Rosetta_Example_1");
            ps.println("THERECANBENOSPACE");
            ps.println(">Rosetta_Example_2");
            ps.println("THERECANBESEVERAL");
            ps.println("LINESBUTTHEYALLMUST");
            ps.println("BECONCATENATED");
        }

        // Run the main method with the test file
        ReadFastaFile.main(new String[]{tempFile.getAbsolutePath()});

        // Capture the output
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\n" +
                                "Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED";

        // Assert the output
        assertEquals(expectedOutput, output);

        // Clean up the temporary file
        tempFile.delete();
    }

    @Test
    public void testReadFastaFileWithEmptyLines() throws FileNotFoundException {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a temporary test file with empty lines
        File tempFile = File.createTempFile("test", ".fasta");
        try (PrintStream ps = new PrintStream(tempFile)) {
            ps.println(">Rosetta_Example_1");
            ps.println("THERECANBENOSPACE");
            ps.println();
            ps.println(">Rosetta_Example_2");
            ps.println("THERECANBESEVERAL");
            ps.println();
            ps.println("LINESBUTTHEYALLMUST");
            ps.println("BECONCATENATED");
        }

        // Run the main method with the test file
        ReadFastaFile.main(new String[]{tempFile.getAbsolutePath()});

        // Capture the output
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = "Rosetta_Example_1: THERECANBENOSPACE\n" +
                                "Rosetta_Example_2: THERECANBESEVERALLINESBUTTHEYALLMUSTBECONCATENATED";

        // Assert the output
        assertEquals(expectedOutput, output);

        // Clean up the temporary file
        tempFile.delete();
    }

    @Test
    public void testReadFastaFileWithNoData() throws FileNotFoundException {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a temporary test file with no data
        File tempFile = File.createTempFile("test", ".fasta");
        try (PrintStream ps = new PrintStream(tempFile)) {
            ps.println(">Rosetta_Example_1");
            ps.println(">Rosetta_Example_2");
        }

        // Run the main method with the test file
        ReadFastaFile.main(new String[]{tempFile.getAbsolutePath()});

        // Capture the output
        String output = outputStream.toString().trim();

        // Expected output
        String expectedOutput = "Rosetta_Example_1: \n" +
                                "Rosetta_Example_2: ";

        // Assert the output
        assertEquals(expectedOutput, output);

        // Clean up the temporary file
        tempFile.delete();
    }
}
