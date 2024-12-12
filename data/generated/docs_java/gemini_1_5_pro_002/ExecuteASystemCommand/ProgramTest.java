import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class ProgramTest {

    @Test
    void testMainWindows() throws IOException {
        // Redirect System.out to capture output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Execute the main method
        Program.main(new String[0]);
       
        // Check for expected output (Partial check - At least the Volume label)
        String expectedOutputWindows = " Volume in drive ";
        assertTrue(outContent.toString().contains(expectedOutputWindows));


        System.setOut(System.out); // Restore System.out
    }



    @Test
    void testMainUnix() throws IOException {

                // Simulate UNIX environment (not a true test on a windows system without mocking)
        // This test is designed to check the error handling path on a Windows system when trying to execute a unix command.
        // On a real Unix system, we would expect a directory listing.

         ByteArrayOutputStream errContent = new ByteArrayOutputStream();
         System.setErr(new PrintStream(errContent));

          try {
            Process p = Runtime.getRuntime().exec("ls -oa");
            Scanner sc = new Scanner(p.getInputStream());    		
    		while (sc.hasNext()) System.out.println(sc.nextLine());
            fail("Expected an IOException on Windows for a Unix command.");
        } catch (IOException e) {
            // On a Windows system trying to run a Unix command, we expect an IOException.
            // A more robust approach would be to mock Runtime.exec() and verify error handling.
             assertNotNull(e.getMessage()); // Check that an error message exists
        }


        System.setErr(System.err);

    }
}
