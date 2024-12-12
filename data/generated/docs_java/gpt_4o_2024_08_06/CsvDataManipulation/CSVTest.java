import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CSVTest {

    private CSV csv;
    private File inputFile;
    private File outputFile;

    @BeforeEach
    public void setUp() throws IOException {
        csv = new CSV();
        inputFile = new File("test_in.csv");
        outputFile = new File("test_out.csv");

        // Create a sample CSV file for testing
        String csvContent = "C1,C2,C3,C4,C5\n" +
                            "1,5,9,13,17\n" +
                            "2,6,10,14,18\n" +
                            "3,7,11,15,19\n" +
                            "4,8,12,16,20\n";
        Files.write(inputFile.toPath(), csvContent.getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
        Files.deleteIfExists(inputFile.toPath());
        Files.deleteIfExists(outputFile.toPath());
    }

    @Test
    public void testOpenAndReadCSV() throws IOException {
        csv.open(inputFile);

        assertEquals("C1", csv.get(0, 0));
        assertEquals("5", csv.get(1, 1));
        assertEquals("10", csv.get(2, 2));
        assertEquals("16", csv.get(3, 3));
        assertEquals("20", csv.get(4, 4));
    }

    @Test
    public void testPutAndSaveCSV() throws IOException {
        csv.open(inputFile);

        csv.put(0, 0, "Column0");
        csv.put(1, 1, "100");
        csv.put(2, 2, "200");
        csv.put(3, 3, "300");
        csv.put(4, 4, "400");

        csv.save(outputFile);

        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertEquals("Column0,C2,C3,C4,C5", lines.get(0));
        assertEquals("1,100,9,13,17", lines.get(1));
        assertEquals("2,6,200,14,18", lines.get(2));
        assertEquals("3,7,11,300,19", lines.get(3));
        assertEquals("4,8,12,16,400", lines.get(4));
    }

    @Test
    public void testAddSumColumn() throws IOException {
        csv.open(inputFile);

        for (int row = 1; row < csv.rows(); row++) {
            int sum = 0;
            for (int col = 0; col < csv.cols(); col++) {
                sum += Integer.parseInt(csv.get(col, row));
            }
            csv.put(csv.cols(), row, String.valueOf(sum));
        }
        csv.put(csv.cols(), 0, "SUM");

        csv.save(outputFile);

        List<String> lines = Files.readAllLines(outputFile.toPath());
        assertEquals("C1,C2,C3,C4,C5,SUM", lines.get(0));
        assertEquals("1,5,9,13,17,45", lines.get(1));
        assertEquals("2,6,10,14,18,50", lines.get(2));
        assertEquals("3,7,11,15,19,55", lines.get(3));
        assertEquals("4,8,12,16,20,60", lines.get(4));
    }
}
