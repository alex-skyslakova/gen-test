import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class FileIODemoTest {

    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "ouput.txt"; // Note: There's a typo in the original code, it should be "output.txt"

    @BeforeEach
    void setUp() throws IOException {
        // Create a sample input file
        try (FileWriter writer = new FileWriter(INPUT_FILE)) {
            writer.write("Hello, World!");
        }
    }

    @AfterEach
    void tearDown() {
        // Delete the files after each test
        new File(INPUT_FILE).delete();
        new File(OUTPUT_FILE).delete();
    }

    @Test
    void testFileCopy() throws IOException {
        // Run the main method of FileIODemo
        FileIODemo.main(new String[]{});

        // Check if the output file exists
        File outputFile = new File(OUTPUT_FILE);
        assertTrue(outputFile.exists(), "Output file should exist");

        // Check if the contents of the output file match the input file
        try (BufferedReader inputReader = new BufferedReader(new FileReader(INPUT_FILE));
             BufferedReader outputReader = new BufferedReader(new FileReader(OUTPUT_FILE))) {

            String inputLine;
            String outputLine;
            while ((inputLine = inputReader.readLine()) != null) {
                outputLine = outputReader.readLine();
                assertEquals(inputLine, outputLine, "Lines in input and output files should match");
            }

            // Ensure the output file has no extra lines
            assertNull(outputReader.readLine(), "Output file should not have extra lines");
        }
    }

    @Test
    void testFileNotFound() {
        // Delete the input file to simulate FileNotFoundException
        new File(INPUT_FILE).delete();

        // Capture the output stream to check for exceptions
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Run the main method of FileIODemo
        FileIODemo.main(new String[]{});

        // Check if FileNotFoundException was printed
        String output = errContent.toString();
        assertTrue(output.contains("FileNotFoundException"), "Should print FileNotFoundException");
    }
}
