import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVTest {

    private static final String TEST_IN_CSV = "test_in.csv";
    private static final String TEST_OUT_CSV = "test_out.csv";
    private static final String TEST_DATA = "C1,C2,C3,C4,C5\n1,5,9,13,17\n2,6,10,14,18\n3,7,11,15,19\n4,8,12,16,20\n";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a test input CSV file
        Files.write(Paths.get(TEST_IN_CSV), TEST_DATA.getBytes());
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the test CSV files
        Files.deleteIfExists(Paths.get(TEST_IN_CSV));
        Files.deleteIfExists(Paths.get(TEST_OUT_CSV));
    }

    @Test
    public void testOpen() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV));

        assertEquals(5, csv.cols());
        assertEquals(5, csv.rows());
        assertEquals("C1", csv.get(0, 0));
        assertEquals("17", csv.get(4, 1));
        assertEquals("20", csv.get(4, 4));
    }

    @Test
    public void testSave() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV));
        csv.put(0, 0, "Column0");
        csv.put(1, 1, "100");
        csv.put(2, 2, "200");
        csv.put(3, 3, "300");
        csv.put(4, 4, "400");
        csv.save(new File(TEST_OUT_CSV));

        List<String> lines = Files.readAllLines(Paths.get(TEST_OUT_CSV));
        assertEquals(5, lines.size());
        assertEquals("Column0,C2,C3,C4,C5", lines.get(0));
        assertEquals("1,100,10,14,18", lines.get(2));
        assertEquals("4,8,12,16,400", lines.get(4));
    }

    @Test
    public void testAddColumnWithSum() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV));

        // Add a column with the sum of each row
        for (int row = 1; row < csv.rows(); row++) {
            int sum = 0;
            for (int col = 0; col < csv.cols(); col++) {
                sum += Integer.parseInt(csv.get(col, row));
            }
            csv.put(csv.cols(), row, String.valueOf(sum));
        }

        csv.save(new File(TEST_OUT_CSV));

        List<String> lines = Files.readAllLines(Paths.get(TEST_OUT_CSV));
        assertEquals(5, lines.size());
        assertEquals("C1,C2,C3,C4,C5,SUM", lines.get(0));
        assertEquals("1,5,9,13,17,45", lines.get(1));
        assertEquals("2,6,10,14,18,50", lines.get(2));
        assertEquals("3,7,11,15,19,55", lines.get(3));
        assertEquals("4,8,12,16,20,60", lines.get(4));
    }

    @Test
    public void testClear() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV));
        csv.clear();

        assertEquals(0, csv.cols());
        assertEquals(0, csv.rows());
    }

    @Test
    public void testPutAndGet() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV));

        csv.put(0, 0, "Test");
        csv.put(1, 1, "Value");

        assertEquals("Test", csv.get(0, 0));
        assertEquals("Value", csv.get(1, 1));
    }

    @Test
    public void testOpenWithDelimiter() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV), ';');

        assertEquals(1, csv.cols());
        assertEquals(1, csv.rows());
        assertEquals(TEST_DATA.replaceAll(",", ";"), csv.get(0, 0));
    }

    @Test
    public void testSaveWithDelimiter() throws IOException {
        CSV csv = new CSV();
        csv.open(new File(TEST_IN_CSV));
        csv.save(new File(TEST_OUT_CSV), ';');

        List<String> lines = Files.readAllLines(Paths.get(TEST_OUT_CSV));
        assertEquals(5, lines.size());
        assertEquals("C1;C2;C3;C4;C5", lines.get(0));
        assertEquals("1;5;9;13;17", lines.get(1));
        assertEquals("2;6;10;14;18", lines.get(2));
        assertEquals("3;7;11;15;19", lines.get(3));
        assertEquals("4;8;12;16;20", lines.get(4));
    }
}
