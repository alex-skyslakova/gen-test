import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreadsTest {

    @Test
    public void testMessageOrder() throws InterruptedException {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the main method
        Threads.main(new String[]{});

        // Give some time for threads to complete
        Thread.sleep(100);

        // Get the output and split it into lines
        String output = outContent.toString().trim();
        String[] lines = output.split("\\r?\\n");

        // Check that there are exactly 3 lines
        assertEquals(3, lines.length, "There should be exactly 3 lines of output.");

        // Check that all expected messages are present
        Set<String> expectedMessages = new HashSet<>(Arrays.asList("Enjoy", "Rosetta", "Code"));
        Set<String> actualMessages = new HashSet<>(Arrays.asList(lines));
        assertEquals(expectedMessages, actualMessages, "The output should contain 'Enjoy', 'Rosetta', and 'Code'.");

        // Check that the order is random by running multiple times
        boolean isRandom = false;
        for (int i = 0; i < 10; i++) {
            outContent.reset();
            Threads.main(new String[]{});
            Thread.sleep(100);
            String newOutput = outContent.toString().trim();
            String[] newLines = newOutput.split("\\r?\\n");
            if (!Arrays.equals(lines, newLines)) {
                isRandom = true;
                break;
            }
        }
        assertTrue(isRandom, "The order of the messages should be random.");
    }
}
