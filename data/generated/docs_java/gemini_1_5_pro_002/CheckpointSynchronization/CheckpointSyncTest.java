import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

public class CheckpointSyncTest {

    @Test
    void testCheckpointSynchronization() throws InterruptedException {
        // Simulate user input
        String input = "3\n2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outputStream);
        System.setOut(out);


        // Run the main method in a separate thread
        Thread mainThread = new Thread(() -> CheckpointSync.main(new String[]{}));
        mainThread.start();
        mainThread.join();

        // Reset System.in and System.out
        System.setIn(System.in);
        System.setOut(System.out);

        // Assertions based on expected output (adjust as needed)
        String output = outputStream.toString();
        assertTrue(output.contains("Starting task number 1."));
        assertTrue(output.contains("Starting task number 2."));
        for(int i=1; i<=3; ++i) {
            assertTrue(output.contains("Worker " + i + " will work for "));
            assertTrue(output.contains("Worker " + i + " is ready"));
        }
    }



    @Test
    void testWorkerCheckpoint() throws InterruptedException {
        CheckpointSync.Worker.nWorkers = 3;
        CheckpointSync.Worker w1 = new CheckpointSync.Worker(1);
        CheckpointSync.Worker w2 = new CheckpointSync.Worker(2);
        CheckpointSync.Worker w3 = new CheckpointSync.Worker(3);


        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w2);
        Thread t3 = new Thread(w3);

        t1.start();
        t2.start();
        t3.start();


        // Simulate all workers finishing
        AtomicInteger finishedCount = new AtomicInteger(0);

        Thread checkThread = new Thread(() -> {
            while (finishedCount.get() < 3) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            CheckpointSync.Worker.checkpoint();
        });

        checkThread.start();

        t1.join();
        finishedCount.incrementAndGet();
        t2.join();
        finishedCount.incrementAndGet();
        t3.join();
        finishedCount.incrementAndGet();


        checkThread.join(); // Wait for checkpoint to complete

        assertEquals(0, CheckpointSync.Worker.nFinished);


    }



    @Test
    void testWorkerRun() {
        CheckpointSync.Worker.nWorkers = 1;
        CheckpointSync.Worker w = new CheckpointSync.Worker(1);
        Thread t = new Thread(w);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals(1, CheckpointSync.Worker.nFinished);
    }


}
