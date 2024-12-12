import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class SphereTest {

    @Test
    void testDrawSphereSmallRadius() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Sphere.drawSphere(5, 2, 0.4);

        String expectedOutput = 
              "          @@@@@@@@@\n"
            + "       @@@@@@@@@@@@@@@\n"
            + "     @@@@@@@@@@@@@@@@@@@\n"
            + "    @@@@@@@@@@@@@@@@@@@@@\n"
            + "  @@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + " @@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + " @@@@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "  @@@@@@@@@@@@@@@@@@@@@@@@@\n"
            + "    @@@@@@@@@@@@@@@@@@@@@\n"
            + "     @@@@@@@@@@@@@@@@@@@\n"
            + "       @@@@@@@@@@@@@@@\n"
            + "          @@@@@@@@@\n"; // Simplified expected output. Full output is complex to represent here.

       String actualOutput = outputStream.toString().trim().replaceAll("\r", ""); // Remove carriage return for cross-platform testing
       
       String[] expectedLines = expectedOutput.split("\n");
       String[] actualLines = actualOutput.split("\n");
       
       assertEquals(expectedLines.length, actualLines.length);
       
       for (int i = 0; i < expectedLines.length; i++) {
           assertEquals(expectedLines[i].trim(), actualLines[i].trim()); // Compare trimmed lines to ignore leading/trailing spaces.
       }

        System.setOut(System.out); // Restore standard output
    }




    @Test
    void testNormalize() {
        double[] v = {3, 4, 0};
        Sphere.normalize(v);
        assertEquals(0.6, v[0], 0.0001);
        assertEquals(0.8, v[1], 0.0001);
        assertEquals(0.0, v[2], 0.0001);
    }

    @Test
    void testDotPositive() {
        double[] x = {1, 0, 0};
        double[] y = {0.5, 0, 0};
        assertEquals(0.0, Sphere.dot(x, y), 0.0001);
    }


    @Test
    void testDotNegative() {
        double[] x = {1, 0, 0};
        double[] y = {-0.5, 0, 0};
        assertEquals(0.5, Sphere.dot(x, y), 0.0001);
    }

}
