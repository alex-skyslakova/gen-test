import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CSVTest {

    @Test
    void testOpenAndSave() throws IOException {
        File inputFile = createTempFile("input.csv", 
                "C1,C2,C3\n",
                "1,2,3\n",
                "4,5,6\n");
        File outputFile = File.createTempFile("output", ".csv");

        CSV csv = new CSV();
        csv.open(inputFile);
        csv.save(outputFile);

        List<String> expectedLines = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
        List<String> actualLines = Files.readAllLines(Paths.get(outputFile.getAbsolutePath()));

        assertLinesMatch(expectedLines, actualLines);

        inputFile.delete();
        outputFile.delete();

    }

    @Test
    void testOpenWithCustomDelimiter() throws IOException {
        File inputFile = createTempFile("input.csv",
                "C1|C2|C3\n",
                "1|2|3\n",
                "4|5|6\n");
        File outputFile = File.createTempFile("output", ".csv");
        char delimiter = '|';

        CSV csv = new CSV();
        csv.open(inputFile, delimiter);
        csv.save(outputFile, delimiter);


        List<String> expectedLines = Files.readAllLines(Paths.get(inputFile.getAbsolutePath()));
        List<String> actualLines = Files.readAllLines(Paths.get(outputFile.getAbsolutePath()));

        assertLinesMatch(expectedLines, actualLines);


        inputFile.delete();
        outputFile.delete();

    }

    @Test
    void testGetAndPut() throws IOException {
        CSV csv = new CSV();
        csv.put(0, 0, "A");
        csv.put(1, 0, "B");
        csv.put(0, 1, "C");

        assertEquals("A", csv.get(0, 0));
        assertEquals("B", csv.get(1, 0));
        assertEquals("C", csv.get(0, 1));
        assertEquals("", csv.get(1,1)); // Test getting a non-existent value
        assertEquals(2, csv.cols());
        assertEquals(2, csv.rows());
    }



    @Test
    void testClear() throws IOException{
        CSV csv = new CSV();
        csv.put(0,0,"A");
        assertEquals(1, csv.cols());
        assertEquals(1, csv.rows());

        csv.clear();
        assertEquals(0, csv.cols());
        assertEquals(0, csv.rows());
        assertEquals("", csv.get(0,0));

    }

    @Test
    void testAddingSumColumn() throws IOException {
        File inputFile = createTempFile("input.csv",
                "C1,C2,C3\n",
                "1,2,3\n",
                "4,5,6\n");
        File outputFile = File.createTempFile("output", ".csv");


        CSV csv = new CSV();
        csv.open(inputFile);

        for(int row = 0; row < csv.rows(); row++){
            int sum = 0;
            for(int col = 0; col < csv.cols(); col++){
                sum += Integer.parseInt(csv.get(col, row));
            }
            csv.put(csv.cols(), row, String.valueOf(sum));
        }


        csv.save(outputFile);

        List<String> expectedLines = List.of("C1,C2,C3,SUM", "1,2,3,6", "4,5,6,15");
        List<String> actualLines = Files.readAllLines(Paths.get(outputFile.getAbsolutePath()));
        assertLinesMatch(expectedLines, actualLines);

        inputFile.delete();
        outputFile.delete();
    }

    private File createTempFile(String fileName, String... lines) throws IOException {
        File file = File.createTempFile(fileName, ".tmp");
        Files.write(Paths.get(file.getAbsolutePath()), List.of(lines));
        return file;
    }


}
