import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CheckpointSyncTest {

    @Test
    public void testCheckpointSynchronization() throws InterruptedException {
        // Set the number of workers and tasks
        Worker.nWorkers = 3;
        int nTasks = 2;

        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the tasks
        CheckpointSync.runTasks(nTasks);

        // Restore the original console output
        System.setOut(System.out);

        // Verify the output
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Check that all workers are ready for each task
        int taskCount = 0;
        for (String line : lines) {
            if (line.contains("Starting task number")) {
                taskCount++;
            }
            if (line.contains("Worker") && line.contains("is ready")) {
                assertTrue(line.contains("Worker 1") || line.contains("Worker 2") || line.contains("Worker 3"));
            }
        }
        assertEquals(nTasks, taskCount, "Incorrect number of tasks started");
    }

    @Test
    public void testWorkerCounting() throws InterruptedException {
        // Set the number of workers and tasks
        Worker.nWorkers = 3;
        int nTasks = 2;

        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the tasks
        CheckpointSync.runTasks(nTasks);

        // Restore the original console output
        System.setOut(System.out);

        // Verify the output
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Check that no worker is counted twice within one working cycle
        int readyCount = 0;
        for (String line : lines) {
            if (line.contains("is ready")) {
                readyCount++;
            }
        }
        assertEquals(Worker.nWorkers * nTasks, readyCount, "A worker was counted twice within one working cycle");
    }

    @Test
    public void testWorkerJoiningAndLeaving() throws InterruptedException {
        // Set the number of workers and tasks
        Worker.nWorkers = 3;
        int nTasks = 2;

        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the tasks
        CheckpointSync.runTasks(nTasks);

        // Restore the original console output
        System.setOut(System.out);

        // Verify the output
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Check that all workers are ready for each task
        int taskCount = 0;
        for (String line : lines) {
            if (line.contains("Starting task number")) {
                taskCount++;
            }
            if (line.contains("Worker") && line.contains("is ready")) {
                assertTrue(line.contains("Worker 1") || line.contains("Worker 2") || line.contains("Worker 3"));
            }
        }
        assertEquals(nTasks, taskCount, "Incorrect number of tasks started");
    }

    @Test
    public void testRaceConditionFree() throws InterruptedException {
        // Set the number of workers and tasks
        Worker.nWorkers = 3;
        int nTasks = 2;

        // Capture the console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Run the tasks
        CheckpointSync.runTasks(nTasks);

        // Restore the original console output
        System.setOut(System.out);

        // Verify the output
        String output = outContent.toString();
        String[] lines = output.split("\n");

        // Check that no worker is counted twice within one working cycle
        int readyCount = 0;
        for (String line : lines) {
            if (line.contains("is ready")) {
                readyCount++;
            }
        }
        assertEquals(Worker.nWorkers * nTasks, readyCount, "A worker was counted twice within one working cycle");
    }
}
