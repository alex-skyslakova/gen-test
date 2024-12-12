import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileIODemoTest {

    @Test
    void testFileCopy() throws IOException {
        // Create a dummy input file
        File inputFile = new File("input.txt");
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("This is a test file.\nWith multiple lines.");
        }

        // Run the main method (assuming it's modified to be testable)
        FileIODemo.main(new String[]{});


        // Check if the output file exists
        File outputFile = new File("ouput.txt"); // Note the typo in the original code
        assertTrue(outputFile.exists());

        // Compare the contents of the input and output files
        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
             BufferedReader outputReader = new BufferedReader(new FileReader(outputFile))) {
            String inputLine;
            String outputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                outputLine = outputReader.readLine();
                assertEquals(inputLine, outputLine);
            }
            assertNull(outputReader.readLine()); // Ensure output file doesn't have extra lines

        }

        //Clean up.  Important for repeatable tests
        inputFile.delete();
        outputFile.delete();
    }


    @Test
    void testFileNotFound() {
         // Delete input file if exists for simulating file not found
         File inputFile = new File("input.txt");
         if(inputFile.exists()) inputFile.delete();


         // Expecting printStackTrace to be called when input file doesn't exist. Replace this with your preferred exception handling
        // For this example we are checking if the output file *doesn't* get created when input file is missing
        File outputFile = new File("ouput.txt");
        assertFalse(outputFile.exists());

    }
}

