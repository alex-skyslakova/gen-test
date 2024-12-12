import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CyclicBarrier;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThreadsTest {

    @Test
    public void testDelayedMessagePrinter() throws InterruptedException {
        // Capture the output of System.out
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a CyclicBarrier with 3 parties
        CyclicBarrier barrier = new CyclicBarrier(3);

        // Create and start the threads
        Thread thread1 = new Thread(new Threads.DelayedMessagePrinter(barrier, "Enjoy"));
        Thread thread2 = new Thread(new Threads.DelayedMessagePrinter(barrier, "Rosetta"));
        Thread thread3 = new Thread(new Threads.DelayedMessagePrinter(barrier, "Code"));

        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for all threads to finish
        thread1.join();
        thread2.join();
        thread3.join();

        // Get the output
        String output = outputStream.toString();

        // Verify that all messages are printed in any order
        assertTrue(output.contains("Enjoy"));
        assertTrue(output.contains("Rosetta"));
        assertTrue(output.contains("Code"));
    }
}
