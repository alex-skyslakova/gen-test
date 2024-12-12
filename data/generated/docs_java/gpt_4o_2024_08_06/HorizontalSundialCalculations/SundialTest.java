import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class SundialTest {

    @Test
    public void testSundialCalculations() {
        // Test input: Latitude 4.95°S, Longitude 150.5°W, Legal Meridian 150°W
        String input = "4.95\n-150.5\n-150\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method
        Sundial.main(new String[]{});

        // Expected output
        String expectedOutput = "Enter latitude: Enter longitude: Enter legal meridian: \n" +
                "sine of latitude: -0.086\n" +
                "diff longitude: -0.500\n\n" +
                "Hour, sun hour angle, dial hour line angle from 6am to 6pm\n" +
                "HR= -6;   \t  HRA=  60.500;   \t  HLA=  60.500\n" +
                "HR= -5;   \t  HRA=  75.500;   \t  HLA=  75.500\n" +
                "HR= -4;   \t  HRA=  90.500;   \t  HLA=  90.500\n" +
                "HR= -3;   \t  HRA= 105.500;   \t  HLA= 105.500\n" +
                "HR= -2;   \t  HRA= 120.500;   \t  HLA= 120.500\n" +
                "HR= -1;   \t  HRA= 135.500;   \t  HLA= 135.500\n" +
                "HR=   0;   \t  HRA= 150.500;   \t  HLA= 150.500\n" +
                "HR=   1;   \t  HRA= 165.500;   \t  HLA= 165.500\n" +
                "HR=   2;   \t  HRA= 180.500;   \t  HLA= 180.500\n" +
                "HR=   3;   \t  HRA= 195.500;   \t  HLA= 195.500\n" +
                "HR=   4;   \t  HRA= 210.500;   \t  HLA= 210.500\n" +
                "HR=   5;   \t  HRA= 225.500;   \t  HLA= 225.500\n" +
                "HR=   6;   \t  HRA= 240.500;   \t  HLA= 240.500\n";

        // Assert the output
        assertEquals(expectedOutput, outContent.toString());
    }
}
