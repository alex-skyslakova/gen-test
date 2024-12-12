import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentNumbersTest {

    @Test
    void testValidCombinations() {
        // Capture the output of the main method
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the main method
        DepartmentNumbers.main(new String[]{});

        // Expected output
        String expectedOutput = "Police  Sanitation  Fire\n" +
                "------  ----------  ----\n" +
                " 2         3         7\n" +
                " 2         5         5\n" +
                " 2         7         3\n" +
                " 4         1         7\n" +
                " 4         3         5\n" +
                " 4         5         3\n" +
                " 4         7         1\n" +
                " 6         1         5\n" +
                " 6         5         1\n" +
                "\n9 valid combinations";

        // Assert the output
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}
