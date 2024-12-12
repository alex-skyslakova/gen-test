import org.junit.Test;
import static org.junit.Assert.*;
import java.io.*;

public class ProgramTest {

    @Test
    public void testWindowsCommandExecution() {
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            try {
                Process p = Runtime.getRuntime().exec("cmd /C dir");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                reader.close();
                assertTrue("Output should contain directory listing", output.length() > 0);
            } catch (IOException e) {
                fail("IOException should not occur: " + e.getMessage());
            }
        }
    }

    @Test
    public void testUnixCommandExecution() {
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            try {
                Process p = Runtime.getRuntime().exec("ls -oa");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                reader.close();
                assertTrue("Output should contain directory listing", output.length() > 0);
            } catch (IOException e) {
                fail("IOException should not occur: " + e.getMessage());
            }
        }
    }
}
